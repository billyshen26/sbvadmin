var d=(e,o,p)=>new Promise((f,c)=>{var b=n=>{try{s(p.next(n))}catch(r){c(r)}},t=n=>{try{s(p.throw(n))}catch(r){c(r)}},s=n=>n.done?f(n.value):Promise.resolve(n.value).then(b,t);s((p=p.apply(e,o)).next())});import{au as k,a as _,cD as R,v as F,aw as u,o as z,j as V,z as l,n as a,B as m}from"./index.21dbddea.js";import{D as T}from"./index.907316d5.js";import{S as O}from"./index.903acead.js";import{B as W}from"./BasicForm.f15e10d2.js";import"./componentMap.89831d4d.js";import{u as h}from"./useForm.e0cbc007.js";import"./RadioButtonGroup.bc4b2238.js";import{P as L}from"./index.262aa95b.js";import{a as E}from"./cascader.e6dfe25c.js";import"./useFlexGapSupport.06ccabe5.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.ce9802a8.js";import"./index.a37178f8.js";import"./index.c3a78ee8.js";import"./_baseIteratee.165cbc4f.js";import"./get.41fb1f2f.js";import"./DeleteOutlined.1931f992.js";import"./index.94534871.js";import"./useRefs.15808103.js";import"./Form.b6c45920.js";import"./Col.2a58f9a8.js";import"./useSize.e955a44e.js";import"./index.5e62cd22.js";import"./uniqBy.45490986.js";import"./index.5d9418ce.js";import"./useWindowSizeFn.7490f562.js";import"./FullscreenOutlined.1b876098.js";import"./index.4a1de338.js";import"./index.82fe2da0.js";import"./Checkbox.b4e3675e.js";import"./index.17dda3d9.js";import"./index.63df3ac3.js";import"./index.193019b5.js";import"./index.ec76c75d.js";import"./index.c91c606e.js";import"./index.299e002f.js";import"./useFormItem.1145f6c8.js";import"./index.2ddb10b6.js";import"./eagerComputed.1ea2a8d9.js";import"./transButton.25f118c6.js";import"./index.ba1296a1.js";import"./index.44538731.js";import"./isNumber.9b40a4c7.js";import"./uuid.2b29000c.js";import"./download.68505b66.js";import"./base64Conver.08b9f4ec.js";import"./index.7b25a8ae.js";import"./index.ead49ec7.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";const G=[{value:"large",label:"large"},{value:"middle",label:"middle"},{value:"small",label:"small"},{value:"default",label:"defualt"}],v=[{field:"field1",component:"Input",label:"\u5B57\u6BB51",colProps:{span:8},componentProps:{placeholder:"\u81EA\u5B9A\u4E49placeholder",onChange:e=>{}}},{field:"field2",component:"Input",label:"\u5B57\u6BB52",colProps:{span:8}},{field:"field3",component:"DatePicker",label:"\u5B57\u6BB53",colProps:{span:8}},{field:"fieldTime",component:"RangePicker",label:"\u65F6\u95F4\u5B57\u6BB5",colProps:{span:8}},{field:"field4",component:"Select",label:"\u5B57\u6BB54",colProps:{span:8},componentProps:{options:[{label:"\u9009\u98791",value:"1",key:"1"},{label:"\u9009\u98792",value:"2",key:"2"}]}},{field:"field5",component:"CheckboxGroup",label:"\u5B57\u6BB55",colProps:{span:8},componentProps:{options:[{label:"\u9009\u98791",value:"1"},{label:"\u9009\u98792",value:"2"}]}},{field:"field7",component:"RadioGroup",label:"\u5B57\u6BB57",colProps:{span:8},componentProps:{options:[{label:"\u9009\u98791",value:"1"},{label:"\u9009\u98792",value:"2"}]}},{field:"field8",component:"ApiCascader",label:"\u8054\u52A8",colProps:{span:8},componentProps:{api:E,apiParamKey:"parentCode",dataField:"data",labelField:"name",valueField:"code",initFetchParams:{parentCode:""},isLeaf:e=>!(e.levelType<3)}},{field:"field9",component:"ApiCascader",label:"\u8054\u52A8\u56DE\u663E",colProps:{span:8},componentProps:{api:E,apiParamKey:"parentCode",dataField:"data",labelField:"name",valueField:"code",initFetchParams:{parentCode:""},isLeaf:e=>!(e.levelType<3)}}],I=[{field:"",component:"Divider",label:"\u57FA\u7840\u5C5E\u6027",colProps:{span:24},componentProps:{orientation:"center"}},{field:"labelWidth",defaultValue:120,component:"InputNumber",label:"labelWidth",colProps:{span:24},componentProps:{size:"small"}},{field:"size",defaultValue:"default",component:"Select",label:"size",colProps:{span:24},componentProps:{options:G,size:"small"}},{field:"disabled",defaultValue:!1,component:"Switch",label:"disabled",colProps:{span:24},componentProps:{size:"small"}},{field:"compact",defaultValue:!1,component:"Switch",label:"compact",colProps:{span:24},componentProps:{size:"small"}},{field:"",component:"Divider",label:"\u7F51\u683C\u5E03\u5C40",colProps:{span:24},componentProps:{orientation:"center"}},{field:"actionColOptions.span",component:"Slider",defaultValue:24,label:"span",colProps:{span:24},componentProps:{min:1,max:24}},{field:"",component:"Divider",label:"\u64CD\u4F5C\u6309\u94AE",colProps:{span:24},componentProps:{orientation:"center"}},{field:"showActionButtonGroup",defaultValue:!0,component:"Switch",label:"\u64CD\u4F5C\u6309\u94AE",colProps:{span:24},componentProps:({formActionType:e})=>({size:"small",onChange:o=>d(void 0,null,function*(){e.updateSchema([{field:"showResetButton",componentProps:{disabled:!o}},{field:"showSubmitButton",componentProps:{disabled:!o}}])})})},{field:"showResetButton",defaultValue:!0,component:"Switch",label:"\u91CD\u7F6E\u6309\u94AE",colProps:{span:24},componentProps:{size:"small"}},{field:"showSubmitButton",defaultValue:!0,component:"Switch",label:"\u63D0\u4EA4\u6309\u94AE",colProps:{span:24},componentProps:{size:"small"}},{field:"",component:"Divider",label:"\u5176\u4ED6\u4E8B\u4EF6",colProps:{span:24},componentProps:{orientation:"center"}},{field:"other",component:"Input",label:"",colProps:{span:24},colSlot:"other"}],N=_({components:{BasicForm:W,CollapseContainer:R,PageWrapper:L,Drawer:T,Space:O},setup(){const e=F(!1),o=F(),[p]=h({labelWidth:80,schemas:I,compact:!0,actionColOptions:{span:24},showActionButtonGroup:!1}),f=()=>d(this,null,function*(){var i;t({resetButtonOptions:{disabled:!1,text:"\u91CD\u7F6E"}}),t({submitButtonOptions:{disabled:!1,loading:!1}}),yield s({field9:[]}),yield(i=o.value)==null?void 0:i.resetFields()}),c=i=>d(this,null,function*(){yield t(i),e.value=!1}),[b,{setProps:t,setFieldsValue:s,updateSchema:n}]=h({labelWidth:120,schemas:v,actionColOptions:{span:24},fieldMapToTime:[["fieldTime",["startTime","endTime"],"YYYY-MM"]]});function r(){return d(this,null,function*(){const S=yield function(){return new Promise(y=>{setTimeout(()=>{y({field9:["430000","430100","430102"],province:"\u6E56\u5357\u7701",city:"\u957F\u6C99\u5E02",district:"\u5CB3\u9E93\u533A"})},1e3)})}(),{field9:w,province:g,city:D,district:A}=S;yield n({field:"field9",componentProps:{displayRenderArray:[g,D,A]}}),yield s({field9:w})})}return{register:b,schemas:v,handleSubmit:i=>{},setProps:t,handleLoad:r,visible:e,showDrawer:()=>{e.value=!0},settingFormRef:o,withClose:i=>{t(i),e.value=!1},onSettings:()=>{var i;(i=o.value)==null||i.submit()},resetSettings:f,registerSetting:p,handleSubmitSetting:c}}});function Y(e,o,p,f,c,b){const t=u("a-button"),s=u("Space"),n=u("BasicForm"),r=u("Drawer"),P=u("CollapseContainer"),C=u("PageWrapper");return z(),V(C,{title:"UseForm\u64CD\u4F5C\u793A\u4F8B"},{default:l(()=>[a(t,{class:"mb-4",type:"primary",onClick:e.showDrawer},{default:l(()=>[m(" \u66F4\u6539\u8BBE\u7F6E ")]),_:1},8,["onClick"]),a(r,{visible:e.visible,"onUpdate:visible":o[2]||(o[2]=B=>e.visible=B),title:"\u66F4\u6539\u8BBE\u7F6E",placement:"right"},{extra:l(()=>[a(s,null,{default:l(()=>[a(t,{onClick:e.resetSettings},{default:l(()=>[m("\u91CD\u7F6E\u8BBE\u7F6E")]),_:1},8,["onClick"]),a(t,{type:"primary",onClick:e.onSettings},{default:l(()=>[m("\u5E94\u7528")]),_:1},8,["onClick"])]),_:1})]),default:l(()=>[a(n,{ref:"settingFormRef",onRegister:e.registerSetting,onSubmit:e.handleSubmitSetting},{other:l(()=>[a(s,null,{default:l(()=>[a(t,{onClick:o[0]||(o[0]=()=>e.withClose({resetButtonOptions:{disabled:!0,text:"\u91CD\u7F6ENew"}}))},{default:l(()=>[m(" \u4FEE\u6539\u91CD\u7F6E\u6309\u94AE ")]),_:1}),a(t,{onClick:o[1]||(o[1]=()=>e.withClose({submitButtonOptions:{disabled:!0,loading:!0}}))},{default:l(()=>[m(" \u4FEE\u6539\u67E5\u8BE2\u6309\u94AE ")]),_:1}),a(t,{onClick:e.handleLoad,class:"mr-2"},{default:l(()=>[m(" \u8054\u52A8\u56DE\u663E ")]),_:1},8,["onClick"])]),_:1})]),_:1},8,["onRegister","onSubmit"])]),_:1},8,["visible"]),a(P,{title:"useForm\u793A\u4F8B"},{default:l(()=>[a(n,{onRegister:e.register,onSubmit:e.handleSubmit},null,8,["onRegister","onSubmit"])]),_:1})]),_:1})}var Me=k(N,[["render",Y]]);export{Me as default};
