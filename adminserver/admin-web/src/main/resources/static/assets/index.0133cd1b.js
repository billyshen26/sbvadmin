import{k as c,B as l}from"./BasicForm.e2d5cc76.js";import{aw as d,a as g,ay as r,o as f,j as B,z as F,n as t,E as _}from"./index.26fcfc43.js";import{u as A}from"./useForm.e26a883f.js";import{P as C}from"./index.44a6cc14.js";import{A as a}from"./index.c7154726.js";import{u as m}from"./upload.b19629a7.js";/* empty css              *//* empty css              */import"./index.68631c3d.js";import"./index.b325d19a.js";import"./Checkbox.2a1a359d.js";import"./index.dc14bff5.js";import"./index.53f940a3.js";import"./index.edc7a53e.js";import"./index.599cf005.js";import"./index.6b155408.js";import"./get.3a52d42a.js";import"./index.27b9c0ab.js";import"./eagerComputed.c053c9c2.js";import"./index.9028474e.js";import"./_baseIteratee.7cbe611d.js";import"./DeleteOutlined.849ecd53.js";import"./index.e58470bc.js";import"./useRefs.753293e1.js";import"./Form.a60d3742.js";import"./Col.ad062a60.js";import"./useFlexGapSupport.71c58254.js";import"./useSize.25d44e1a.js";import"./transButton.2853c7d5.js";import"./index.d4067d83.js";import"./index.2cf2b49a.js";import"./useWindowSizeFn.d178ecd3.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.915486e3.js";import"./uuid.2b29000c.js";import"./download.7b60a2b1.js";import"./base64Conver.08b9f4ec.js";import"./index.42baef15.js";import"./index.995e13ea.js";import"./uniqBy.1499beda.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";const h=[{field:"field1",component:"Upload",label:"\u5B57\u6BB51",colProps:{span:8},rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u4E0A\u4F20\u6587\u4EF6"}],componentProps:{api:m}}],E=g({components:{BasicUpload:c,BasicForm:l,PageWrapper:C,[a.name]:a},setup(){const{createMessage:o}=_(),[e]=A({labelWidth:120,schemas:h,actionColOptions:{span:16}});return{handleChange:p=>{o.info(`\u5DF2\u4E0A\u4F20\u6587\u4EF6${JSON.stringify(p)}`)},uploadApi:m,register:e}}});function P(o,e,p,$,x,y){const i=r("a-alert"),s=r("BasicUpload"),n=r("BasicForm"),u=r("PageWrapper");return f(),B(u,{title:"\u4E0A\u4F20\u7EC4\u4EF6\u793A\u4F8B"},{default:F(()=>[t(i,{message:"\u57FA\u7840\u793A\u4F8B"}),t(s,{maxSize:20,maxNumber:10,onChange:o.handleChange,api:o.uploadApi,class:"my-5",accept:["image/*"]},null,8,["onChange","api"]),t(i,{message:"\u5D4C\u5165\u8868\u5355,\u52A0\u5165\u8868\u5355\u6821\u9A8C"}),t(n,{onRegister:o.register,class:"my-5"},null,8,["onRegister"])]),_:1})}var Ao=d(E,[["render",P]]);export{Ao as default};
