import{a as m,bG as p,f as c,dy as d,fz as l,au as _,aw as r,o as f,h as F,B as o,n as s,z as i,t as C}from"./index.21dbddea.js";import{D as M}from"./index.a37178f8.js";const A=m({name:"CurrentPermissionMode",components:{Divider:M},setup(){const e=p(),n=c(()=>e.getProjectConfig.permissionMode),{togglePermissionMode:t}=d();return{permissionMode:n,PermissionModeEnum:l,togglePermissionMode:t}}}),D={class:"mt-2"};function P(e,n,t,g,v,k){const u=r("a-button"),a=r("Divider");return f(),F("div",D,[o(" \u5F53\u524D\u6743\u9650\u6A21\u5F0F\uFF1A "),s(u,{type:"link"},{default:i(()=>[o(C(e.permissionMode===e.PermissionModeEnum.BACK?"\u540E\u53F0\u6743\u9650\u6A21\u5F0F":"\u524D\u7AEF\u89D2\u8272\u6743\u9650\u6A21\u5F0F"),1)]),_:1}),s(u,{class:"ml-4",onClick:e.togglePermissionMode,type:"primary"},{default:i(()=>[o(" \u5207\u6362\u6743\u9650\u6A21\u5F0F ")]),_:1},8,["onClick"]),s(a)])}var h=_(A,[["render",P]]);export{h as default};
