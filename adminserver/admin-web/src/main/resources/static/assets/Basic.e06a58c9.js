import{B as f}from"./TableImg.bc5f2ace.js";import"./componentMap.371f952a.js";import{getBasicColumns as B,getBasicData as v}from"./tableData.e32a6b31.js";import{au as b,a as h,v as i,aw as c,o as A,h as E,n as e,z as r,B as m,t as l}from"./index.8936adfc.js";import"./index.a031b534.js";import"./Checkbox.ef980fcb.js";import"./index.ea04e905.js";import"./index.992924f4.js";import"./eagerComputed.5918221c.js";import"./BasicForm.00e2f65d.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.49d1b3fd.js";import"./index.8ec43242.js";import"./index.fc0fbd70.js";import"./_baseIteratee.f05d7e29.js";import"./get.62ec95b2.js";import"./DeleteOutlined.8e231f8a.js";import"./index.bcf6ef30.js";import"./useRefs.215ca6c8.js";import"./Form.e91498a8.js";import"./Col.4fe65e36.js";import"./useFlexGapSupport.3a88a899.js";import"./useSize.205cd3d8.js";import"./index.b51f6c0b.js";import"./uniqBy.4d1cf5d8.js";import"./index.144a57bb.js";import"./useWindowSizeFn.c4d53914.js";import"./FullscreenOutlined.03730ed4.js";import"./index.0b5ddde1.js";import"./useForm.06b23df2.js";import"./RadioButtonGroup.80cfb485.js";import"./useFormItem.edd024b8.js";import"./index.894818cb.js";import"./index.467c9ef6.js";import"./index.a51b36c2.js";import"./useContentViewHeight.6afc3857.js";import"./ArrowLeftOutlined.398710a4.js";import"./index.01ed4ff8.js";import"./transButton.f5a81c82.js";import"./index.5bc6caf0.js";import"./index.aaf8b1a3.js";import"./index.61e0a1ab.js";import"./index.69870b0f.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.b6662317.js";import"./index.636a0484.js";import"./isNumber.5ce0e4d0.js";import"./fromPairs.84aabb58.js";import"./scrollTo.f466bdf4.js";import"./index.16dfc5a5.js";import"./index.e50b03d6.js";import"./index.3edac26d.js";import"./index.05b679d6.js";import"./download.09d4dc22.js";import"./base64Conver.08b9f4ec.js";import"./index.56da1a6c.js";import"./index.c6fbab17.js";import"./select.4edc5eb7.js";const k=h({components:{BasicTable:f},setup(){const o=i(!1),a=i(!1),p=i(!0),n=i(!0),u=i(!1);function d(){o.value=!o.value}function t(){p.value=!p.value}function s(){a.value=!0,setTimeout(()=>{a.value=!1,u.value={pageSize:20}},3e3)}function g(){n.value=!n.value}function C(F){}return{columns:B(),data:v(),canResize:o,loading:a,striped:p,border:n,toggleStriped:t,toggleCanResize:d,toggleLoading:s,toggleBorder:g,pagination:u,handleColumnChange:C}}}),y={class:"p-4"};function z(o,a,p,n,u,d){const t=c("a-button"),s=c("BasicTable");return A(),E("div",y,[e(s,{title:"\u57FA\u7840\u793A\u4F8B",titleHelpMessage:"\u6E29\u99A8\u63D0\u9192",columns:o.columns,dataSource:o.data,canResize:o.canResize,loading:o.loading,striped:o.striped,bordered:o.border,showTableSetting:"",pagination:o.pagination,onColumnsChange:o.handleColumnChange},{toolbar:r(()=>[e(t,{type:"primary",onClick:o.toggleCanResize},{default:r(()=>[m(l(o.canResize?"\u53D6\u6D88\u81EA\u9002\u5E94":"\u81EA\u9002\u5E94\u9AD8\u5EA6"),1)]),_:1},8,["onClick"]),e(t,{type:"primary",onClick:o.toggleBorder},{default:r(()=>[m(l(o.border?"\u9690\u85CF\u8FB9\u6846":"\u663E\u793A\u8FB9\u6846"),1)]),_:1},8,["onClick"]),e(t,{type:"primary",onClick:o.toggleLoading},{default:r(()=>[m(" \u5F00\u542Floading ")]),_:1},8,["onClick"]),e(t,{type:"primary",onClick:o.toggleStriped},{default:r(()=>[m(l(o.striped?"\u9690\u85CF\u6591\u9A6C\u7EB9":"\u663E\u793A\u6591\u9A6C\u7EB9"),1)]),_:1},8,["onClick"])]),_:1},8,["columns","dataSource","canResize","loading","striped","bordered","pagination","onColumnsChange"])])}var Lo=b(k,[["render",z]]);export{Lo as default};