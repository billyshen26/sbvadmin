import{a,v as s,fm as m,N as _,o as c,h as f,aC as k,aF as C,aw as v,ay as i,j as x,z as r,n as O,i as g,t as h}from"./index.26fcfc43.js";import{P as y}from"./index.44a6cc14.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useSize.25d44e1a.js";import"./eagerComputed.c053c9c2.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./transButton.2853c7d5.js";const S=a({__name:"ClickOutSide",emits:["mounted","clickOutside"],setup(e,{emit:t}){const n=s(null);return m(n,()=>{t("clickOutside")}),_(()=>{t("mounted")}),(o,d)=>(c(),f("div",{ref_key:"wrap",ref:n},[k(o.$slots,"default")],512))}}),w=C(S);const B=a({components:{ClickOutSide:w,PageWrapper:y},setup(){const e=s("Click");function t(){e.value="Click Out Side"}function n(){e.value="Click Inner"}return{innerClick:n,handleClickOutside:t,text:e}}});function $(e,t,n,o,d,E){const l=i("ClickOutSide"),u=i("PageWrapper");return c(),x(u,{title:"\u70B9\u5185\u5916\u90E8\u89E6\u53D1\u4E8B\u4EF6"},{default:r(()=>[O(l,{onClickOutside:e.handleClickOutside,class:"flex justify-center"},{default:r(()=>[g("div",{onClick:t[0]||(t[0]=(...p)=>e.innerClick&&e.innerClick(...p)),class:"demo-box"},h(e.text),1)]),_:1},8,["onClickOutside"])]),_:1})}var q=v(B,[["render",$],["__scopeId","data-v-6c56edd4"]]);export{q as default};
