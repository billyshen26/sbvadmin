import{aw as m,a as l,dm as c,b as d,ay as _,o as i,h as r,F as f,aB as u,q as p,bm as k,n as h}from"./index.26fcfc43.js";import{b as v}from"./index.eb44ecb9.js";import"./index.979c70dc.js";import"./index.8ae645aa.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.68631c3d.js";import"./index.a4ddba4a.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.9ecf85ce.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./uniqBy.1499beda.js";import"./_baseIteratee.7cbe611d.js";import"./get.3a52d42a.js";import"./index.5c68a18c.js";import"./useRefs.753293e1.js";import"./PlusOutlined.15e93643.js";import"./RedoOutlined.e3ed3ea8.js";import"./index.5adf5b3f.js";import"./lock.c8969bdc.js";const C=l({name:"ThemeColorPicker",components:{CheckOutlined:c},props:{colorList:{type:Array,defualt:[]},event:{type:Number},def:{type:String}},setup(e){const{prefixCls:n}=d("setting-theme-picker");function o(s){e.event&&v(e.event,s)}return{prefixCls:n,handleClick:o}}}),b=["onClick"];function g(e,n,o,s,y,x){const a=_("CheckOutlined");return i(),r("div",{class:p(e.prefixCls)},[(i(!0),r(f,null,u(e.colorList||[],t=>(i(),r("span",{key:t,onClick:$=>e.handleClick(t),class:p([`${e.prefixCls}__item`,{[`${e.prefixCls}__item--active`]:e.def===t}]),style:k({background:t})},[h(a)],14,b))),128))],2)}var K=m(C,[["render",g]]);export{K as default};