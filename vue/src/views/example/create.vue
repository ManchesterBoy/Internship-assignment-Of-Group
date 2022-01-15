<template>
  <div class="app-container">
    <div class="filter-container">
      <div class="filter-div">
        标题:
        <el-input v-model="listQuery.title" 
          placeholder="输入标题" 
          style="width: 200px;" 
          class="filter-item"
          />
      </div>

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search 
      </el-button>

      <div v-if="userInfo.status == 1">
        <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-delete" @click="handleDelete">
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

      <el-table-column label="标题" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" min-width="200px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.createDate }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建人" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.userInf.username }}</span>
        </template>
      </el-table-column>

      <el-table-column label="描述" min-width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.remark }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" 
        align="center" 
        width="230" 
        class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" 
            v-if="userInfo.status == 1"
            size="mini" @click="handleUpdate(row)">
            Edit
          </el-button>
          <el-button type="primary" size="mini" @click="handleDown(row)">
            Down
            <!-- <a href="https://www.baidu.com/">Down</a> -->
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />


    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="标题" >
          <el-input v-model="temp.title" placeholder="输入标题" />
        </el-form-item>

        <el-form-item label="描述" >
          <el-input v-model="temp.remark" type="textarea" placeholder="请输入描述文件内容!"/>
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

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="updateData()">
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
import { fetchList , updataDocu , addDocu , deleteDocu , upload } from '@/api/api/upLoad'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'create.vue',
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
      fileList:[],
      importanceOptions: ['管理员', '普通用户'],
      statusOptions: ['管理员', '普通用户'],
      temp: {
        id: undefined,
        importance: 1,
        title: '',
        remark: '',
        userId: '',
        filename: ''
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
        data['title'] = this.listQuery.title
      }
      fetchList(data).then(response => {
        console.log(response)
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
        title: '',
        remark: '',
        userId: '',
        filename: ''
      }
    },
    handleDown(row){
      console.log(row)
      window.open(row.filename)
    },
    //删除信息
    handleDelete(){
      if(this.multipleSelection.length == 0){
        this.$message.error('请选择要删除的数据!')
        return
      }
      this.listLoading = true
      deleteDocu(this.multipleSelection)
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
      if(this.temp.username.trim().length == 0
        || this.temp.loginname.trim().length == 0
        || this.temp.password.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }
      let data = {
        username:this.temp.username,
        loginname:this.temp.loginname,
        status: this.temp.status == '管理员' ? 1 : 2,
        password: this.temp.password
      }
      this.listLoading = true
      addDocu(data)
      .then(response => {
        this.$notify({
          title: 'Success',
          message: 'Created Successfully',
          type: 'success',
          duration: 2000
        })
        this.getList()
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    changeFileList(file, fileList){
      this.fileList = fileList
    },
    updateData() {
      if(this.temp.title.trim().length == 0
        || this.temp.remark.trim().length == 0) {
        this.$message.error('信息不能为空!');
        return
      }
      this.listLoading = true
      if(this.fileList.length > 0){
        //  有提交新文件!!
        this.$refs.uploads.submit();
      }else{
        let data = {
          id:this.temp.id,
          title:this.temp.title,
          userId:this.userInfo.id,
          remark:this.temp.remark,
        }
        this.updataInfo(data)
      }
    },
    updataInfo(data){
      updataDocu(data)
      .then(response => {
        console.log(response)
        this.dialogFormVisible = false
        this.$notify({
          title: 'Success',
          message: 'Update Successfully',
          type: 'success',
          duration: 2000
        })
        this.getList()
        this.listLoading = false
      })
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，请取消之前文件，再进行添加!`);
    },
    uploadFile(param){
      let data = new FormData()
      data.append("file",param.file)
      let that = this
      upload(data)
      .then(res => {
        if(res.status != 200) return
        const filename = res.path
        let data = {
          id:this.temp.id,
          title:this.temp.title,
          userId:this.userInfo.id,
          remark:this.temp.remark,
          filename
        }
        that.updataInfo(data)
      })
    }
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

</style>