import{B as k}from"./TableImg.9e89c887.js";import"./BasicForm.e2d5cc76.js";import{u as B}from"./useTable.98436359.js";import{getBasicColumns as C,getFormConfig as b}from"./tableData.849d9def.js";import{aw as F,a as A,v,ay as r,o as i,j as g,z as t,n as m,h as d,i as E,t as S,F as T,l as w,B as a}from"./index.26fcfc43.js";import{A as V}from"./index.c7154726.js";import{A as D}from"./index.f2fc0df8.js";import{g as K}from"./system.2501c755.js";import"./index.b325d19a.js";import"./Checkbox.2a1a359d.js";import"./index.dc14bff5.js";import"./index.27b9c0ab.js";import"./eagerComputed.c053c9c2.js";import"./useForm.e26a883f.js";import"./index.44a6cc14.js";import"./index.2245f205.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./useSize.25d44e1a.js";import"./transButton.2853c7d5.js";import"./index.edc7a53e.js";import"./index.599cf005.js";import"./index.53f940a3.js";import"./uuid.2b29000c.js";import"./index.9028474e.js";import"./_baseIteratee.7cbe611d.js";import"./get.3a52d42a.js";import"./DeleteOutlined.849ecd53.js";import"./index.e58470bc.js";import"./useRefs.753293e1.js";import"./Form.a60d3742.js";import"./Col.ad062a60.js";import"./useFlexGapSupport.71c58254.js";import"./index.2cf2b49a.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.68631c3d.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.e3ed3ea8.js";import"./index.915486e3.js";import"./fromPairs.84aabb58.js";import"./scrollTo.15c9e589.js";import"./index.2a41d039.js";/* empty css              *//* empty css              */import"./index.6b155408.js";import"./index.d4067d83.js";import"./download.7b60a2b1.js";import"./base64Conver.08b9f4ec.js";import"./index.42baef15.js";import"./index.995e13ea.js";import"./uniqBy.1499beda.js";const $=A({components:{BasicTable:k,AAlert:V,Avatar:D},setup(){const o=v([]),[e,{getForm:n}]=B({title:"\u7528\u6237\u7BA1\u7406",api:K,columns:C(),useSearchForm:!0,formConfig:b(),showTableSetting:!0,tableSetting:{fullScreen:!0},showIndexColumn:!1,rowKey:"id"});function p(){n().getFieldsValue()}function u(s){o.value=s}return{registerTable:e,getFormValues:p,checkedKeys:o,onSelectChange:u}}}),N=a(" custom-slot "),z=a("\u6E05\u7A7A"),L={key:1},R=a("\u83B7\u53D6\u8868\u5355\u6570\u636E");function U(o,e,n,p,u,s){const l=r("a-button"),f=r("a-alert"),h=r("Avatar"),_=r("BasicTable");return i(),g(_,{onRegister:o.registerTable,rowSelection:{type:"checkbox",selectedRowKeys:o.checkedKeys,onChange:o.onSelectChange}},{"form-custom":t(()=>[N]),headerTop:t(()=>[m(f,{type:"info","show-icon":""},{message:t(()=>[o.checkedKeys.length>0?(i(),d(T,{key:0},[E("span",null,"\u5DF2\u9009\u4E2D"+S(o.checkedKeys.length)+"\u6761\u8BB0\u5F55(\u53EF\u8DE8\u9875)",1),m(l,{type:"link",onClick:e[0]||(e[0]=c=>o.checkedKeys=[]),size:"small"},{default:t(()=>[z]),_:1})],64)):(i(),d("span",L,"\u672A\u9009\u4E2D\u4EFB\u4F55\u9879\u76EE"))]),_:1})]),bodyCell:t(({column:c,record:y})=>[c.key==="avatar"?(i(),g(h,{key:0,size:60,src:y.avatar},null,8,["src"])):w("",!0)]),toolbar:t(()=>[m(l,{type:"primary",onClick:o.getFormValues},{default:t(()=>[R]),_:1},8,["onClick"])]),_:1},8,["onRegister","rowSelection"])}var jo=F($,[["render",U]]);export{jo as default};
