import{au as r,a as p,b as d,f as m,aw as l,o as c,h,i as u,t as f,n as g,ax as C,q as _,c as v}from"./index.21dbddea.js";import{S as y}from"./index.c91c606e.js";import{b}from"./index.62c60a6e.js";import"./index.f4811fee.js";import"./index.907316d5.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.a37178f8.js";import"./index.6d83fe47.js";import"./FullscreenOutlined.1b876098.js";import"./index.b337bc77.js";import"./useWindowSizeFn.7490f562.js";import"./useContentViewHeight.22c0cdcf.js";import"./uniqBy.45490986.js";import"./_baseIteratee.165cbc4f.js";import"./get.41fb1f2f.js";import"./index.6f5bf206.js";import"./useRefs.15808103.js";import"./PlusOutlined.dd44a3e9.js";import"./RedoOutlined.169c31ba.js";import"./index.9e0276f3.js";import"./lock.87b847fc.js";const w=p({name:"SwitchItem",components:{Switch:y},props:{event:{type:Number},disabled:{type:Boolean},title:{type:String},def:{type:Boolean}},setup(e){const{prefixCls:t}=d("setting-switch-item"),{t:n}=v(),o=m(()=>e.def?{checked:e.def}:{});function i(a){e.event&&b(e.event,a)}return{prefixCls:t,t:n,handleChange:i,getBindValue:o}}});function S(e,t,n,o,i,a){const s=l("Switch");return c(),h("div",{class:_(e.prefixCls)},[u("span",null,f(e.title),1),g(s,C(e.getBindValue,{onChange:e.handleChange,disabled:e.disabled,checkedChildren:e.t("layout.setting.on"),unCheckedChildren:e.t("layout.setting.off")}),null,16,["onChange","disabled","checkedChildren","unCheckedChildren"])],2)}var O=r(w,[["render",S],["__scopeId","data-v-440e72fd"]]);export{O as default};
