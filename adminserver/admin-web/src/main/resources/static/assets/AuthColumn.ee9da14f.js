import{B as s}from"./TableImg.9e89c887.js";import{j as d}from"./BasicForm.e2d5cc76.js";import{u as c}from"./useTable.98436359.js";import{d as F}from"./table.03dd1559.js";import{aw as h,a as b,ay as a,o as r,h as f,n as B,z as A,j as C,l as w}from"./index.26fcfc43.js";import"./index.b325d19a.js";import"./Checkbox.2a1a359d.js";import"./index.dc14bff5.js";import"./index.27b9c0ab.js";import"./eagerComputed.c053c9c2.js";import"./useForm.e26a883f.js";import"./index.44a6cc14.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useSize.25d44e1a.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./transButton.2853c7d5.js";import"./index.edc7a53e.js";import"./index.599cf005.js";import"./index.53f940a3.js";import"./uuid.2b29000c.js";import"./index.9028474e.js";import"./_baseIteratee.7cbe611d.js";import"./get.3a52d42a.js";import"./DeleteOutlined.849ecd53.js";import"./index.e58470bc.js";import"./useRefs.753293e1.js";import"./Form.a60d3742.js";import"./Col.ad062a60.js";import"./useFlexGapSupport.71c58254.js";import"./index.2cf2b49a.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.68631c3d.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.e3ed3ea8.js";import"./index.915486e3.js";import"./fromPairs.84aabb58.js";import"./scrollTo.15c9e589.js";import"./index.2a41d039.js";/* empty css              *//* empty css              */import"./index.6b155408.js";import"./index.d4067d83.js";import"./index.c7154726.js";import"./download.7b60a2b1.js";import"./base64Conver.08b9f4ec.js";import"./index.42baef15.js";import"./index.995e13ea.js";import"./uniqBy.1499beda.js";const I=[{title:"\u7F16\u53F7",dataIndex:"no",width:100},{title:"\u59D3\u540D",dataIndex:"name",width:200,auth:"test"},{title:"\u72B6\u6001",dataIndex:"status"},{title:"\u72B6\u60011",dataIndex:"status1"},{title:"\u72B6\u60012",dataIndex:"status2"},{title:"\u72B6\u60013",dataIndex:"status3"},{title:"\u72B6\u60014",dataIndex:"status4"},{title:"\u72B6\u60015",dataIndex:"status5"},{title:"\u5730\u5740",dataIndex:"address",auth:"super",ifShow:t=>!0},{title:"\u5F00\u59CB\u65F6\u95F4",dataIndex:"beginTime"},{title:"\u7ED3\u675F\u65F6\u95F4",dataIndex:"endTime",width:200}],T=b({components:{BasicTable:s,TableAction:d},setup(){const[t]=c({title:"TableAction\u7EC4\u4EF6\u53CA\u56FA\u5B9A\u5217\u793A\u4F8B",api:F,columns:I,bordered:!0,rowKey:"id",rowSelection:{type:"checkbox"},actionColumn:{width:250,title:"Action",dataIndex:"action"}});function i(e){}function u(e){}function n(e){}return{registerTable:t,handleEdit:i,handleDelete:u,handleOpen:n}}}),x={class:"p-4"};function _(t,i,u,n,e,E){const l=a("TableAction"),p=a("BasicTable");return r(),f("div",x,[B(p,{onRegister:t.registerTable},{bodyCell:A(({column:m,record:o})=>[m.key==="action"?(r(),C(l,{key:0,actions:[{label:"\u7F16\u8F91",onClick:t.handleEdit.bind(null,o),auth:"other"},{label:"\u5220\u9664",icon:"ic:outline-delete-outline",onClick:t.handleDelete.bind(null,o),auth:"super"}],dropDownActions:[{label:"\u542F\u7528",popConfirm:{title:"\u662F\u5426\u542F\u7528\uFF1F",confirm:t.handleOpen.bind(null,o)},ifShow:g=>o.status!=="enable"},{label:"\u7981\u7528",popConfirm:{title:"\u662F\u5426\u7981\u7528\uFF1F",confirm:t.handleOpen.bind(null,o)},ifShow:()=>o.status==="enable"},{label:"\u540C\u65F6\u63A7\u5236",popConfirm:{title:"\u662F\u5426\u52A8\u6001\u663E\u793A\uFF1F",confirm:t.handleOpen.bind(null,o)},auth:"super",ifShow:()=>!0}]},null,8,["actions","dropDownActions"])):w("",!0)]),_:1},8,["onRegister"])])}var Et=h(T,[["render",_]]);export{Et as default};