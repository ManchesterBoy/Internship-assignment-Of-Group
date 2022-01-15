<template>
  <div class="app-container">
    <div class="filter-container">
      <div class="filter-div">
        部门名：
        <el-input v-model="listQuery.title" 
          placeholder="输入部门名：" 
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

      <el-table-column label="部门名" min-width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
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

      <el-table-column label="详细信息" min-width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.remark }}</span>
        </template>
      </el-table-column>



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
        <el-form-item label="部门名" >
          <el-input v-model="temp.name" placeholder />
        </el-form-item>

        <el-form-item label="详细信息" >
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
import { fetchList , updataDept , addDept , deleteDept } from '@/api/api/dept'

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
        id: undefined,
        importance: 1,
        loginname: '',
        name: '',
        remark: '',
        status: '普通用户',
        password:''
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
        id: undefined,
        importance: 1,
        // loginname: '',
        name: '',
        remark: '',
        status: '普通用户',
        // password:''
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
      deleteDept(this.multipleSelection)
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
      if(this.temp.name.trim().length == 0
        || this.temp.remark.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }
      let data = {
        name:this.temp.name,
        remark:this.temp.remark,
        status: this.temp.status == '管理员' ? 1 : 2,
        password: this.temp.password
      }
      this.listLoading = true
      addDept(data)
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
      if(this.temp.name.trim().length == 0
        || this.temp.remark.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }

      let data = {
        id:this.temp.id,
        name:this.temp.name,
        // loginname:this.temp.loginname,
        remark:this.temp.remark,
        status: this.temp.status == '管理员' ? 1 : 2 
      }
      this.listLoading = true
      updataDept(data)
      .then(response => {
        this.dialogFormVisible = false
        //如果更新的是自己的权限,则进行重新登录!!1
        if(data.id == this.userInfo.id){
          removeUser()
          let userData = {
            name:this.userInfo.name,
            remark:this.userInfo.remark,
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