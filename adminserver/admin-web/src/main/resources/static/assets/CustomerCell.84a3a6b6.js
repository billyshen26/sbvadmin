import{B as g,T as _}from"./TableImg.9e89c887.js";import"./BasicForm.e2d5cc76.js";import{u as E}from"./useTable.98436359.js";import{aw as T,a as y,ay as r,o as t,h as l,n as k,z as a,F as A,B as m,t as p,j as i,l as B}from"./index.26fcfc43.js";import{T as C}from"./index.53f940a3.js";import{A as f}from"./index.f2fc0df8.js";import{d as I}from"./table.03dd1559.js";import"./index.b325d19a.js";import"./Checkbox.2a1a359d.js";import"./index.dc14bff5.js";import"./index.27b9c0ab.js";import"./eagerComputed.c053c9c2.js";import"./useForm.e26a883f.js";import"./index.44a6cc14.js";import"./index.2245f205.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./useSize.25d44e1a.js";import"./transButton.2853c7d5.js";import"./index.edc7a53e.js";import"./index.599cf005.js";import"./uuid.2b29000c.js";import"./index.9028474e.js";import"./_baseIteratee.7cbe611d.js";import"./get.3a52d42a.js";import"./DeleteOutlined.849ecd53.js";import"./index.e58470bc.js";import"./useRefs.753293e1.js";import"./Form.a60d3742.js";import"./Col.ad062a60.js";import"./useFlexGapSupport.71c58254.js";import"./index.2cf2b49a.js";import"./FullscreenOutlined.eba4c2eb.js";import"./index.68631c3d.js";import"./sortable.esm.2632adaa.js";import"./RedoOutlined.e3ed3ea8.js";import"./index.915486e3.js";import"./fromPairs.84aabb58.js";import"./scrollTo.15c9e589.js";import"./index.2a41d039.js";/* empty css              *//* empty css              */import"./index.6b155408.js";import"./index.d4067d83.js";import"./index.c7154726.js";import"./download.7b60a2b1.js";import"./base64Conver.08b9f4ec.js";import"./index.42baef15.js";import"./index.995e13ea.js";import"./uniqBy.1499beda.js";const b=[{title:"ID",dataIndex:"id"},{title:"\u5934\u50CF",dataIndex:"avatar",width:100},{title:"\u5206\u7C7B",dataIndex:"category",width:80,align:"center",defaultHidden:!0},{title:"\u59D3\u540D",dataIndex:"name",width:120},{title:"\u56FE\u7247\u5217\u88681",dataIndex:"imgArr",helpMessage:["\u8FD9\u662F\u7B80\u5355\u6A21\u5F0F\u7684\u56FE\u7247\u5217\u8868","\u53EA\u4F1A\u663E\u793A\u4E00\u5F20\u5728\u8868\u683C\u4E2D","\u4F46\u70B9\u51FB\u53EF\u9884\u89C8\u591A\u5F20\u56FE\u7247"],width:140},{title:"\u7167\u7247\u5217\u88682",dataIndex:"imgs",width:160},{title:"\u5730\u5740",dataIndex:"address"},{title:"\u7F16\u53F7",dataIndex:"no"},{title:"\u5F00\u59CB\u65F6\u95F4",dataIndex:"beginTime"},{title:"\u7ED3\u675F\u65F6\u95F4",dataIndex:"endTime"}],x=y({components:{BasicTable:g,TableImg:_,Tag:C,Avatar:f},setup(){const[u]=E({title:"\u81EA\u5B9A\u4E49\u5217\u5185\u5BB9",titleHelpMessage:"\u8868\u683C\u4E2D\u6240\u6709\u5934\u50CF\u3001\u56FE\u7247\u5747\u4E3Amock\u751F\u6210\uFF0C\u4EC5\u7528\u4E8E\u6F14\u793A\u56FE\u7247\u5360\u4F4D",api:I,columns:b,bordered:!0,showTableSetting:!0});return{registerTable:u}}}),h={class:"p-4"};function D(u,v,w,L,z,$){const s=r("Tag"),c=r("Avatar"),n=r("TableImg"),F=r("BasicTable");return t(),l("div",h,[k(F,{onRegister:u.registerTable},{bodyCell:a(({column:e,record:o,text:d})=>[e.key==="id"?(t(),l(A,{key:0},[m(" ID: "+p(o.id),1)],64)):e.key==="no"?(t(),i(s,{key:1,color:"green"},{default:a(()=>[m(p(o.no),1)]),_:2},1024)):e.key==="avatar"?(t(),i(c,{key:2,size:60,src:o.avatar},null,8,["src"])):e.key==="imgArr"?(t(),i(n,{key:3,size:60,simpleShow:!0,imgList:d},null,8,["imgList"])):e.key==="imgs"?(t(),i(n,{key:4,size:60,imgList:d},null,8,["imgList"])):e.key==="category"?(t(),i(s,{key:5,color:"green"},{default:a(()=>[m(p(o.no),1)]),_:2},1024)):B("",!0)]),_:1},8,["onRegister"])])}var zt=T(x,[["render",D]]);export{zt as default};