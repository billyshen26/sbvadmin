var I=Object.defineProperty,g=Object.defineProperties;var C=Object.getOwnPropertyDescriptors;var p=Object.getOwnPropertySymbols;var F=Object.prototype.hasOwnProperty,v=Object.prototype.propertyIsEnumerable;var s=(o,e,t)=>e in o?I(o,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):o[e]=t,a=(o,e)=>{for(var t in e||(e={}))F.call(e,t)&&s(o,t,e[t]);if(p)for(var t of p(e))v.call(e,t)&&s(o,t,e[t]);return o},n=(o,e)=>g(o,C(e));import{a as b,w as N,bc as S,au as k,aw as c,o as y,h as V,i as l,n as f,t as w,q as O,bl as $}from"./index.a0f4a53f.js";import B from"./FormNodeOperate.d4a34f42.js";import{u as D}from"./useFormDesignState.eb5822a3.js";import _ from"./index.1add0bdc.js";import"./index.05ba8a79.js";import"./isNumber.8890bb61.js";import"./formItemConfig.fe275348.js";import"./componentMap.532712b5.js";import"./index.c614ae28.js";import"./Checkbox.7a2e3c7b.js";import"./index.a4da4b30.js";import"./index.8681b128.js";import"./index.74f87750.js";import"./index.7392d982.js";import"./index.e3018fdb.js";import"./index.152abf5f.js";import"./index.acf588db.js";import"./useFormItem.51e35cd3.js";import"./RadioButtonGroup.0d384df5.js";import"./get.72cb776d.js";import"./index.112a9f37.js";import"./eagerComputed.f27ac635.js";import"./index.c34f9609.js";import"./_baseIteratee.7eef18d3.js";import"./DeleteOutlined.e19abfbb.js";import"./index.da68fb2b.js";import"./useRefs.727c25ab.js";import"./Form.1dc8a98e.js";import"./Col.82157996.js";import"./useFlexGapSupport.55bd0324.js";import"./useSize.bbc03f2d.js";import"./transButton.e15f313e.js";import"./index.49be5f7f.js";import"./index.73a625c6.js";import"./useWindowSizeFn.61bf8fec.js";import"./FullscreenOutlined.a503eb0e.js";import"./index.0b5da165.js";import"./index.98ddc2e5.js";import"./uuid.2b29000c.js";import"./download.56687849.js";import"./base64Conver.08b9f4ec.js";import"./index.38d55bea.js";import"./index.82b1ebe6.js";/* empty css              */import"./index.9fa96fb8.js";const q=b({name:"FormNode",components:{VFormItem:_,FormNodeOperate:B},props:{schema:{type:Object,required:!0}},setup(o){const{formConfig:e,formDesignMethods:t}=D(),r=N({}),m=()=>{t.handleSetSelectItem(o.schema)};return n(a({},S(r)),{handleSelectItem:m,formConfig:e})}}),M={class:"form-item-box"},j={class:"show-key-box"};function z(o,e,t,r,m,E){var i;const d=c("VFormItem"),h=c("FormNodeOperate");return y(),V("div",{class:O(["drag-move-box",{active:o.schema.key===((i=o.formConfig.currentItem)==null?void 0:i.key)}]),onClick:e[0]||(e[0]=$((...u)=>o.handleSelectItem&&o.handleSelectItem(...u),["stop"]))},[l("div",M,[f(d,{formConfig:o.formConfig,schema:o.schema},null,8,["formConfig","schema"])]),l("div",j,w(o.schema.label+(o.schema.field?"/"+o.schema.field:"")),1),f(h,{schema:o.schema,currentItem:o.formConfig.currentItem},null,8,["schema","currentItem"])],2)}var Do=k(q,[["render",z]]);export{Do as default};