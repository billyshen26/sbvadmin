var k=(h,s,t)=>new Promise((e,n)=>{var f=r=>{try{i(t.next(r))}catch(a){n(a)}},d=r=>{try{i(t.throw(r))}catch(a){n(a)}},i=r=>r.done?e(r.value):Promise.resolve(r.value).then(f,d);i((t=t.apply(h,s)).next())});import{dB as x,a as T,v as w,c as B,fw as D,a7 as A,al as R,ah as V,aw as I,o as u,h as _,F as S,az as j,x as M,y as N,n as m,k as o,z as p,B as y,t as b,j as z,l as F}from"./index.a0f4a53f.js";import $ from"./DetailModal.11ee06b6.js";import{B as H}from"./TableImg.8b38357d.js";import{k as q}from"./componentMap.532712b5.js";import{u as G}from"./useTable.9731f2a2.js";import{b as J}from"./index.73a625c6.js";import{getColumns as K}from"./data.ae9f75a6.js";import"./index.63b2a56a.js";import"./index.96149a77.js";import"./get.72cb776d.js";import"./useDescription.0c3e89d3.js";import"./index.c614ae28.js";import"./Checkbox.7a2e3c7b.js";import"./index.a4da4b30.js";import"./index.112a9f37.js";import"./eagerComputed.f27ac635.js";import"./BasicForm.539460c6.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.92918688.js";import"./index.acf588db.js";import"./index.c34f9609.js";import"./_baseIteratee.7eef18d3.js";import"./DeleteOutlined.e19abfbb.js";import"./index.da68fb2b.js";import"./useRefs.727c25ab.js";import"./Form.1dc8a98e.js";import"./Col.82157996.js";import"./useFlexGapSupport.55bd0324.js";import"./useSize.bbc03f2d.js";import"./index.9fa96fb8.js";import"./uniqBy.9a18c29f.js";import"./index.3facd4b8.js";import"./useForm.c64d8c6a.js";import"./RadioButtonGroup.0d384df5.js";import"./useFormItem.51e35cd3.js";import"./index.77638da0.js";import"./index.ee2c9f76.js";import"./index.eaffcdc0.js";import"./useWindowSizeFn.61bf8fec.js";import"./useContentViewHeight.66e53fe3.js";import"./ArrowLeftOutlined.41cc7a18.js";import"./index.0c949780.js";import"./transButton.e15f313e.js";import"./index.7392d982.js";import"./index.e3018fdb.js";import"./index.74f87750.js";import"./index.8681b128.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.32db9eb1.js";import"./FullscreenOutlined.a503eb0e.js";import"./index.98ddc2e5.js";import"./isNumber.8890bb61.js";import"./fromPairs.84aabb58.js";import"./scrollTo.5f2fb44e.js";import"./index.58cb923a.js";import"./index.152abf5f.js";import"./index.49be5f7f.js";import"./index.0b5da165.js";import"./download.56687849.js";import"./base64Conver.08b9f4ec.js";import"./index.38d55bea.js";import"./index.82b1ebe6.js";const O=()=>x.get({url:"/error"}),P={class:"p-4"},Q=["src"],po=T({__name:"index",setup(h){const s=w(),t=w([]),{t:e}=B(),n=D(),[f,{setTableData:d}]=G({title:e("sys.errorLog.tableTitle"),columns:K(),actionColumn:{width:80,title:"Action",dataIndex:"action"}}),[i,{openModal:r}]=J();A(()=>n.getErrorLogInfoList,l=>{R(()=>{d(V(l))})},{immediate:!0});function a(l){s.value=l,r(!0)}function C(){throw new Error("fire vue error!")}function E(){t.value.push(`${new Date().getTime()}.png`)}function L(){return k(this,null,function*(){yield O()})}return(l,U)=>{const g=I("a-button");return u(),_("div",P,[(u(!0),_(S,null,j(t.value,c=>M((u(),_("img",{key:c,src:c,alt:""},null,8,Q)),[[N,!1]])),128)),m($,{info:s.value,onRegister:o(i)},null,8,["info","onRegister"]),m(o(H),{onRegister:o(f),class:"error-handle-table"},{toolbar:p(()=>[m(g,{onClick:C,type:"primary"},{default:p(()=>[y(b(o(e)("sys.errorLog.fireVueError")),1)]),_:1}),m(g,{onClick:E,type:"primary"},{default:p(()=>[y(b(o(e)("sys.errorLog.fireResourceError")),1)]),_:1}),m(g,{onClick:L,type:"primary"},{default:p(()=>[y(b(o(e)("sys.errorLog.fireAjaxError")),1)]),_:1})]),bodyCell:p(({column:c,record:v})=>[c.key==="action"?(u(),z(o(q),{key:0,actions:[{label:o(e)("sys.errorLog.tableActionDesc"),onClick:a.bind(null,v)}]},null,8,["actions"])):F("",!0)]),_:1},8,["onRegister"])])}}});export{po as default};