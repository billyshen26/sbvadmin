import{B as b}from"./TableImg.bc5f2ace.js";import{k as C}from"./componentMap.371f952a.js";import{u as g}from"./useTable.855c1ea0.js";import{c as _}from"./system.ac9b6afb.js";import{u as w}from"./index.f96df07d.js";import{R as D,c as B,s as T}from"./RoleDrawer.28f4a881.js";import{au as R,a as k,aw as e,o as d,h as y,n as m,z as a,B as S,j as E,l as v}from"./index.8936adfc.js";import"./index.a031b534.js";import"./Checkbox.ef980fcb.js";import"./index.ea04e905.js";import"./index.992924f4.js";import"./eagerComputed.5918221c.js";import"./BasicForm.00e2f65d.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.49d1b3fd.js";import"./index.8ec43242.js";import"./index.fc0fbd70.js";import"./_baseIteratee.f05d7e29.js";import"./get.62ec95b2.js";import"./DeleteOutlined.8e231f8a.js";import"./index.bcf6ef30.js";import"./useRefs.215ca6c8.js";import"./Form.e91498a8.js";import"./Col.4fe65e36.js";import"./useFlexGapSupport.3a88a899.js";import"./useSize.205cd3d8.js";import"./index.b51f6c0b.js";import"./uniqBy.4d1cf5d8.js";import"./index.144a57bb.js";import"./useWindowSizeFn.c4d53914.js";import"./FullscreenOutlined.03730ed4.js";import"./index.0b5ddde1.js";import"./useForm.06b23df2.js";import"./RadioButtonGroup.80cfb485.js";import"./useFormItem.edd024b8.js";import"./index.894818cb.js";import"./index.467c9ef6.js";import"./index.a51b36c2.js";import"./useContentViewHeight.6afc3857.js";import"./ArrowLeftOutlined.398710a4.js";import"./index.01ed4ff8.js";import"./transButton.f5a81c82.js";import"./index.5bc6caf0.js";import"./index.aaf8b1a3.js";import"./index.61e0a1ab.js";import"./index.69870b0f.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.b6662317.js";import"./index.636a0484.js";import"./isNumber.5ce0e4d0.js";import"./fromPairs.84aabb58.js";import"./scrollTo.f466bdf4.js";import"./index.16dfc5a5.js";import"./index.e50b03d6.js";import"./index.3edac26d.js";import"./index.05b679d6.js";import"./download.09d4dc22.js";import"./base64Conver.08b9f4ec.js";import"./index.56da1a6c.js";import"./index.c6fbab17.js";import"./index.cd635507.js";import"./index.f04e25b4.js";import"./useContextMenu.99a9252c.js";const A=k({name:"RoleManagement",components:{BasicTable:b,RoleDrawer:D,TableAction:C},setup(){const[o,{openDrawer:r}]=w(),[p,{reload:s}]=g({title:"\u89D2\u8272\u5217\u8868",api:_,columns:B,formConfig:{labelWidth:120,schemas:T},useSearchForm:!0,showTableSetting:!0,bordered:!0,showIndexColumn:!1,actionColumn:{width:80,title:"\u64CD\u4F5C",dataIndex:"action",fixed:void 0}});function l(){r(!0,{isUpdate:!1})}function c(t){r(!0,{record:t,isUpdate:!0})}function i(t){}function n(){s()}return{registerTable:p,registerDrawer:o,handleCreate:l,handleEdit:c,handleDelete:i,handleSuccess:n}}});function F(o,r,p,s,l,c){const i=e("a-button"),n=e("TableAction"),t=e("BasicTable"),f=e("RoleDrawer");return d(),y("div",null,[m(t,{onRegister:o.registerTable},{toolbar:a(()=>[m(i,{type:"primary",onClick:o.handleCreate},{default:a(()=>[S(" \u65B0\u589E\u89D2\u8272 ")]),_:1},8,["onClick"])]),bodyCell:a(({column:h,record:u})=>[h.key==="action"?(d(),E(n,{key:0,actions:[{icon:"clarity:note-edit-line",onClick:o.handleEdit.bind(null,u)},{icon:"ant-design:delete-outlined",color:"error",popConfirm:{title:"\u662F\u5426\u786E\u8BA4\u5220\u9664",placement:"left",confirm:o.handleDelete.bind(null,u)}}]},null,8,["actions"])):v("",!0)]),_:1},8,["onRegister"]),m(f,{onRegister:o.registerDrawer,onSuccess:o.handleSuccess},null,8,["onRegister","onSuccess"])])}var qo=R(A,[["render",F]]);export{qo as default};