import{B as n}from"./BasicForm.e2d5cc76.js";import{aw as u,a as l,cD as c,ay as e,o as d,j as C,z as i,n as p,bS as f,E as _}from"./index.26fcfc43.js";import{T as h}from"./index.ac76b54e.js";import{P as b}from"./index.44a6cc14.js";/* empty css              *//* empty css              */import"./index.68631c3d.js";import"./index.b325d19a.js";import"./Checkbox.2a1a359d.js";import"./index.dc14bff5.js";import"./index.53f940a3.js";import"./index.edc7a53e.js";import"./index.599cf005.js";import"./index.6b155408.js";import"./get.3a52d42a.js";import"./index.27b9c0ab.js";import"./eagerComputed.c053c9c2.js";import"./index.9028474e.js";import"./_baseIteratee.7cbe611d.js";import"./DeleteOutlined.849ecd53.js";import"./index.e58470bc.js";import"./useRefs.753293e1.js";import"./Form.a60d3742.js";import"./Col.ad062a60.js";import"./useFlexGapSupport.71c58254.js";import"./useSize.25d44e1a.js";import"./transButton.2853c7d5.js";import"./index.d4067d83.js";import"./index.2cf2b49a.js";import"./useWindowSizeFn.d178ecd3.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.c7154726.js";import"./index.915486e3.js";import"./uuid.2b29000c.js";import"./download.7b60a2b1.js";import"./base64Conver.08b9f4ec.js";import"./index.42baef15.js";import"./index.995e13ea.js";import"./uniqBy.1499beda.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";const B=[{field:"title",component:"Input",label:"title",defaultValue:"defaultValue",rules:[{required:!0}]},{field:"tinymce",component:"Input",label:"tinymce",defaultValue:"defaultValue",rules:[{required:!0}],render:({model:t,field:o})=>f(h,{value:t[o],onChange:r=>{t[o]=r}})}],g=l({components:{BasicForm:n,CollapseContainer:c,PageWrapper:b},setup(){const{createMessage:t}=_();return{schemas:B,handleSubmit:o=>{t.success("click search,values:"+JSON.stringify(o))}}}});function S(t,o,r,y,P,V){const a=e("BasicForm"),m=e("CollapseContainer"),s=e("PageWrapper");return d(),C(s,{title:"\u5BCC\u6587\u672C\u5D4C\u5165\u8868\u5355\u793A\u4F8B"},{default:i(()=>[p(m,{title:"\u5BCC\u6587\u672C\u8868\u5355"},{default:i(()=>[p(a,{labelWidth:100,schemas:t.schemas,actionColOptions:{span:24},baseColProps:{span:24},onSubmit:t.handleSubmit},null,8,["schemas","onSubmit"])]),_:1})]),_:1})}var ft=u(g,[["render",S]]);export{ft as default};