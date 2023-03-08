import{au as B,a as v,dy as P,s as T,fy as F,f as D,aw as r,bQ as g,o as a,j as n,z as e,n as s,i as c,B as o,t as d,l as f,x as C}from"./index.21dbddea.js";import{A as h}from"./index.ba1296a1.js";import{D as U}from"./index.a37178f8.js";import{S as k}from"./index.903acead.js";import $ from"./CurrentPermissionMode.0fa980af.js";import{A as b}from"./index.9a0254d4.js";import{P as w}from"./index.262aa95b.js";import"./useFlexGapSupport.06ccabe5.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useSize.e955a44e.js";import"./eagerComputed.1ea2a8d9.js";import"./useWindowSizeFn.7490f562.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";import"./transButton.25f118c6.js";const N=v({components:{Alert:h,PageWrapper:w,Space:k,CurrentPermissionMode:$,Divider:U,Authority:b},setup(){const{changeRole:u,hasPermission:l}=P(),E=T();return{userStore:E,RoleEnum:F,isSuper:D(()=>E.getRoleList.includes(F.SUPER)),isTest:D(()=>E.getRoleList.includes(F.TEST)),changeRole:u,hasPermission:l}}}),V={class:"mt-4"};function L(u,l,E,M,W,j){const R=r("CurrentPermissionMode"),_=r("Alert"),t=r("a-button"),A=r("Space"),m=r("Divider"),i=r("Authority"),S=r("PageWrapper"),p=g("auth");return a(),n(S,{title:"\u524D\u7AEF\u6743\u9650\u6309\u94AE\u793A\u4F8B",contentBackground:"",contentClass:"p-4",content:"\u7531\u4E8E\u5237\u65B0\u7684\u65F6\u5019\u4F1A\u8BF7\u6C42\u7528\u6237\u4FE1\u606F\u63A5\u53E3\uFF0C\u4F1A\u6839\u636E\u63A5\u53E3\u91CD\u7F6E\u89D2\u8272\u4FE1\u606F\uFF0C\u6240\u4EE5\u5237\u65B0\u540E\u754C\u9762\u4F1A\u6062\u590D\u539F\u6837\uFF0C\u5982\u679C\u4E0D\u9700\u8981\uFF0C\u53EF\u4EE5\u6CE8\u91CA src/layout/default/index\u5185\u7684\u83B7\u53D6\u7528\u6237\u4FE1\u606F\u63A5\u53E3"},{default:e(()=>[s(R),c("p",null,[o(" \u5F53\u524D\u89D2\u8272: "),c("a",null,d(u.userStore.getRoleList),1)]),s(_,{class:"mt-4",type:"info",message:"\u70B9\u51FB\u540E\u8BF7\u67E5\u770B\u6309\u94AE\u53D8\u5316","show-icon":""}),c("div",V,[o(" \u6743\u9650\u5207\u6362(\u8BF7\u5148\u5207\u6362\u6743\u9650\u6A21\u5F0F\u4E3A\u524D\u7AEF\u89D2\u8272\u6743\u9650\u6A21\u5F0F): "),s(A,null,{default:e(()=>[s(t,{onClick:l[0]||(l[0]=y=>u.changeRole(u.RoleEnum.SUPER)),type:u.isSuper?"primary":"default"},{default:e(()=>[o(d(u.RoleEnum.SUPER),1)]),_:1},8,["type"]),s(t,{onClick:l[1]||(l[1]=y=>u.changeRole(u.RoleEnum.TEST)),type:u.isTest?"primary":"default"},{default:e(()=>[o(d(u.RoleEnum.TEST),1)]),_:1},8,["type"])]),_:1})]),s(m,null,{default:e(()=>[o("\u7EC4\u4EF6\u65B9\u5F0F\u5224\u65AD\u6743\u9650(\u6709\u9700\u8981\u53EF\u4EE5\u81EA\u884C\u5168\u5C40\u6CE8\u518C)")]),_:1}),s(i,{value:u.RoleEnum.SUPER},{default:e(()=>[s(t,{type:"primary",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709super\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})]),_:1},8,["value"]),s(i,{value:u.RoleEnum.TEST},{default:e(()=>[s(t,{color:"success",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709test\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})]),_:1},8,["value"]),s(i,{value:[u.RoleEnum.TEST,u.RoleEnum.SUPER]},{default:e(()=>[s(t,{color:"error",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709[test,super]\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})]),_:1},8,["value"]),s(m,null,{default:e(()=>[o("\u51FD\u6570\u65B9\u5F0F\u65B9\u5F0F\u5224\u65AD\u6743\u9650(\u9002\u7528\u4E8E\u51FD\u6570\u5185\u90E8\u8FC7\u6EE4)")]),_:1}),u.hasPermission(u.RoleEnum.SUPER)?(a(),n(t,{key:0,type:"primary",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709super\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})):f("",!0),u.hasPermission(u.RoleEnum.TEST)?(a(),n(t,{key:1,color:"success",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709test\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})):f("",!0),u.hasPermission([u.RoleEnum.TEST,u.RoleEnum.SUPER])?(a(),n(t,{key:2,color:"error",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709[test,super]\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})):f("",!0),s(m,null,{default:e(()=>[o("\u6307\u4EE4\u65B9\u5F0F\u65B9\u5F0F\u5224\u65AD\u6743\u9650(\u8BE5\u65B9\u5F0F\u4E0D\u80FD\u52A8\u6001\u4FEE\u6539\u6743\u9650.)")]),_:1}),C((a(),n(t,{type:"primary",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709super\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})),[[p,u.RoleEnum.SUPER]]),C((a(),n(t,{color:"success",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709test\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})),[[p,u.RoleEnum.TEST]]),C((a(),n(t,{color:"error",class:"mx-4"},{default:e(()=>[o(" \u62E5\u6709[test,super]\u89D2\u8272\u6743\u9650\u53EF\u89C1 ")]),_:1})),[[p,[u.RoleEnum.TEST,u.RoleEnum.SUPER]]])]),_:1})}var tu=B(N,[["render",L],["__scopeId","data-v-a5cca872"]]);export{tu as default};
