(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{"713b":function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("q-layout",{attrs:{view:"hHh lpR fFf"}},[e("q-header",{staticClass:"bg-primary text-white",attrs:{"height-hint":"98"}},[e("q-toolbar",[e("q-toolbar-title",[e("q-avatar",[e("img",{attrs:{src:"https://cdn.quasar.dev/logo/svg/quasar-logo.svg"}})]),t._v("펜타폰\n      ")],1),t.isLogin?e("q-btn",{attrs:{icon:"logout",label:"로그아웃",stack:"",outline:"",color:"standard",size:"sm"},on:{click:t.loginToggle}}):e("q-btn",{attrs:{icon:"login",label:"로그인",stack:"",outline:"",color:"standard",size:"sm"},on:{click:t.loginToggle}})],1),e("q-tabs",{attrs:{dense:"",align:"left"}},[e("q-route-tab",{attrs:{to:"/main",label:"메인"}}),e("q-route-tab",{attrs:{to:"/login",label:"로그인"}}),e("q-route-tab",{attrs:{to:"/after",label:"로그인후"}})],1),e("q-tabs",{staticClass:"bg-white text-black",attrs:{dense:"",align:"left"}},[e("q-tab",{attrs:{label:"PAGE1"}}),e("q-tab",{attrs:{label:"PAGE2"}}),e("q-tab",{attrs:{label:"PAGE3"}}),e("q-tab",{attrs:{label:"PAGE4"}}),e("q-tab",{attrs:{label:"PAGE5"}})],1)],1),e("q-page-container",[e("router-view")],1),e("q-footer",{staticClass:"bg-grey-8 text-white"},[e("div",{staticClass:"q-gutter-y-md"},[e("q-tabs",{staticClass:"bg-grey-1",attrs:{dense:"",align:"justify"}},[e("q-tab",{staticClass:"text-orange",attrs:{name:"mails",icon:"mail",label:"Mails"}}),e("q-tab",{staticClass:"text-cyan",attrs:{name:"alarms",icon:"alarm",label:"Alarms"}}),e("q-tab",{staticClass:"text-red",attrs:{name:"movies",icon:"movie",label:"Movies"}})],1)],1)])],1)},l=[],o={name:"MainLayout",data(){return{}},methods:{loginToggle(){this.$store.commit("setAuth");let t="",a="";this.isLogin?(t="로그인 되었습니다.",a="positive"):(t="로그아웃 되었습니다.",a="negative",this.$router.push({path:"main"})),this.$q.notify({group:!1,color:a,message:t})}},computed:{isLogin:{get(){return this.$store.getters.isAuth}}}},i=o,r=e("2877"),n=e("4d5a"),b=e("e359"),c=e("65c6"),g=e("6ac5"),u=e("cb32"),q=e("9c40"),m=e("429b"),h=e("7867"),d=e("7460"),p=e("09e3"),v=e("7ff0"),f=e("eebe"),w=e.n(f),Q=Object(r["a"])(i,s,l,!1,null,null,null);a["default"]=Q.exports;w()(Q,"components",{QLayout:n["a"],QHeader:b["a"],QToolbar:c["a"],QToolbarTitle:g["a"],QAvatar:u["a"],QBtn:q["a"],QTabs:m["a"],QRouteTab:h["a"],QTab:d["a"],QPageContainer:p["a"],QFooter:v["a"]})}}]);