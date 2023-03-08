import{B as g}from"./TableImg.292e04d9.js";import{k as C}from"./componentMap.89831d4d.js";import{u as b}from"./useTable.f07faccc.js";import{b as T}from"./index.5d9418ce.js";import{au as _,a as B,w as S,fM as k,aw as r,o as h,h as I,n as p,z as c,B as M,j as y,l as w,fN as E}from"./index.21dbddea.js";import{C as F,c as R,s as A}from"./ConfigModal.b3df7859.js";import"./index.82fe2da0.js";import"./Checkbox.b4e3675e.js";import"./index.17dda3d9.js";import"./index.2ddb10b6.js";import"./eagerComputed.1ea2a8d9.js";import"./BasicForm.f15e10d2.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.ce9802a8.js";import"./index.a37178f8.js";import"./index.c3a78ee8.js";import"./_baseIteratee.165cbc4f.js";import"./get.41fb1f2f.js";import"./DeleteOutlined.1931f992.js";import"./index.94534871.js";import"./useRefs.15808103.js";import"./Form.b6c45920.js";import"./Col.2a58f9a8.js";import"./useFlexGapSupport.06ccabe5.js";import"./useSize.e955a44e.js";import"./index.5e62cd22.js";import"./uniqBy.45490986.js";import"./index.4a1de338.js";import"./useForm.e0cbc007.js";import"./RadioButtonGroup.bc4b2238.js";import"./useFormItem.1145f6c8.js";import"./index.262aa95b.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useWindowSizeFn.7490f562.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";import"./transButton.25f118c6.js";import"./index.ec76c75d.js";import"./index.c91c606e.js";import"./index.193019b5.js";import"./index.63df3ac3.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.169c31ba.js";import"./FullscreenOutlined.1b876098.js";import"./index.44538731.js";import"./isNumber.9b40a4c7.js";import"./fromPairs.84aabb58.js";import"./scrollTo.667abe92.js";import"./index.8d01bfec.js";import"./index.299e002f.js";import"./index.903acead.js";import"./index.ba1296a1.js";import"./download.68505b66.js";import"./base64Conver.08b9f4ec.js";import"./index.7b25a8ae.js";import"./index.ead49ec7.js";const D=B({name:"ConfigManagement",components:{BasicTable:g,ConfigModal:F,TableAction:C},setup(){const[o,{openModal:i}]=T(),u=S({}),[d,{reload:n,updateTableDataRecord:f}]=b({title:"\u5217\u8868",api:k,rowKey:"id",columns:R,formConfig:{labelWidth:120,schemas:A,autoSubmitOnEnter:!0},showIndexColumn:!1,useSearchForm:!0,showTableSetting:!0,bordered:!0,handleSearchInfoFn(e){return e},actionColumn:{width:120,title:"\u64CD\u4F5C",dataIndex:"action"}});function a(){i(!0,{isUpdate:!1})}function m(e){i(!0,{record:e,isUpdate:!0})}function s(e){E(e.id).then(t=>{}).catch(t=>{}).finally(()=>{n()})}function l({isUpdate:e,values:t}){if(e){const $=f(t.id,t)}else n()}return{registerTable:d,registerModal:o,handleCreate:a,handleEdit:m,handleDelete:s,handleSuccess:l,searchInfo:u}}});function N(o,i,u,d,n,f){const a=r("a-button"),m=r("TableAction"),s=r("BasicTable"),l=r("ConfigModal");return h(),I("div",null,[p(s,{onRegister:o.registerTable,searchInfo:o.searchInfo},{toolbar:c(()=>[p(a,{type:"primary",onClick:o.handleCreate},{default:c(()=>[M(" \u65B0\u589E ")]),_:1},8,["onClick"])]),bodyCell:c(({column:e,record:t})=>[e.key==="action"?(h(),y(m,{key:0,actions:[{icon:"clarity:note-edit-line",onClick:o.handleEdit.bind(null,t)},{icon:"ant-design:delete-outlined",color:"error",popConfirm:{title:"\u662F\u5426\u786E\u8BA4\u5220\u9664",placement:"left",confirm:o.handleDelete.bind(null,t)}}]},null,8,["actions"])):w("",!0)]),_:1},8,["onRegister","searchInfo"]),p(l,{onRegister:o.registerModal,onSuccess:o.handleSuccess},null,8,["onRegister","onSuccess"])])}var Lo=_(D,[["render",N]]);export{Lo as default};
