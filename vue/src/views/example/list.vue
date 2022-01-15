<template>
  <div class="app-container">
    
    <el-form ref="dataForm" 
        :model="temp" 
        label-position="left" 
        label-width="70px" 
        v-loading="listLoading"
        style="width: 400px; margin-left:50px;">
      <el-form-item label="标题" >
        <el-input v-model="temp.title" placeholder="输入标题" />
      </el-form-item>

      <el-form-item label="描述" >
        <el-input v-model="temp.remark" 
          :autosize="{ minRows: 8}"
          type="textarea" 
          placeholder="请输入描述文件内容!"/>
      </el-form-item>

      <el-upload
          class="upload-demo"
          action="/api/upload"
          ref="uploads"
          :before-remove="beforeRemove"
          :multiple="false"
          :auto-upload="false"
          :limit="1"
          :on-exceed="handleExceed"
          :http-request="uploadFile"
          :on-change="changeFileList"
          :on-remove="changeFileList"
          :file-list.sync="fileList">
          <el-button size="small" type="primary">选择上传文件</el-button>

      </el-upload>

      
      <div class="select-button">
        <el-button type="primary" @click="submitEvnet">提交</el-button>
        <el-button type="primary" @click="resetEvent">重置</el-button>  
      </div>
      

    </el-form>

  

  
  </div>
</template>

<script>
import { fetchList , updataDocu , addDocu , deleteDocu , upload } from '@/api/api/upLoad'
export default {
  name: 'ArticleList',
  data() {
    return {
      temp: {
        title: '',
        remark: '',
        userId: this.$store.getters.userInfo.id,
        filename: '',
      },
      listLoading:false,
      fileList:[]
    }
  },
  created() {
    // this.getList()
  },
  methods: {
    submitEvnet(){ 
      if(this.temp.title.trim().length == 0 ||
          this.temp.remark.trim().length == 0 ) {
         this.$message.error('信息填写未完整!')
        return
      }
      if(this.fileList.length == 0){
        this.$message.error('请上传文件!')
        return
      }
      this.$refs.uploads.submit();
    },
    resetEvent(){
      this.temp = Object.assign({
        title: '',
        remark: '',
        userId: this.$store.getters.userInfo.id,
        filename: ''
      })
      if(this.fileList.length > 0)  this.fileList.pop()
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，请取消之前文件，再进行添加!`);
    },
    changeFileList(file, fileList){
      this.fileList = fileList
    },
    uploadFile(param){
      let data = new FormData()
      data.append("file",param.file)
      this.listLoading = true
      upload(data)
      .then(res => {
        if(res.status != 200) {
          this.$message.error('上传文件发生错误!!!');
          return
        }
        const filename = res.path
        let data = {
          title:this.temp.title,
          userId:this.$store.getters.userInfo.id,
          remark:this.temp.remark,
          filename
        }
        this.addDocuEvent(data)
      })
    },
    addDocuEvent(data){
      addDocu(data)
      .then(response => {
        console.log(response)
        this.$notify({
          title: 'Success',
          message: 'AddDocument Successfully',
          type: 'success',
          duration: 2000
        })
        this.listLoading = false
        this.resetEvent()
      })
    },

  }
}
</script>

<style lang="scss">
  .filter-container{
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    .filter-div{
      margin-right: 10px;
    }
    .filter-item,button{
      margin:0;
    }
  }

  .upload-demo{
    display:flex;
    .el-upload-list__item{
      margin: 0;
      margin-left: 10px;
      height: 100%;
      display: flex;
      position: relative;
      align-items: center;
      .el-icon-close{
        top: 0;bottom: 0;
        align-items: center;
        display: flex;
      }
    }
  }

  .select-button{
    margin-top: 20px;
  }
</style>
