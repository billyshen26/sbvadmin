import{B as h}from"./TableImg.292e04d9.js";import{k as C}from"./componentMap.89831d4d.js";import{u as w}from"./useTable.f07faccc.js";import{a as E,au as B,aw as r,o as d,h as _,n as c,z as f,j as D,l as g,B as N}from"./index.21dbddea.js";import"./index.82fe2da0.js";import"./Checkbox.b4e3675e.js";import"./index.17dda3d9.js";import"./index.2ddb10b6.js";import"./eagerComputed.1ea2a8d9.js";import"./BasicForm.f15e10d2.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.ce9802a8.js";import"./index.a37178f8.js";import"./index.c3a78ee8.js";import"./_baseIteratee.165cbc4f.js";import"./get.41fb1f2f.js";import"./DeleteOutlined.1931f992.js";import"./index.94534871.js";import"./useRefs.15808103.js";import"./Form.b6c45920.js";import"./Col.2a58f9a8.js";import"./useFlexGapSupport.06ccabe5.js";import"./useSize.e955a44e.js";import"./index.5e62cd22.js";import"./uniqBy.45490986.js";import"./index.5d9418ce.js";import"./useWindowSizeFn.7490f562.js";import"./FullscreenOutlined.1b876098.js";import"./index.4a1de338.js";import"./useForm.e0cbc007.js";import"./RadioButtonGroup.bc4b2238.js";import"./useFormItem.1145f6c8.js";import"./index.262aa95b.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";import"./transButton.25f118c6.js";import"./index.ec76c75d.js";import"./index.c91c606e.js";import"./index.193019b5.js";import"./index.63df3ac3.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.169c31ba.js";import"./index.44538731.js";import"./isNumber.9b40a4c7.js";import"./fromPairs.84aabb58.js";import"./scrollTo.667abe92.js";import"./index.8d01bfec.js";import"./index.299e002f.js";import"./index.903acead.js";import"./index.ba1296a1.js";import"./download.68505b66.js";import"./base64Conver.08b9f4ec.js";import"./index.7b25a8ae.js";import"./index.ead49ec7.js";const T=[{title:"\u6210\u5458\u59D3\u540D",dataIndex:"name",editRow:!0},{title:"\u5DE5\u53F7",dataIndex:"no",editRow:!0},{title:"\u6240\u5C5E\u90E8\u95E8",dataIndex:"dept",editRow:!0}],x=[{name:"John Brown",no:"00001",dept:"New York No. 1 Lake Park"},{name:"John Brown2",no:"00002",dept:"New York No. 2 Lake Park"},{name:"John Brown3",no:"00003",dept:"New York No. 3Lake Park"}],y=E({components:{BasicTable:h,TableAction:C},setup(){const[e,{getDataSource:i}]=w({columns:T,showIndexColumn:!1,dataSource:x,actionColumn:{width:160,title:"\u64CD\u4F5C",dataIndex:"action"},scroll:{y:"100%"},pagination:!1});function a(t){var o;(o=t.onEdit)==null||o.call(t,!0)}function l(t){var o;if((o=t.onEdit)==null||o.call(t,!1),t.isNew){const n=i(),b=n.findIndex(k=>k.key===t.key);n.splice(b,1)}}function u(t){var o;(o=t.onEdit)==null||o.call(t,!1,!0)}function s(t){}function p(){const t=i(),o={name:"",no:"",dept:"",editable:!0,isNew:!0,key:`${Date.now()}`};t.push(o)}function m(t,o){return t.editable?[{label:"\u4FDD\u5B58",onClick:u.bind(null,t,o)},{label:"\u53D6\u6D88",popConfirm:{title:"\u662F\u5426\u53D6\u6D88\u7F16\u8F91",confirm:l.bind(null,t,o)}}]:[{label:"\u7F16\u8F91",onClick:a.bind(null,t)},{label:"\u5220\u9664"}]}return{registerTable:e,handleEdit:a,createActions:m,handleAdd:p,getDataSource:i,handleEditChange:s}}});function F(e,i,a,l,u,s){const p=r("TableAction"),m=r("BasicTable"),t=r("a-button");return d(),_("div",null,[c(m,{onRegister:e.registerTable,onEditChange:e.handleEditChange},{bodyCell:f(({column:o,record:n})=>[o.key==="action"?(d(),D(p,{key:0,actions:e.createActions(n,o)},null,8,["actions"])):g("",!0)]),_:1},8,["onRegister","onEditChange"]),c(t,{block:"",class:"mt-5",type:"dashed",onClick:e.handleAdd},{default:f(()=>[N(" \u65B0\u589E\u6210\u5458 ")]),_:1},8,["onClick"])])}var Pt=B(y,[["render",F]]);export{Pt as default};
