"use strict";(self["webpackChunkelectron_test"]=self["webpackChunkelectron_test"]||[]).push([[634],{3634:function(e,t,a){a.r(t),a.d(t,{default:function(){return c}});var i=function(){var e=this,t=e._self._c;return t("div",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{id:"selfHelp","element-loading-background":"#f8f8f8"}},[t("el-row",[t("el-row",[t("h2",[e._v("个人信息修改")]),t("el-divider")],1),t("el-row",{staticStyle:{"margin-bottom":"20px"}},[t("el-col",[t("el-upload",{attrs:{action:"#","show-file-list":!1,"auto-upload":!1,multiple:!1,"on-change":e.uploadFile,drag:"",accept:"image/jpg,image/jpeg,image/png"}},[t("div",[t("i",{staticClass:"el-icon-picture",staticStyle:{"margin-top":"40px","font-size":"40px",color:"#999a9c"}}),t("div",[e._v("上传图片")]),t("div",[e._v("格式为png、jpeg或jpg")])])])],1)],1),e.imageUrl?t("el-divider"):e._e(),t("el-row",[t("el-col",[e.imageUrl?t("i",{staticClass:"el-icon-circle-close deleteImg",on:{click:function(t){return t.stopPropagation(),e.handleRemove.apply(null,arguments)}}}):e._e(),e.imageUrl?t("el-image",{staticClass:"el-upload--picture-car",staticStyle:{width:"100px",height:"100px",margin:"10px 0"},attrs:{src:e.imageUrl,"preview-src-list":[e.imageUrl]}}):e._e()],1)],1)],1),e.imageUrl?t("el-divider"):e._e(),t("el-row",[t("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.temp,rules:e.rules,"label-width":"50px"}},[t("el-form-item",{attrs:{label:"账号",prop:"account"}},[t("el-input",{staticClass:"el-input-plus",attrs:{size:"mini",clearable:""},model:{value:e.temp.account,callback:function(t){e.$set(e.temp,"account",t)},expression:"temp.account"}})],1),t("el-form-item",{attrs:{label:"密码",prop:"password"}},[t("el-input",{staticClass:"el-input-plus",attrs:{size:"mini",clearable:""},model:{value:e.temp.password,callback:function(t){e.$set(e.temp,"password",t)},expression:"temp.password"}})],1),t("el-form-item",{attrs:{label:"电话",prop:"phone"}},[t("el-input",{staticClass:"el-input-plus",attrs:{size:"mini",clearable:""},model:{value:e.temp.phone,callback:function(t){e.$set(e.temp,"phone",t)},expression:"temp.phone"}})],1),t("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[t("el-input",{staticClass:"el-input-plus",attrs:{size:"mini",clearable:""},model:{value:e.temp.email,callback:function(t){e.$set(e.temp,"email",t)},expression:"temp.email"}})],1),t("el-form-item")],1)],1),t("el-row",[t("el-popconfirm",{attrs:{title:"确定要修改以下个人信息？","confirm-button-text":"确认","cancel-button-text":"取消"},on:{confirm:e.update}},[t("el-button",{staticClass:"el-icon-edit",attrs:{slot:"reference",size:"mini",type:"primary"},slot:"reference"},[e._v("修改")])],1),t("el-divider")],1)],1)},l=[],s=a(3822),r={name:"YSelfHelp",data(){return{loading:!0,temp:{account:"",password:"",phone:"",email:"",image:""},rules:{account:[{pattern:/^[A-Za-z0-9\u4e00-\u9fa5]{6,10}$/,message:"6到10位的数字、大小写字母、中文",trigger:"blur"}],password:[{pattern:/^[A-Za-z0-9]{6,12}$/,message:"6到12位的数字、大小写字母",trigger:"blur"}],phone:[{pattern:/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,message:"电话号码格式不符",trigger:"blur"}],email:[{type:"email",message:"电子邮箱格式不符",trigger:"blur"}]},imageUrl:""}},methods:{...(0,s.nv)("user",{updateUserInfo:"updateUserInfo"}),uploadFile(e){this.temp.image=e.raw,void 0!=window.createObjectURL?this.imageUrl=window.createObjectURL(e.raw):void 0!=window.URL?this.imageUrl=window.URL.createObjectURL(e.raw):void 0!=window.webkitURL&&(this.imageUrl=window.webkitURL.createObjectURL(e.raw)),console.log(this.imageUrl,"imageUrl")},handleRemove(){this.imageUrl=""},update(){this.$refs["ruleForm"].validate((e=>{if(!e)return this.$message({message:"请检查输入项是否符合要求",type:"warning"}),!1;{let e=new FormData;e.append("usrId",this.$store.state.user.userForm.usrId),e.append("usrAccount",this.temp.account),e.append("usrPwd",this.temp.password),e.append("usrEmail",this.temp.email),e.append("usrPhone",this.temp.phone),e.append("avatar",this.temp.image);const t={_this:this,data:e};this.updateUserInfo(t)}}))}},activated(){console.log("activated"),setTimeout((()=>{this.loading=!1}),1e3)},deactivated(){console.log("deactivated"),this.loading=!0}},o=r,n=a(1001),p=(0,n.Z)(o,i,l,!1,null,"7d900582",null),c=p.exports}}]);
//# sourceMappingURL=634.a1ae87bf.js.map