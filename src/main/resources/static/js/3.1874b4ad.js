(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[3],{a5f5:function(e,i,t){"use strict";t.r(i);var n=function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div")},r=[],s={name:"SocialLogin",props:{service:{type:String,required:!0},id:{type:String,required:!0},email:{type:String,default:""},name:{type:String,default:""}},mounted(){this.$store.commit("setNewSocialUser",{service:this.service,id:this.id,email:this.email,name:this.name}),this.$router.push({path:"/signup"})}},a=s,u=t("2877"),l=Object(u["a"])(a,n,r,!1,null,null,null);i["default"]=l.exports}}]);