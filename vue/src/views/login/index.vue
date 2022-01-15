<template>
  <div class="login-container">
    <el-form ref="loginForm" 
        class="login-form" 
        :model="loginForm" 
        autocomplete="on" 
        label-position="left">

      <div class="title-container">
        <h3 class="title">Login Form</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
      </el-tooltip>


      <div style="margin-bottom:15px;margin-left:10px;">
        <el-checkbox v-model="checked">记住密码</el-checkbox>
      </div>

      <div class="other-select">
        <el-button :loading="loading" class="thirdparty-button" type="primary" @click.native.prevent="handleLogin">
          登录
        </el-button>

        <el-button :loading="hadFaceLoading" class="thirdparty-button" type="primary" @click="handleFaceLogin">
          刷脸
        </el-button>
      </div>
    </el-form>

    <el-dialog :visible.sync="visible" title="人脸登录" >
      <div class="camera_outer">
        <!-- :width="videoWidth" :height="videoHeight" -->
        <video id="videoCamera" width="60%" autoplay></video>
        <!-- :width="videoWidth" :height="videoHeight" -->
        <canvas style="display:none;" id="canvasCamera"></canvas>
        <div v-if="imgSrc" class="img_bg_camera">
          <img :src="imgSrc" alt="" class="tx_img">
        </div>
        <div></div>
        <button @click="getCompetence()">打开摄像头</button>
        <button @click="stopNavigator()">关闭摄像头</button>
        <button @click="setImage()" >验证</button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'

import { loginUserByPass , hadExitFace , loginByFace } from '@/api/api/login.js'
import { setToken , getAccount , removeAccount , setAccount } from '@/utils/auth'
import axios from 'axios'
export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      redirect: undefined,
      otherQuery: {},
      checked:false,
      hadFaceLoading:false,

      videoWidth: 100,
      videoHeight: 100,
      imgSrc: '',
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      visible:false,
      path:'',
      id:""
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    let loginForm = getAccount()
    if(loginForm){
      this.loginForm = JSON.parse(loginForm)
      this.checked = true
    }
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  methods: {
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      if(this.loginForm.username.trim().length == 0 ||
          this.loginForm.password.trim().length == 0){
        this.$message.error('请输入完整信息!');
        return
      }
      this.loading = true
      loginUserByPass(this.loginForm).then(res => {
        console.log(res)
        if(res.state == 200){
          if(this.checked){
            //记住密码
            setAccount(this.loginForm)
          }else{
            //不记住密码
            removeAccount()
          }
          //登录成功
          this.$store.dispatch('user/login', res.data)
          // setToken('SET_TOKEN', JSON.stringify(res.data))
          this.$router.push({ path: this.redirect || '/'})
        }else{
          this.$message.error('账号或者密码错误!');
        }
        this.loading = false
      })

    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    },
    handleFaceLogin(){
      if(this.loginForm.username.trim().length == 0){
        this.$message.error('请输入用户账号!');
        return
      }
      this.hadFaceLoading = true
      let data = {
        "loginname":this.loginForm.username
      }
      hadExitFace(data)
      .then(res => {
        this.hadFaceLoading = false
        if(res == "error"){
          this.$message.error('该账号不存在!');
          return
        }
        this.visible = true
        // res.path为图片名称
        this.path = res.path
        this.id = res.id

      })
    },
    getCompetence () {
      var _this = this
      this.thisCancas = document.getElementById('canvasCamera')
      this.thisContext = this.thisCancas.getContext('2d')
      this.thisVideo = document.getElementById('videoCamera')
      // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {}
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          
          if (!getUserMedia) {
            return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject)
          })
        }
      }
      var constraints = { audio: false, video: { width: this.videoWidth, height: this.videoHeight, transform: 'scaleX(-1)' } }
      navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        // 旧的浏览器可能没有srcObject
        if ('srcObject' in _this.thisVideo) {
          _this.thisVideo.srcObject = stream
        } else {
          // 避免在新的浏览器中使用它，因为它正在被弃用。
          _this.thisVideo.src = window.URL.createObjectURL(stream)
        }
        _this.thisVideo.onloadedmetadata = function (e) {
          _this.thisVideo.play()
        }
      }).catch(err => {
        console.log(err)
      })
    },
//  绘制图片（拍照功能）

    setImage () {
      var _this = this
      // 点击，canvas画图
      _this.thisContext.drawImage(_this.thisVideo, 0, 0, _this.videoWidth, _this.videoHeight)
      // 获取图片base64链接
      var image = this.thisCancas.toDataURL('image/png')
      // _this.imgSrc = image

      let data = {
        base64Data:image,
        id:this.id,
        path:this.path
      }
      let loading = this.$loading({
        lock: true,
        text: '验证中~',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      loginByFace(data)
      .then(res => {
        loading.close()
        if(res.length != 0){
          //登录成功
          this.$store.dispatch('user/login', res)
          this.$router.push({ path: this.redirect || '/'})
        }else{
          //验证失败
          this.$message.error('验证失败!!');
        }
      })
    },
// base64转文件

    dataURLtoFile (dataurl, filename) {
      var arr = dataurl.split(',')
      var mime = arr[0].match(/:(.*?);/)[1]
      var bstr = atob(arr[1])
      var n = bstr.length
      var u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new File([u8arr], filename, { type: mime })
    },
// 关闭摄像头

    stopNavigator () {
      this.thisVideo.srcObject.getTracks()[0].stop()
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .other-select{
    button{
      width: 50%;

    }
    display: flex;
    justify-content: space-between;
  }

  
}
</style>
