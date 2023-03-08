import{B as E}from"./TableImg.292e04d9.js";import"./componentMap.89831d4d.js";import{getBasicColumns as p,getBasicShortColumns as R}from"./tableData.f8e139b8.js";import{au as T,a as w,v as D,aw as f,o as _,h as S,i as g,n as e,z as u,B as i,E as h,k as L}from"./index.21dbddea.js";import{d as y}from"./table.5d23c004.js";import"./index.82fe2da0.js";import"./Checkbox.b4e3675e.js";import"./index.17dda3d9.js";import"./index.2ddb10b6.js";import"./eagerComputed.1ea2a8d9.js";import"./BasicForm.f15e10d2.js";/* empty css              *//* empty css              */import"./FormItem.vue_vue_type_script_lang.ce9802a8.js";import"./index.a37178f8.js";import"./index.c3a78ee8.js";import"./_baseIteratee.165cbc4f.js";import"./get.41fb1f2f.js";import"./DeleteOutlined.1931f992.js";import"./index.94534871.js";import"./useRefs.15808103.js";import"./Form.b6c45920.js";import"./Col.2a58f9a8.js";import"./useFlexGapSupport.06ccabe5.js";import"./useSize.e955a44e.js";import"./index.5e62cd22.js";import"./uniqBy.45490986.js";import"./index.5d9418ce.js";import"./useWindowSizeFn.7490f562.js";import"./FullscreenOutlined.1b876098.js";import"./index.4a1de338.js";import"./useForm.e0cbc007.js";import"./RadioButtonGroup.bc4b2238.js";import"./useFormItem.1145f6c8.js";import"./index.262aa95b.js";import"./index.8d2cb20f.js";import"./index.2e41d04d.js";import"./useContentViewHeight.22c0cdcf.js";import"./ArrowLeftOutlined.a8bf1d79.js";import"./index.dd967332.js";import"./transButton.25f118c6.js";import"./index.ec76c75d.js";import"./index.c91c606e.js";import"./index.193019b5.js";import"./index.63df3ac3.js";import"./uuid.2b29000c.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.169c31ba.js";import"./index.44538731.js";import"./isNumber.9b40a4c7.js";import"./fromPairs.84aabb58.js";import"./scrollTo.667abe92.js";import"./index.8d01bfec.js";import"./index.299e002f.js";import"./index.903acead.js";import"./index.ba1296a1.js";import"./download.68505b66.js";import"./base64Conver.08b9f4ec.js";import"./index.7b25a8ae.js";import"./index.ead49ec7.js";import"./select.5a545c5a.js";const K=w({components:{BasicTable:E},setup(){const o=D(null),{createMessage:a}=h();function n(){const m=L(o);if(!m)throw new Error("tableAction is null");return m}function s(){n().setLoading(!0),setTimeout(()=>{n().setLoading(!1)},1e3)}function r(){n().setColumns(R())}function c(){n().setColumns(p()),n().reload({page:1})}function t(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function l(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function C(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function d(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function F(){n().setPagination({current:2}),n().reload()}function B(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function k(){a.info("\u8BF7\u5728\u63A7\u5236\u53F0\u67E5\u770B\uFF01")}function b(){n().setSelectedRowKeys(["0","1","2"])}function A(){n().clearSelectedRowKeys()}return{tableRef:o,api:y,columns:p(),changeLoading:s,changeColumns:r,reloadTable:c,getColumn:t,getTableData:l,getTableRawData:C,getPagination:d,setPaginationInfo:F,getSelectRowList:B,getSelectRowKeyList:k,setSelectedRowKeyList:b,clearSelect:A}}}),v={class:"p-4"},P={class:"mb-4"},$={class:"mb-4"};function M(o,a,n,s,r,c){const t=f("a-button"),l=f("BasicTable");return _(),S("div",v,[g("div",P,[e(t,{class:"mr-2",onClick:o.reloadTable},{default:u(()=>[i(" \u8FD8\u539F ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.changeLoading},{default:u(()=>[i(" \u5F00\u542Floading ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.changeColumns},{default:u(()=>[i(" \u66F4\u6539Columns ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.getColumn},{default:u(()=>[i(" \u83B7\u53D6Columns ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.getTableData},{default:u(()=>[i(" \u83B7\u53D6\u8868\u683C\u6570\u636E ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.getTableRawData},{default:u(()=>[i(" \u83B7\u53D6\u63A5\u53E3\u539F\u59CB\u6570\u636E ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.setPaginationInfo},{default:u(()=>[i(" \u8DF3\u8F6C\u5230\u7B2C2\u9875 ")]),_:1},8,["onClick"])]),g("div",$,[e(t,{class:"mr-2",onClick:o.getSelectRowList},{default:u(()=>[i(" \u83B7\u53D6\u9009\u4E2D\u884C ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.getSelectRowKeyList},{default:u(()=>[i(" \u83B7\u53D6\u9009\u4E2D\u884CKey ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.setSelectedRowKeyList},{default:u(()=>[i(" \u8BBE\u7F6E\u9009\u4E2D\u884C ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.clearSelect},{default:u(()=>[i(" \u6E05\u7A7A\u9009\u4E2D\u884C ")]),_:1},8,["onClick"]),e(t,{class:"mr-2",onClick:o.getPagination},{default:u(()=>[i(" \u83B7\u53D6\u5206\u9875\u4FE1\u606F ")]),_:1},8,["onClick"])]),e(l,{canResize:!1,title:"RefTable\u793A\u4F8B",titleHelpMessage:"\u4F7F\u7528Ref\u8C03\u7528\u8868\u683C\u5185\u65B9\u6CD5",ref:"tableRef",api:o.api,columns:o.columns,rowKey:"id",rowSelection:{type:"checkbox"}},null,8,["api","columns"])])}var Jo=T(K,[["render",M]]);export{Jo as default};
