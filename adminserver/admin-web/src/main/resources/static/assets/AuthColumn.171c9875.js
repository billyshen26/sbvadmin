import{B as s}from"./TableImg.bc5f2ace.js";import{k as d}from"./componentMap.371f952a.js";import{u as c}from"./useTable.855c1ea0.js";import{d as F}from"./table.0883fc74.js";import{au as h,a as b,aw as n,o as a,h as f,n as B,z as A,j as C,l as w}from"./index.8936adfc.js";import"./index.a031b534.js";import"./Checkbox.ef980fcb.js";import"./index.ea04e905.js";import"./index.992924f4.js";import"./eagerComputed.5918221c.js";import"./BasicForm.00e2f65d.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.49d1b3fd.js";import"./index.8ec43242.js";import"./index.fc0fbd70.js";import"./_baseIteratee.f05d7e29.js";import"./get.62ec95b2.js";import"./DeleteOutlined.8e231f8a.js";import"./index.bcf6ef30.js";import"./useRefs.215ca6c8.js";import"./Form.e91498a8.js";import"./Col.4fe65e36.js";import"./useFlexGapSupport.3a88a899.js";import"./useSize.205cd3d8.js";import"./index.b51f6c0b.js";import"./uniqBy.4d1cf5d8.js";import"./index.144a57bb.js";import"./useWindowSizeFn.c4d53914.js";import"./FullscreenOutlined.03730ed4.js";import"./index.0b5ddde1.js";import"./useForm.06b23df2.js";import"./RadioButtonGroup.80cfb485.js";import"./useFormItem.edd024b8.js";import"./index.894818cb.js";import"./index.467c9ef6.js";import"./index.a51b36c2.js";import"./useContentViewHeight.6afc3857.js";import"./ArrowLeftOutlined.398710a4.js";import"./index.01ed4ff8.js";import"./transButton.f5a81c82.js";import"./index.5bc6caf0.js";import"./index.aaf8b1a3.js";import"./index.61e0a1ab.js";import"./index.69870b0f.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.b6662317.js";import"./index.636a0484.js";import"./isNumber.5ce0e4d0.js";import"./fromPairs.84aabb58.js";import"./scrollTo.f466bdf4.js";import"./index.16dfc5a5.js";import"./index.e50b03d6.js";import"./index.3edac26d.js";import"./index.05b679d6.js";import"./download.09d4dc22.js";import"./base64Conver.08b9f4ec.js";import"./index.56da1a6c.js";import"./index.c6fbab17.js";const I=[{title:"\u7F16\u53F7",dataIndex:"no",width:100},{title:"\u59D3\u540D",dataIndex:"name",width:200,auth:"test"},{title:"\u72B6\u6001",dataIndex:"status"},{title:"\u72B6\u60011",dataIndex:"status1"},{title:"\u72B6\u60012",dataIndex:"status2"},{title:"\u72B6\u60013",dataIndex:"status3"},{title:"\u72B6\u60014",dataIndex:"status4"},{title:"\u72B6\u60015",dataIndex:"status5"},{title:"\u5730\u5740",dataIndex:"address",auth:"super",ifShow:t=>!0},{title:"\u5F00\u59CB\u65F6\u95F4",dataIndex:"beginTime"},{title:"\u7ED3\u675F\u65F6\u95F4",dataIndex:"endTime",width:200}],T=b({components:{BasicTable:s,TableAction:d},setup(){const[t]=c({title:"TableAction\u7EC4\u4EF6\u53CA\u56FA\u5B9A\u5217\u793A\u4F8B",api:F,columns:I,bordered:!0,rowKey:"id",rowSelection:{type:"checkbox"},actionColumn:{width:250,title:"Action",dataIndex:"action"}});function i(e){}function u(e){}function r(e){}return{registerTable:t,handleEdit:i,handleDelete:u,handleOpen:r}}}),x={class:"p-4"};function _(t,i,u,r,e,E){const p=n("TableAction"),m=n("BasicTable");return a(),f("div",x,[B(m,{onRegister:t.registerTable},{bodyCell:A(({column:l,record:o})=>[l.key==="action"?(a(),C(p,{key:0,actions:[{label:"\u7F16\u8F91",onClick:t.handleEdit.bind(null,o),auth:"other"},{label:"\u5220\u9664",icon:"ic:outline-delete-outline",onClick:t.handleDelete.bind(null,o),auth:"super"}],dropDownActions:[{label:"\u542F\u7528",popConfirm:{title:"\u662F\u5426\u542F\u7528\uFF1F",confirm:t.handleOpen.bind(null,o)},ifShow:k=>o.status!=="enable"},{label:"\u7981\u7528",popConfirm:{title:"\u662F\u5426\u7981\u7528\uFF1F",confirm:t.handleOpen.bind(null,o)},ifShow:()=>o.status==="enable"},{label:"\u540C\u65F6\u63A7\u5236",popConfirm:{title:"\u662F\u5426\u52A8\u6001\u663E\u793A\uFF1F",confirm:t.handleOpen.bind(null,o)},auth:"super",ifShow:()=>!0}]},null,8,["actions","dropDownActions"])):w("",!0)]),_:1},8,["onRegister"])])}var vt=h(T,[["render",_]]);export{vt as default};