import{B as _,a as h}from"./index.2cf2b49a.js";import{a as f,v as p,a7 as B,aw as v,ay as m,o as t,j as M,z as i,n as y,h as o,l as g,F as C,aB as F,t as k,az as D,B as b}from"./index.26fcfc43.js";import"./useWindowSizeFn.d178ecd3.js";import"./FullscreenOutlined.eba4c2eb.js";const w=f({components:{BasicModal:_},setup(){const e=p(!0),a=p(10),[u,{setModalProps:n,redoModalHeight:l}]=h();B(()=>a.value,()=>{l()});function d(r){r&&(e.value=!0,n({loading:!0,confirmLoading:!0}),setTimeout(()=>{a.value=Math.round(Math.random()*30+5),e.value=!1,n({loading:!1,confirmLoading:!1})},3e3))}function s(){a.value=Math.round(Math.random()*20+10)}return{register:u,loading:e,handleShow:d,lines:a,setLines:s}}}),A=b("\u70B9\u6211\u66F4\u65B0\u5185\u5BB9"),L={key:0,class:"empty-tips"},V={key:1};function $(e,a,u,n,l,d){const s=m("a-button"),r=m("BasicModal");return t(),M(r,D(e.$attrs,{destroyOnClose:"",onRegister:e.register,title:"Modal Title",helpMessage:["\u63D0\u793A1","\u63D0\u793A2"],onVisibleChange:e.handleShow}),{insertFooter:i(()=>[y(s,{type:"primary",danger:"",onClick:e.setLines,disabled:e.loading},{default:i(()=>[A]),_:1},8,["onClick","disabled"])]),default:i(()=>[e.loading?(t(),o("div",L,"\u52A0\u8F7D\u4E2D\uFF0C\u7A0D\u7B493\u79D2\u2026\u2026")):g("",!0),e.loading?g("",!0):(t(),o("ul",V,[(t(!0),o(C,null,F(e.lines,c=>(t(),o("li",{key:c},"\u52A0\u8F7D\u5B8C\u6210"+k(c)+"\uFF01",1))),128))]))]),_:1},16,["onRegister","onVisibleChange"])}var E=v(w,[["render",$],["__scopeId","data-v-47fa5808"]]);export{E as default};
