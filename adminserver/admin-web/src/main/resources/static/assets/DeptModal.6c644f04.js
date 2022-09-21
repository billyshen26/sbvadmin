var v=Object.defineProperty;var p=Object.getOwnPropertySymbols;var _=Object.prototype.hasOwnProperty,M=Object.prototype.propertyIsEnumerable;var m=(t,o,e)=>o in t?v(t,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):t[o]=e,f=(t,o)=>{for(var e in o||(o={}))_.call(o,e)&&m(t,e,o[e]);if(p)for(var e of p(o))M.call(o,e)&&m(t,e,o[e]);return t};var d=(t,o,e)=>new Promise((r,s)=>{var c=a=>{try{u(e.next(a))}catch(n){s(n)}},i=a=>{try{u(e.throw(a))}catch(n){s(n)}},u=a=>a.done?r(a.value):Promise.resolve(a.value).then(c,i);u((e=e.apply(t,o)).next())});import{B as D,a as P}from"./index.2cf2b49a.js";import{B as C}from"./BasicForm.e2d5cc76.js";import{u as I}from"./useForm.e26a883f.js";import{bS as S,a as w,v as y,k as b,f as N,aw as k,ay as g,o as T,j as x,z as R,n as $,az as A}from"./index.26fcfc43.js";import{T as q}from"./index.53f940a3.js";import{c as L}from"./system.fcb28edb.js";const X=[{title:"\u90E8\u95E8\u540D\u79F0",dataIndex:"deptName",width:160,align:"left"},{title:"\u6392\u5E8F",dataIndex:"orderNo",width:50},{title:"\u72B6\u6001",dataIndex:"status",width:80,customRender:({record:t})=>{const e=~~t.status===0,r=e?"green":"red",s=e?"\u542F\u7528":"\u505C\u7528";return S(q,{color:r},()=>s)}},{title:"\u521B\u5EFA\u65F6\u95F4",dataIndex:"createTime",width:180},{title:"\u5907\u6CE8",dataIndex:"remark"}],Y=[{field:"deptName",label:"\u90E8\u95E8\u540D\u79F0",component:"Input",colProps:{span:8}},{field:"status",label:"\u72B6\u6001",component:"Select",componentProps:{options:[{label:"\u542F\u7528",value:"0"},{label:"\u505C\u7528",value:"1"}]},colProps:{span:8}}],O=[{field:"deptName",label:"\u90E8\u95E8\u540D\u79F0",component:"Input",required:!0},{field:"parentDept",label:"\u4E0A\u7EA7\u90E8\u95E8",component:"TreeSelect",componentProps:{fieldNames:{label:"deptName",key:"id",value:"id"},getPopupContainer:()=>document.body},required:!0},{field:"orderNo",label:"\u6392\u5E8F",component:"InputNumber",required:!0},{field:"status",label:"\u72B6\u6001",component:"RadioButtonGroup",defaultValue:"0",componentProps:{options:[{label:"\u542F\u7528",value:"0"},{label:"\u505C\u7528",value:"1"}]},required:!0},{label:"\u5907\u6CE8",field:"remark",component:"InputTextArea"}],j=w({name:"DeptModal",components:{BasicModal:D,BasicForm:C},emits:["success","register"],setup(t,{emit:o}){const e=y(!0),[r,{resetFields:s,setFieldsValue:c,updateSchema:i,validate:u}]=I({labelWidth:100,baseColProps:{span:24},schemas:O,showActionButtonGroup:!1}),[a,{setModalProps:n,closeModal:F}]=P(l=>d(this,null,function*(){s(),n({confirmLoading:!1}),e.value=!!(l!=null&&l.isUpdate),b(e)&&c(f({},l.record));const h=yield L();i({field:"parentDept",componentProps:{treeData:h}})})),E=N(()=>b(e)?"\u7F16\u8F91\u90E8\u95E8":"\u65B0\u589E\u90E8\u95E8");function B(){return d(this,null,function*(){try{const l=yield u();n({confirmLoading:!0}),F(),o("success")}finally{n({confirmLoading:!1})}})}return{registerModal:a,registerForm:r,getTitle:E,handleSubmit:B}}});function z(t,o,e,r,s,c){const i=g("BasicForm"),u=g("BasicModal");return T(),x(u,A(t.$attrs,{onRegister:t.registerModal,title:t.getTitle,onOk:t.handleSubmit}),{default:R(()=>[$(i,{onRegister:t.registerForm},null,8,["onRegister"])]),_:1},16,["onRegister","title","onOk"])}var V=k(j,[["render",z]]),Z=Object.freeze(Object.defineProperty({__proto__:null,default:V},Symbol.toStringTag,{value:"Module"}));export{V as D,Z as a,X as c,Y as s};
