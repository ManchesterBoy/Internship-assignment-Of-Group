(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-edc1d49c"],{"11f6":function(e,t,a){"use strict";a.d(t,"b",(function(){return l})),a.d(t,"a",(function(){return r}));var i=a("1da1"),n=(a("96cf"),a("b775"));function l(e){return s.apply(this,arguments)}function s(){return s=Object(i["a"])(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(n["a"])({url:"/userInf/loginByPass",method:"post",data:t}));case 1:case"end":return e.stop()}}),e)}))),s.apply(this,arguments)}function r(e){return o.apply(this,arguments)}function o(){return o=Object(i["a"])(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(n["a"])({url:"/userInf/byLoginname",method:"get",params:t}));case 1:case"end":return e.stop()}}),e)}))),o.apply(this,arguments)}},"1c18":function(e,t,a){},"333d":function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},n=[];a("a9e3");Math.easeInOutQuad=function(e,t,a,i){return e/=i/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var l=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function s(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function r(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function o(e,t,a){var i=r(),n=e-i,o=20,p=0;t="undefined"===typeof t?500:t;var u=function e(){p+=o;var r=Math.easeInOutQuad(p,i,n,t);s(r),p<t?l(e):a&&"function"===typeof a&&a()};u()}var p={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&o(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&o(0,800)}}},u=p,c=(a("e498"),a("2877")),d=Object(c["a"])(u,i,n,!1,null,"6af373ef",null);t["a"]=d.exports},4199:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("div",{staticClass:"filter-div"},[e._v(" 员工名称 "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"输入员工名称："},model:{value:e.listQuery.title,callback:function(t){e.$set(e.listQuery,"title",t)},expression:"listQuery.title"}})],1),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(" Search ")]),1==e.userInfo.status?a("div",[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleCreate}},[e._v(" Add ")]),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleDelete}},[e._v(" Delete ")])],1):e._e()],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.list,border:"",fit:"","highlight-current-row":""},on:{"selection-change":e.handleSelectionChange}},[1==e.userInfo.status?a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}):e._e(),a("el-table-column",{attrs:{label:"姓名",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.name))])]}}])}),a("el-table-column",{attrs:{label:"性别",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[1==i.sex?a("el-tag",[e._v("男")]):e._e(),2==i.sex?a("el-tag",[e._v("女")]):e._e()]}}])}),a("el-table-column",{attrs:{label:"手机号码",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.phone))])]}}])}),a("el-table-column",{attrs:{label:"邮箱",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.email))])]}}])}),a("el-table-column",{attrs:{label:"职位",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[1==i.jobId?a("el-tag",[e._v("职员")]):e._e(),2==i.jobId?a("el-tag",[e._v("Java开发工程师")]):e._e(),3==i.jobId?a("el-tag",[e._v("Java中级开发工程师")]):e._e(),4==i.jobId?a("el-tag",[e._v("Java高级开发工程师")]):e._e(),6==i.jobId?a("el-tag",[e._v("架构师")]):e._e(),14==i.jobId?a("el-tag",[e._v("老板")]):e._e(),9==i.jobId?a("el-tag",[e._v("总经理")]):e._e(),8==i.jobId?a("el-tag",[e._v("经理")]):e._e()]}}])}),a("el-table-column",{attrs:{label:"学历",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.education))])]}}])}),a("el-table-column",{attrs:{label:"身份证号码",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.cardId))])]}}])}),a("el-table-column",{attrs:{label:"部门",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[1==i.deptId?a("el-tag",[e._v("技术部")]):e._e(),2==i.deptId?a("el-tag",[e._v("运营部")]):e._e(),3==i.deptId?a("el-tag",[e._v("财务部")]):e._e(),4==i.deptId?a("el-tag",[e._v("市场部")]):e._e(),7==i.deptId?a("el-tag",[e._v("摸鱼部")]):e._e(),8==i.deptId?a("el-tag",[e._v("无敌部")]):e._e()]}}])}),a("el-table-column",{attrs:{label:"联系地址",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.address))])]}}])}),a("el-table-column",{attrs:{label:"建档日期",width:"200px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("span",[e._v(e._s(i.createDate))])]}}])}),1==e.userInfo.status?a("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;t.$index;return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.handleUpdate(i)}}},[e._v(" Edit ")])]}}],null,!1,576413475)}):e._e()],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.page,limit:e.listQuery.limit},on:{"update:page":function(t){return e.$set(e.listQuery,"page",t)},"update:limit":function(t){return e.$set(e.listQuery,"limit",t)},pagination:e.getList}}),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.temp,"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"ID(必填)"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.temp.id,callback:function(t){e.$set(e.temp,"id",t)},expression:"temp.id"}})],1),a("el-form-item",{attrs:{label:"DEPT_ID(必填)"}},[a("el-input",{model:{value:e.temp.deptId,callback:function(t){e.$set(e.temp,"deptId",t)},expression:"temp.deptId"}})],1),a("el-form-item",{attrs:{label:"JOB_ID(必填)"}},[a("el-input",{model:{value:e.temp.jobId,callback:function(t){e.$set(e.temp,"jobId",t)},expression:"temp.jobId"}})],1),a("el-form-item",{attrs:{label:"NAME(必填)"}},[a("el-input",{model:{value:e.temp.name,callback:function(t){e.$set(e.temp,"name",t)},expression:"temp.name"}})],1),a("el-form-item",{attrs:{label:"CARD_ID(必填)"}},[a("el-input",{model:{value:e.temp.cardId,callback:function(t){e.$set(e.temp,"cardId",t)},expression:"temp.cardId"}})],1),a("el-form-item",{attrs:{label:"ADDRESS(必填)"}},[a("el-input",{model:{value:e.temp.address,callback:function(t){e.$set(e.temp,"address",t)},expression:"temp.address"}})],1),a("el-form-item",{attrs:{label:"POST_CODE"}},[a("el-input",{model:{value:e.temp.postCode,callback:function(t){e.$set(e.temp,"postCode",t)},expression:"temp.postCode"}})],1),a("el-form-item",{attrs:{label:"TEL"}},[a("el-input",{model:{value:e.temp.tel,callback:function(t){e.$set(e.temp,"tel",t)},expression:"temp.tel"}})],1),a("el-form-item",{attrs:{label:"PHONE(必填)"}},[a("el-input",{model:{value:e.temp.phone,callback:function(t){e.$set(e.temp,"phone",t)},expression:"temp.phone"}})],1),a("el-form-item",{attrs:{label:"QQ_NUM"}},[a("el-input",{model:{value:e.temp.qqNum,callback:function(t){e.$set(e.temp,"qqNum",t)},expression:"temp.qqNum"}})],1),a("el-form-item",{attrs:{label:"EMAIL(必填)"}},[a("el-input",{model:{value:e.temp.email,callback:function(t){e.$set(e.temp,"email",t)},expression:"temp.email"}})],1),a("el-form-item",{attrs:{label:"SEX(必填)"}},[a("el-input",{model:{value:e.temp.sex,callback:function(t){e.$set(e.temp,"sex",t)},expression:"temp.sex"}})],1),a("el-form-item",{attrs:{label:"PARTY"}},[a("el-input",{model:{value:e.temp.party,callback:function(t){e.$set(e.temp,"party",t)},expression:"temp.party"}})],1),a("el-form-item",{attrs:{label:"BIRTHDAY"}},[a("el-input",{model:{value:e.temp.birthday,callback:function(t){e.$set(e.temp,"birthday",t)},expression:"temp.birthday"}})],1),a("el-form-item",{attrs:{label:"SPECIALITY"}},[a("el-input",{model:{value:e.temp.speciality,callback:function(t){e.$set(e.temp,"speciality",t)},expression:"temp.speciality"}})],1),a("el-form-item",{attrs:{label:"HOBBY"}},[a("el-input",{model:{value:e.temp.hobby,callback:function(t){e.$set(e.temp,"hobby",t)},expression:"temp.hobby"}})],1),a("el-form-item",{attrs:{label:"REMARK"}},[a("el-input",{model:{value:e.temp.remark,callback:function(t){e.$set(e.temp,"remark",t)},expression:"temp.remark"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v(" Cancel ")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v(" Confirm ")])],1)],1),a("el-dialog",{attrs:{visible:e.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(t){e.dialogPvVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"key",label:"Channel"}}),a("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogPvVisible=!1}}},[e._v("Confirm")])],1)],1)],1)},n=[],l=(a("498a"),a("b0c0"),a("11f6")),s=a("5f87"),r=a("b775");function o(e){return Object(r["a"])({url:"/employeeInf",method:"get",params:e})}function p(e){return Object(r["a"])({url:"/employeeInf",method:"put",data:e})}function u(e){return Object(r["a"])({url:"/employeeInf",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/employeeInf",method:"delete",data:e})}var d=a("6724"),m=a("333d"),h={name:"ComplexTable",components:{Pagination:m["a"]},directives:{waves:d["a"]},data:function(){return{userInfo:this.$store.getters.userInfo,multipleSelection:[],list:null,total:0,listLoading:!0,listQuery:{page:1,limit:10,importance:void 0,title:void 0,type:void 0},importanceOptions:["管理员","普通用户"],statusOptions:["管理员","普通用户"],temp:{importance:1,loginname:"",username:"",status:"普通用户",password:"",id:"",deptId:"",jobId:"",name:"",cardId:"",address:"",postCode:"",tel:"",phone:"",qqNum:"",email:"",sex:"",party:"",birthday:"",race:"",education:"",speciality:"",hobby:"",remark:"",createDate:""},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"Edit",create:"Create"},dialogPvVisible:!1}},created:function(){this.getList()},methods:{handleSelectionChange:function(e){var t=[];for(var a in e)t.push(e[a].id);this.multipleSelection=t},getList:function(){var e=this;this.listLoading=!0;var t={page:this.listQuery.page,size:this.listQuery.limit};void 0!=this.listQuery.title&&0!=this.listQuery.title.trim().length&&(t["name"]=this.listQuery.title),void 0!=this.listQuery.importance&&0!=this.listQuery.importance.length&&(t["status"]="管理员"==this.listQuery.importance?1:2),o(t).then((function(t){e.list=t.data,e.total=t.pageMax,e.listLoading=!1}))},handleFilter:function(){this.listQuery.page=1,this.getList()},resetTemp:function(){this.temp={importance:1,loginname:"",username:"",status:"普通用户",password:"",id:"",deptId:"",jobId:"",name:"",cardId:"",address:"",postCode:"",tel:"",phone:"",qqNum:"",email:"",sex:"",party:"",birthday:"",race:"",education:"",speciality:"",hobby:"",remark:"",createDate:""}},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},handleDelete:function(){var e=this;0!=this.multipleSelection.length?(this.listLoading=!0,c(this.multipleSelection).then((function(t){e.$notify({title:"Success",message:"delete Successfully",type:"success",duration:2e3}),e.getList(),e.listLoading=!1}))):this.$message.error("请选择要删除的数据!")},createData:function(){var e=this;if(0!=this.temp.id.trim().length&&0!=this.temp.deptId.trim().length&&0!=this.temp.jobId.trim().length&&0!=this.temp.name.trim().length&&0!=this.temp.cardId.trim().length&&0!=this.temp.address.trim().length&&0!=this.temp.phone.trim().length&&0!=this.temp.email.trim().length&&0!=this.temp.sex.trim().length){var t={username:this.temp.username,loginname:this.temp.loginname,status:"管理员"==this.temp.status?1:2,password:this.temp.password,id:this.temp.id,deptId:this.temp.deptId,jobId:this.temp.jobId,name:this.temp.name,cardId:this.temp.cardId,address:this.temp.address,postCode:this.temp.postCode,tel:this.temp.tel,phone:this.temp.phone,qqNum:this.temp.qqNum,email:this.temp.email,sex:this.temp.sex,party:this.temp.party,birthday:this.temp.birthday,race:this.temp.race,education:this.temp.education,speciality:this.temp.speciality,hobby:this.temp.hobby,remark:this.temp.remark,createDate:this.temp.createDate};this.listLoading=!0,u(t).then((function(t){e.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3}),e.getList(),e.listLoading=!1}))}else this.$message.error("信息不能为空!")},handleUpdate:function(e){var t=this;this.temp=Object.assign({},e),1==this.temp.status?this.temp.status="管理员":this.temp.status="普通用户",this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},updateData:function(){var e=this;if(0!=this.temp.id.trim().length&&0!=this.temp.deptId.trim().length&&0!=this.temp.jobId.trim().length&&0!=this.temp.name.trim().length&&0!=this.temp.cardId.trim().length&&0!=this.temp.address.trim().length&&0!=this.temp.phone.trim().length&&0!=this.temp.email.trim().length&&0!=this.temp.sex.trim().length){var t={id:this.temp.id,username:this.temp.username,loginname:this.temp.loginname,status:"管理员"==this.temp.status?1:2};this.listLoading=!0,p(t).then((function(a){if(e.dialogFormVisible=!1,t.id==e.userInfo.id){Object(s["f"])();var i={username:e.userInfo.username,password:e.userInfo.password,id:e.temp.id,deptId:e.temp.deptId,jobId:e.temp.jobId,name:e.temp.name,cardId:e.temp.cardId,address:e.temp.address,postCode:e.temp.postCode,tel:e.temp.tel,phone:e.temp.phone,qqNum:e.temp.qqNum,email:e.temp.email,sex:e.temp.sex,party:e.temp.party,birthday:e.temp.birthday,race:e.temp.race,education:e.temp.education,speciality:e.temp.speciality,hobby:e.temp.hobby,remark:e.temp.remark,createDate:e.temp.createDate};Object(l["b"])(i).then((function(t){e.listLoading=!1,e.$store.dispatch("user/login",t.data),e.$router.go(0)}))}else e.$notify({title:"Success",message:"Update Successfully",type:"success",duration:2e3}),e.getList(),e.listLoading=!1}))}else this.$message.error("信息不能为空!")}}},f=h,g=(a("97ee"),a("2877")),b=Object(g["a"])(f,i,n,!1,null,null,null);t["default"]=b.exports},6724:function(e,t,a){"use strict";a("8d41");var i="@@wavesContext";function n(e,t){function a(a){var i=Object.assign({},t.value),n=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),l=n.ele;if(l){l.style.position="relative",l.style.overflow="hidden";var s=l.getBoundingClientRect(),r=l.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":(r=document.createElement("span"),r.className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",l.appendChild(r)),n.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=n.color,r.className="waves-ripple z-active",!1}}return e[i]?e[i].removeHandle=a:e[i]={removeHandle:a},a}var l={bind:function(e,t){e.addEventListener("click",n(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[i].removeHandle,!1),e.addEventListener("click",n(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[i].removeHandle,!1),e[i]=null,delete e[i]}},s=function(e){e.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(s)),l.install=s;t["a"]=l},"8d41":function(e,t,a){},"97ee":function(e,t,a){"use strict";a("d989")},d989:function(e,t,a){},e498:function(e,t,a){"use strict";a("1c18")}}]);