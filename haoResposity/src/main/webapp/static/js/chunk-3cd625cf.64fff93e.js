(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3cd625cf"],{"11f6":function(t,e,i){"use strict";i.d(e,"b",(function(){return s})),i.d(e,"a",(function(){return l}));var a=i("1da1"),n=(i("96cf"),i("b775"));function s(t){return r.apply(this,arguments)}function r(){return r=Object(a["a"])(regeneratorRuntime.mark((function t(e){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.abrupt("return",Object(n["a"])({url:"/userInf/loginByPass",method:"post",data:e}));case 1:case"end":return t.stop()}}),t)}))),r.apply(this,arguments)}function l(t){return o.apply(this,arguments)}function o(){return o=Object(a["a"])(regeneratorRuntime.mark((function t(e){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.abrupt("return",Object(n["a"])({url:"/userInf/byLoginname",method:"get",params:e}));case 1:case"end":return t.stop()}}),t)}))),o.apply(this,arguments)}},"1c18":function(t,e,i){},"333d":function(t,e,i){"use strict";var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[i("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},n=[];i("a9e3");Math.easeInOutQuad=function(t,e,i,a){return t/=a/2,t<1?i/2*t*t+e:(t--,-i/2*(t*(t-2)-1)+e)};var s=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function r(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function l(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function o(t,e,i){var a=l(),n=t-a,o=20,u=0;e="undefined"===typeof e?500:e;var c=function t(){u+=o;var l=Math.easeInOutQuad(u,a,n,e);r(l),u<e?s(t):i&&"function"===typeof i&&i()};c()}var u={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&o(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&o(0,800)}}},c=u,d=(i("e498"),i("2877")),p=Object(d["a"])(c,a,n,!1,null,"6af373ef",null);e["a"]=p.exports},"5fb3":function(t,e,i){"use strict";i.r(e);var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"app-container"},[i("div",{staticClass:"filter-container"},[i("div",{staticClass:"filter-div"},[t._v(" 职位名称 "),i("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"输入职位名称："},model:{value:t.listQuery.title,callback:function(e){t.$set(t.listQuery,"title",e)},expression:"listQuery.title"}})],1),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v(" Search ")]),1==t.userInfo.status?i("div",[i("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v(" Add ")]),i("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleDelete}},[t._v(" Delete ")])],1):t._e()],1),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:t.list,border:"",fit:"","highlight-current-row":""},on:{"selection-change":t.handleSelectionChange}},[1==t.userInfo.status?i("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}):t._e(),i("el-table-column",{attrs:{label:"职位名称","min-width":"200px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[i("span",[t._v(t._s(a.name))])]}}])}),i("el-table-column",{attrs:{label:"详细信息","min-width":"100px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[i("span",[t._v(t._s(a.remark))])]}}])}),1==t.userInfo.status?i("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;e.$index;return[i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.handleUpdate(a)}}},[t._v(" Edit ")])]}}],null,!1,576413475)}):t._e()],1),i("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.page,limit:t.listQuery.limit},on:{"update:page":function(e){return t.$set(t.listQuery,"page",e)},"update:limit":function(e){return t.$set(t.listQuery,"limit",e)},pagination:t.getList}}),i("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[i("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.temp,"label-position":"left","label-width":"70px"}},[i("el-form-item",{attrs:{label:"职位名称"}},[i("el-input",{attrs:{placeholder:""},model:{value:t.temp.name,callback:function(e){t.$set(t.temp,"name",e)},expression:"temp.name"}})],1),i("el-form-item",{attrs:{label:"详细信息"}},[i("el-input",{model:{value:t.temp.remark,callback:function(e){t.$set(t.temp,"remark",e)},expression:"temp.remark"}})],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v(" Cancel ")]),i("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updateData()}}},[t._v(" Confirm ")])],1)],1),i("el-dialog",{attrs:{visible:t.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(e){t.dialogPvVisible=e}}},[i("el-table",{staticStyle:{width:"100%"},attrs:{border:"",fit:"","highlight-current-row":""}},[i("el-table-column",{attrs:{prop:"key",label:"Channel"}}),i("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPvVisible=!1}}},[t._v("Confirm")])],1)],1)],1)},n=[],s=(i("498a"),i("b0c0"),i("11f6")),r=i("5f87"),l=i("b775");function o(t){return Object(l["a"])({url:"/jobInf",method:"get",params:t})}function u(t){return Object(l["a"])({url:"/jobInf",method:"put",data:t})}function c(t){return Object(l["a"])({url:"/jobInf",method:"post",data:t})}function d(t){return Object(l["a"])({url:"/jobInf",method:"delete",data:t})}var p=i("6724"),m=i("333d"),f={name:"ComplexTable",components:{Pagination:m["a"]},directives:{waves:p["a"]},data:function(){return{userInfo:this.$store.getters.userInfo,multipleSelection:[],list:null,total:0,listLoading:!0,listQuery:{page:1,limit:10,importance:void 0,title:void 0,type:void 0},importanceOptions:["管理员","普通用户"],statusOptions:["管理员","普通用户"],temp:{id:void 0,importance:1,loginname:"",username:"",status:"普通用户",password:""},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"Edit",create:"Create"},dialogPvVisible:!1}},created:function(){this.getList()},methods:{handleSelectionChange:function(t){var e=[];for(var i in t)e.push(t[i].id);this.multipleSelection=e},getList:function(){var t=this;this.listLoading=!0;var e={page:this.listQuery.page,size:this.listQuery.limit};void 0!=this.listQuery.title&&0!=this.listQuery.title.trim().length&&(e["name"]=this.listQuery.title),void 0!=this.listQuery.importance&&0!=this.listQuery.importance.length&&(e["status"]="管理员"==this.listQuery.importance?1:2),o(e).then((function(e){t.list=e.data,t.total=e.pageMax,t.listLoading=!1}))},handleFilter:function(){this.listQuery.page=1,this.getList()},resetTemp:function(){this.temp={id:void 0,importance:1,name:"",remark:"",status:"普通用户"}},handleCreate:function(){var t=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},handleDelete:function(){var t=this;0!=this.multipleSelection.length?(this.listLoading=!0,d(this.multipleSelection).then((function(e){t.$notify({title:"Success",message:"delete Successfully",type:"success",duration:2e3}),t.getList(),t.listLoading=!1}))):this.$message.error("请选择要删除的数据!")},createData:function(){var t=this;if(0!=this.temp.name.trim().length&&0!=this.temp.remark.trim().length){var e={name:this.temp.name,remark:this.temp.remark,status:"管理员"==this.temp.status?1:2,password:this.temp.password};this.listLoading=!0,c(e).then((function(e){t.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3}),t.getList(),t.listLoading=!1}))}else this.$message.error("信息不能为空!")},handleUpdate:function(t){var e=this;this.temp=Object.assign({},t),1==this.temp.status?this.temp.status="管理员":this.temp.status="普通用户",this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},updateData:function(){var t=this;if(0!=this.temp.name.trim().length&&0!=this.temp.remark.trim().length){var e={id:this.temp.id,name:this.temp.name,remark:this.temp.remark,status:"管理员"==this.temp.status?1:2};this.listLoading=!0,u(e).then((function(i){if(t.dialogFormVisible=!1,e.id==t.userInfo.id){Object(r["f"])();var a={name:t.userInfo.name,remark:t.userInfo.remark};Object(s["b"])(a).then((function(e){t.listLoading=!1,t.$store.dispatch("user/login",e.data),t.$router.go(0)}))}else t.$notify({title:"Success",message:"Update Successfully",type:"success",duration:2e3}),t.getList(),t.listLoading=!1}))}else this.$message.error("信息不能为空!")}}},h=f,g=(i("b8d9"),i("2877")),v=Object(g["a"])(h,a,n,!1,null,null,null);e["default"]=v.exports},6724:function(t,e,i){"use strict";i("8d41");var a="@@wavesContext";function n(t,e){function i(i){var a=Object.assign({},e.value),n=Object.assign({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},a),s=n.ele;if(s){s.style.position="relative",s.style.overflow="hidden";var r=s.getBoundingClientRect(),l=s.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":(l=document.createElement("span"),l.className="waves-ripple",l.style.height=l.style.width=Math.max(r.width,r.height)+"px",s.appendChild(l)),n.type){case"center":l.style.top=r.height/2-l.offsetHeight/2+"px",l.style.left=r.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(i.pageY-r.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(i.pageX-r.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=n.color,l.className="waves-ripple z-active",!1}}return t[a]?t[a].removeHandle=i:t[a]={removeHandle:i},i}var s={bind:function(t,e){t.addEventListener("click",n(t,e),!1)},update:function(t,e){t.removeEventListener("click",t[a].removeHandle,!1),t.addEventListener("click",n(t,e),!1)},unbind:function(t){t.removeEventListener("click",t[a].removeHandle,!1),t[a]=null,delete t[a]}},r=function(t){t.directive("waves",s)};window.Vue&&(window.waves=s,Vue.use(r)),s.install=r;e["a"]=s},"8d41":function(t,e,i){},b8d9:function(t,e,i){"use strict";i("cde5")},cde5:function(t,e,i){},e498:function(t,e,i){"use strict";i("1c18")}}]);