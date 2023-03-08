var h=Object.defineProperty,D=Object.defineProperties;var _=Object.getOwnPropertyDescriptors;var b=Object.getOwnPropertySymbols;var M=Object.prototype.hasOwnProperty,y=Object.prototype.propertyIsEnumerable;var I=(o,t,e)=>t in o?h(o,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[t]=e,p=(o,t)=>{for(var e in t||(t={}))M.call(t,e)&&I(o,e,t[e]);if(b)for(var e of b(t))y.call(t,e)&&I(o,e,t[e]);return o},f=(o,t)=>D(o,_(t));var F=(o,t,e)=>new Promise((r,u)=>{var d=s=>{try{n(e.next(s))}catch(l){u(l)}},i=s=>{try{n(e.throw(s))}catch(l){u(l)}},n=s=>s.done?r(s.value):Promise.resolve(s.value).then(d,i);n((e=e.apply(o,t)).next())});import{B as P,a as A}from"./index.5d9418ce.js";import{B as k}from"./BasicForm.f15e10d2.js";import"./componentMap.89831d4d.js";import{u as x}from"./useForm.e0cbc007.js";import"./RadioButtonGroup.bc4b2238.js";import{au as S,a as $,v as B,k as c,f as w,aw as E,o as O,j as R,z as U,n as j,ax as L,fO as N,fP as T}from"./index.21dbddea.js";const Z=[{title:"ID",dataIndex:"id",sorter:!0},{title:"\u914D\u7F6E\u6807\u8BC6",dataIndex:"symbol"},{title:"\u914D\u7F6E\u503C",dataIndex:"value"},{title:"\u914D\u7F6E\u540D",dataIndex:"name"},{title:"\u63CF\u8FF0",dataIndex:"description"},{title:"\u6392\u5E8F",dataIndex:"sort"},{title:"\u521B\u5EFA\u65F6\u95F4",dataIndex:"createdAt"},{title:"\u4FEE\u6539\u65F6\u95F4",dataIndex:"updatedAt"}],ee=[{field:"id",label:"ID",component:"Input",colProps:{span:8}},{field:"createdAt",component:"RangePicker",label:"\u521B\u5EFA\u65E5\u671F",colProps:{span:8}}],q=[{field:"id",label:"ID",component:"InputNumber",dynamicDisabled:!0},{field:"symbol",label:"\u914D\u7F6E\u6807\u8BC6",component:"Input",required:!0},{field:"value",label:"\u914D\u7F6E\u503C",component:"Input"},{field:"name",label:"\u914D\u7F6E\u540D",component:"Input",required:!0},{field:"description",label:"\u63CF\u8FF0",component:"Input"},{field:"sort",label:"\u6392\u5E8F",component:"InputNumber"},{field:"createdAt",label:"\u521B\u5EFA\u65F6\u95F4",component:"DatePicker"},{field:"updatedAt",label:"\u4FEE\u6539\u65F6\u95F4",component:"DatePicker"}],z=$({name:"ConfigModal",components:{BasicModal:P,BasicForm:k},emits:["success","register"],setup(o,{emit:t}){const e=B(!0),r=B(""),[u,{resetFields:d,setFieldsValue:i,validate:n}]=x({labelWidth:100,baseColProps:{span:24},schemas:q,showActionButtonGroup:!1}),[s,{setModalProps:l,closeModal:g}]=A(a=>F(this,null,function*(){d(),l({confirmLoading:!1}),e.value=!!(a!=null&&a.isUpdate),c(e)&&(r.value=a.record.id,i(p({},a.record)))})),C=w(()=>c(e)?"\u7F16\u8F91":"\u65B0\u589E");function v(){return F(this,null,function*(){try{const a=yield n();l({confirmLoading:!0}),c(e)?N(a).then(m=>{}).catch(m=>{}).finally(()=>{g(),t("success",{isUpdate:c(e),values:f(p({},a),{id:r.value})})}):T(a).then(m=>{}).catch(m=>{}).finally(()=>{t("success",{isUpdate:c(e),values:f(p({},a),{id:r.value})})}),g()}finally{l({confirmLoading:!1})}})}return{registerModal:s,registerForm:u,getTitle:C,handleSubmit:v}}});function V(o,t,e,r,u,d){const i=E("BasicForm"),n=E("BasicModal");return O(),R(n,L(o.$attrs,{onRegister:o.registerModal,title:o.getTitle,onOk:o.handleSubmit}),{default:U(()=>[j(i,{onRegister:o.registerForm},null,8,["onRegister"])]),_:1},16,["onRegister","title","onOk"])}var G=S(z,[["render",V]]),oe=Object.freeze(Object.defineProperty({__proto__:null,default:G},Symbol.toStringTag,{value:"Module"}));export{G as C,oe as a,Z as c,ee as s};
