import{au as P,a as A,aY as C,aZ as I,v as y,bW as N,aw as s,o as c,j as V,z as t,n as r,B as b,i,h as f,F as _,az as k,t as g,l as v}from"./index.21dbddea.js";import{P as W}from"./index.262aa95b.js";import{T as B}from"./index.6f5bf206.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useSize.e955a44e.js";import"./eagerComputed.1ea2a8d9.js";import"./useWindowSizeFn.7490f562.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";import"./transButton.25f118c6.js";import"./useRefs.15808103.js";import"./PlusOutlined.dd44a3e9.js";const $=A({name:"AccountDetail",components:{PageWrapper:W,ATabs:B,ATabPane:B.TabPane},setup(){var a;const e=C(),n=I(),p=y((a=e.params)==null?void 0:a.id),u=y("detail"),{setTitle:m}=N();m("\u8BE6\u60C5\uFF1A\u7528\u6237"+p.value);function d(){n("/system/account")}return{userId:p,currentKey:u,goBack:d}}}),w={class:"pt-4 m-4 desc-wrap"};function D(e,n,p,u,m,d){const a=s("a-button"),l=s("a-tab-pane"),T=s("a-tabs"),K=s("PageWrapper");return c(),V(K,{title:"\u7528\u6237"+e.userId+"\u7684\u8D44\u6599",content:"\u8FD9\u662F\u7528\u6237\u8D44\u6599\u8BE6\u60C5\u9875\u9762\u3002\u672C\u9875\u9762\u4EC5\u7528\u4E8E\u6F14\u793A\u76F8\u540C\u8DEF\u7531\u5728tab\u4E2D\u6253\u5F00\u591A\u4E2A\u9875\u9762\u5E76\u4E14\u663E\u793A\u4E0D\u540C\u7684\u6570\u636E",contentBackground:"",onBack:e.goBack},{extra:t(()=>[r(a,{type:"primary",danger:""},{default:t(()=>[b(" \u7981\u7528\u8D26\u53F7 ")]),_:1}),r(a,{type:"primary"},{default:t(()=>[b(" \u4FEE\u6539\u5BC6\u7801 ")]),_:1})]),footer:t(()=>[r(T,{"default-active-key":"detail",activeKey:e.currentKey,"onUpdate:activeKey":n[0]||(n[0]=o=>e.currentKey=o)},{default:t(()=>[r(l,{key:"detail",tab:"\u7528\u6237\u8D44\u6599"}),r(l,{key:"logs",tab:"\u64CD\u4F5C\u65E5\u5FD7"})]),_:1},8,["activeKey"])]),default:t(()=>[i("div",w,[e.currentKey=="detail"?(c(),f(_,{key:0},k(10,o=>i("div",{key:o},"\u8FD9\u662F\u7528\u6237"+g(e.userId)+"\u8D44\u6599Tab",1)),64)):v("",!0),e.currentKey=="logs"?(c(),f(_,{key:1},k(10,o=>i("div",{key:o},"\u8FD9\u662F\u7528\u6237"+g(e.userId)+"\u64CD\u4F5C\u65E5\u5FD7Tab",1)),64)):v("",!0)])]),_:1},8,["title","onBack"])}var J=P($,[["render",D]]);export{J as default};
