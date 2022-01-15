<template>
  <div class="app-container">
    <div class="filter-container">
      <div class="filter-div">
        员工名称
        <el-input v-model="listQuery.title" 
          placeholder="输入员工名称：" 
          style="width: 200px;" 
          class="filter-item"
          />
      </div>
      
      <!-- <div class="filter-div">
        用户状态：
        <el-select v-model="listQuery.importance" placeholder="权限" clearable style="width: 90px" class="filter-item">
          <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
        </el-select>
      </div> -->

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search 
      </el-button>

      <div v-if="userInfo.status == 1">
        <el-button class="filter-item" 
          style="margin-left: 10px;" 
          type="primary" 
          icon="el-icon-edit" 
          @click="handleCreate">
          Add
        </el-button>

        <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleDelete">
          Delete
        </el-button>  
      </div>
      

    </div>

    <el-table
      ref="multipleTable"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
        v-if="userInfo.status == 1">
      </el-table-column>

      <el-table-column label="姓名" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template> 
      </el-table-column>
      <el-table-column label="性别" width="200px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.sex == 1">男</el-tag>
          <el-tag v-if="row.sex == 2">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column label="职位" width="200px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.jobId == 1">职员</el-tag>
          <el-tag v-if="row.jobId == 2">Java开发工程师</el-tag>
          <el-tag v-if="row.jobId == 3">Java中级开发工程师</el-tag>
          <el-tag v-if="row.jobId == 4">Java高级开发工程师</el-tag>
          <el-tag v-if="row.jobId == 6">架构师</el-tag>
          <el-tag v-if="row.jobId == 14">老板</el-tag>
          <el-tag v-if="row.jobId == 9">总经理</el-tag>
          <el-tag v-if="row.jobId == 8">经理</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="学历" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.education }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号码" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.cardId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门" width="200px" align="center">
        <template slot-scope="{row}">
          <!-- <span>{{ row.deptId }}</span> -->
          <el-tag v-if="row.deptId == 1">技术部</el-tag>
          <el-tag v-if="row.deptId == 2">运营部</el-tag>
          <el-tag v-if="row.deptId == 3">财务部</el-tag>
          <el-tag v-if="row.deptId == 4">市场部</el-tag>
          <el-tag v-if="row.deptId == 7">摸鱼部</el-tag>
          <el-tag v-if="row.deptId == 8">无敌部</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="联系地址" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.address }}</span>
        </template>
      </el-table-column>
  
      
      <el-table-column label="建档日期" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createDate }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="密码" min-width="150px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.password }}</span>
        </template>
      </el-table-column>

      <el-table-column label="用户名" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.username }}</span>
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="详细信息" width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.status }}</span>
        </template>
      </el-table-column> -->




      <!-- <el-table-column label="创造时间" class-name="status-col" width="300px">
        <template slot-scope="{row}">
          <span>{{ row.createdate }}</span>
        </template>
      </el-table-column> -->

      <el-table-column label="操作" 
        v-if="userInfo.status == 1"
        align="center" 
        width="230" 
        class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            Edit
          </el-button>  
        </template>
      </el-table-column>
    </el-table>


    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />


    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="ID(必填)" >
          <el-input v-model="temp.id" placeholder />
        </el-form-item>

        <el-form-item label="DEPT_ID(必填)" >
          <el-input v-model="temp.deptId" />
        </el-form-item>
        <el-form-item label="JOB_ID(必填)" >
          <el-input v-model="temp.jobId" />
        </el-form-item>
        <el-form-item label="NAME(必填)" >
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="CARD_ID(必填)" >
          <el-input v-model="temp.cardId" />
        </el-form-item>
        <el-form-item label="ADDRESS(必填)" >
          <el-input v-model="temp.address" />
        </el-form-item>
        <el-form-item label="POST_CODE" >
          <el-input v-model="temp.postCode" />
        </el-form-item>
        <el-form-item label="TEL" >
          <el-input v-model="temp.tel" />
        </el-form-item>
        <el-form-item label="PHONE(必填)" >
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="QQ_NUM" >
          <el-input v-model="temp.qqNum" />
        </el-form-item>
        <el-form-item label="EMAIL(必填)" >
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="SEX(必填)" >
          <el-input v-model="temp.sex" />
        </el-form-item>
        <el-form-item label="PARTY" >
          <el-input v-model="temp.party" />
        </el-form-item>
        <el-form-item label="BIRTHDAY" >
          <el-input v-model="temp.birthday" />
        </el-form-item>
        <el-form-item label="SPECIALITY" >
          <el-input v-model="temp.speciality" />
        </el-form-item>
        <el-form-item label="HOBBY" >
          <el-input v-model="temp.hobby" />
        </el-form-item>
        <el-form-item label="REMARK" >
          <el-input v-model="temp.remark" />
        </el-form-item>

        <!-- <el-form-item label="密码" v-if="this.dialogStatus == 'create'">
          <el-input v-model="temp.password" />
        </el-form-item> -->

        <!-- <el-form-item label="权限">
          <el-select v-model="temp.status" class="filter-item" placeholder="Please select">
            <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item> -->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { loginUserByPass } from '@/api/api/login'
import { removeUser } from '@/utils/auth'
import { fetchList , updateEmp , addEmp , deleteEmp } from '@/api/api/emp'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      //获取登录用户的信息,权限在里面,status==1 为管理员  ==2普通用户
      userInfo:this.$store.getters.userInfo,
      //进行多选择数组
      multipleSelection: [],
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined,
        title: undefined,
        type: undefined,
      },
      importanceOptions: ['管理员', '普通用户'],
      statusOptions: ['管理员', '普通用户'],
      temp: {
        // id: undefined,
        importance: 1,
        loginname: '',
        username: '',
        status: '普通用户',
        password:'',

        id:'',
        deptId: '',
        jobId: '',
        name: '',
        cardId: '',
        address: '',
        postCode: '',
        tel: '',
        phone: '',
        qqNum: '',
        email: '',
        sex: '',
        party: '',
        birthday: '',
        race: '',
        education: '',
        speciality: '',
        hobby: '',
        remark: '',
        createDate: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleSelectionChange(val) {
      let multipleSelection = []
      for(let index in val){
        multipleSelection.push(val[index].id)
      }
      this.multipleSelection = multipleSelection
    },
    getList() {
      this.listLoading = true
      let data = {
        page:this.listQuery.page,
        size:this.listQuery.limit
      }
      if(this.listQuery.title != undefined && this.listQuery.title.trim().length != 0 ){
        data['name'] = this.listQuery.title
      }
      if(this.listQuery.importance != undefined && this.listQuery.importance.length != 0){
        data['status'] = this.listQuery.importance == '管理员' ? 1 : 2
      }
      fetchList(data).then(response => {
        this.list = response.data
        this.total = response.pageMax
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetTemp() {
      this.temp = {
        // id: undefined,
        importance: 1,
        loginname: '',
        username: '',
        status: '普通用户',
        password:'',

        id:'',
        deptId: '',
        jobId: '',
        name: '',
        cardId: '',
        address: '',
        postCode: '',
        tel: '',
        phone: '',
        qqNum: '',
        email: '',
        sex: '',
        party: '',
        birthday: '',
        race: '',
        education: '',
        speciality: '',
        hobby: '',
        remark: '',
        createDate: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    //删除信息
    handleDelete(){
      if(this.multipleSelection.length == 0){
        this.$message.error('请选择要删除的数据!')
        return
      }
      this.listLoading = true
      deleteEmp(this.multipleSelection)
      .then(response => {
        this.$notify({
          title: 'Success',
          message: 'delete Successfully',
          type: 'success',
          duration: 2000
        })
        this.getList()
        this.listLoading = false
      })
    },
    createData() {
      if(this.temp.id.trim().length == 0
        || this.temp.deptId.trim().length == 0
        || this.temp.jobId.trim().length == 0
        || this.temp.name.trim().length == 0
        || this.temp.cardId.trim().length == 0
        || this.temp.address.trim().length == 0
        || this.temp.phone.trim().length == 0
        || this.temp.email.trim().length == 0
        || this.temp.sex.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }
      let data = {
        username:this.temp.username,
        loginname:this.temp.loginname,
        status: this.temp.status == '管理员' ? 1 : 2,
        password: this.temp.password,

        id: this.temp.id,
        deptId: this.temp.deptId,
        jobId: this.temp.jobId,
        name: this.temp.name,
        cardId: this.temp.cardId,
        address: this.temp.address,
        postCode: this.temp.postCode,
        tel: this.temp.tel,
        phone: this.temp.phone,
        qqNum: this.temp.qqNum,
        email: this.temp.email,
        sex: this.temp.sex,
        party: this.temp.party,
        birthday: this.temp.birthday,
        race: this.temp.race,
        education: this.temp.education,
        speciality: this.temp.speciality,
        hobby: this.temp.hobby,
        remark: this.temp.remark,
        createDate: this.temp.createDate

      }
      this.listLoading = true
      addEmp(data)
      .then(response => {
        this.$notify({
          title: 'Success',
          message: 'Created Successfully',
          type: 'success',
          duration: 2000
        })
        this.getList()
        this.listLoading = false
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj

      if(this.temp.status == 1){
        this.temp.status = '管理员'
      }else{
        this.temp.status = '普通用户'
      }

      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      if(this.temp.id.trim().length == 0
        || this.temp.deptId.trim().length == 0
        || this.temp.jobId.trim().length == 0
        || this.temp.name.trim().length == 0
        || this.temp.cardId.trim().length == 0
        || this.temp.address.trim().length == 0
        || this.temp.phone.trim().length == 0
        || this.temp.email.trim().length == 0
        || this.temp.sex.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }

      let data = {
        id:this.temp.id,
        username:this.temp.username,
        loginname:this.temp.loginname,
        status: this.temp.status == '管理员' ? 1 : 2 
      }
      this.listLoading = true
      updateEmp(data)
      .then(response => {
        this.dialogFormVisible = false
        //如果更新的是自己的权限,则进行重新登录!!1
        if(data.id == this.userInfo.id){
          removeUser()
          let userData = {
            username:this.userInfo.username,
            password:this.userInfo.password,

            id: this.temp.id,
            deptId: this.temp.deptId,
            jobId: this.temp.jobId,
            name: this.temp.name,
            cardId: this.temp.cardId,
            address: this.temp.address,
            postCode: this.temp.postCode,
            tel: this.temp.tel,
            phone: this.temp.phone,
            qqNum: this.temp.qqNum,
            email: this.temp.email,
            sex: this.temp.sex,
            party: this.temp.party,
            birthday: this.temp.birthday,
            race: this.temp.race,
            education: this.temp.education,
            speciality: this.temp.speciality,
            hobby: this.temp.hobby,
            remark: this.temp.remark,
            createDate: this.temp.createDate,
          }
          loginUserByPass(userData).then(res => {
            this.listLoading = false
            this.$store.dispatch('user/login', res.data)
            this.$router.go(0);
          })
        }else{
          this.$notify({
            title: 'Success',
            message: 'Update Successfully',
            type: 'success',
            duration: 2000
          })
          this.getList()
          this.listLoading = false
        }
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

</style>