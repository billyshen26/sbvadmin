import{B as h}from"./TableImg.bc5f2ace.js";import{k as C}from"./componentMap.371f952a.js";import{u as w}from"./useTable.855c1ea0.js";import{a as E,au as B,aw as r,o as d,h as _,n as c,z as f,j as D,l as g,B as N}from"./index.8936adfc.js";import"./index.a031b534.js";import"./Checkbox.ef980fcb.js";import"./index.ea04e905.js";import"./index.992924f4.js";import"./eagerComputed.5918221c.js";import"./BasicForm.00e2f65d.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.49d1b3fd.js";import"./index.8ec43242.js";import"./index.fc0fbd70.js";import"./_baseIteratee.f05d7e29.js";import"./get.62ec95b2.js";import"./DeleteOutlined.8e231f8a.js";import"./index.bcf6ef30.js";import"./useRefs.215ca6c8.js";import"./Form.e91498a8.js";import"./Col.4fe65e36.js";import"./useFlexGapSupport.3a88a899.js";import"./useSize.205cd3d8.js";import"./index.b51f6c0b.js";import"./uniqBy.4d1cf5d8.js";import"./index.144a57bb.js";import"./useWindowSizeFn.c4d53914.js";import"./FullscreenOutlined.03730ed4.js";import"./index.0b5ddde1.js";import"./useForm.06b23df2.js";import"./RadioButtonGroup.80cfb485.js";import"./useFormItem.edd024b8.js";import"./index.894818cb.js";import"./index.467c9ef6.js";import"./index.a51b36c2.js";import"./useContentViewHeight.6afc3857.js";import"./ArrowLeftOutlined.398710a4.js";import"./index.01ed4ff8.js";import"./transButton.f5a81c82.js";import"./index.5bc6caf0.js";import"./index.aaf8b1a3.js";import"./index.61e0a1ab.js";import"./index.69870b0f.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.b6662317.js";import"./index.636a0484.js";import"./isNumber.5ce0e4d0.js";import"./fromPairs.84aabb58.js";import"./scrollTo.f466bdf4.js";import"./index.16dfc5a5.js";import"./index.e50b03d6.js";import"./index.3edac26d.js";import"./index.05b679d6.js";import"./download.09d4dc22.js";import"./base64Conver.08b9f4ec.js";import"./index.56da1a6c.js";import"./index.c6fbab17.js";const T=[{title:"\u6210\u5458\u59D3\u540D",dataIndex:"name",editRow:!0},{title:"\u5DE5\u53F7",dataIndex:"no",editRow:!0},{title:"\u6240\u5C5E\u90E8\u95E8",dataIndex:"dept",editRow:!0}],x=[{name:"John Brown",no:"00001",dept:"New York No. 1 Lake Park"},{name:"John Brown2",no:"00002",dept:"New York No. 2 Lake Park"},{name:"John Brown3",no:"00003",dept:"New York No. 3Lake Park"}],y=E({components:{BasicTable:h,TableAction:C},setup(){const[e,{getDataSource:i}]=w({columns:T,showIndexColumn:!1,dataSource:x,actionColumn:{width:160,title:"\u64CD\u4F5C",dataIndex:"action"},scroll:{y:"100%"},pagination:!1});function a(t){var o;(o=t.onEdit)==null||o.call(t,!0)}function l(t){var o;if((o=t.onEdit)==null||o.call(t,!1),t.isNew){const n=i(),b=n.findIndex(k=>k.key===t.key);n.splice(b,1)}}function u(t){var o;(o=t.onEdit)==null||o.call(t,!1,!0)}function s(t){}function p(){const t=i(),o={name:"",no:"",dept:"",editable:!0,isNew:!0,key:`${Date.now()}`};t.push(o)}function m(t,o){return t.editable?[{label:"\u4FDD\u5B58",onClick:u.bind(null,t,o)},{label:"\u53D6\u6D88",popConfirm:{title:"\u662F\u5426\u53D6\u6D88\u7F16\u8F91",confirm:l.bind(null,t,o)}}]:[{label:"\u7F16\u8F91",onClick:a.bind(null,t)},{label:"\u5220\u9664"}]}return{registerTable:e,handleEdit:a,createActions:m,handleAdd:p,getDataSource:i,handleEditChange:s}}});function F(e,i,a,l,u,s){const p=r("TableAction"),m=r("BasicTable"),t=r("a-button");return d(),_("div",null,[c(m,{onRegister:e.registerTable,onEditChange:e.handleEditChange},{bodyCell:f(({column:o,record:n})=>[o.key==="action"?(d(),D(p,{key:0,actions:e.createActions(n,o)},null,8,["actions"])):g("",!0)]),_:1},8,["onRegister","onEditChange"]),c(t,{block:"",class:"mt-5",type:"dashed",onClick:e.handleAdd},{default:f(()=>[N(" \u65B0\u589E\u6210\u5458 ")]),_:1},8,["onClick"])])}var Pt=B(y,[["render",F]]);export{Pt as default};