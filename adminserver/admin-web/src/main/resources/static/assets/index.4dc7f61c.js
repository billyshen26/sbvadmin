var N=Object.defineProperty,y=Object.defineProperties;var A=Object.getOwnPropertyDescriptors;var B=Object.getOwnPropertySymbols;var M=Object.prototype.hasOwnProperty,I=Object.prototype.propertyIsEnumerable;var F=(e,n,a)=>n in e?N(e,n,{enumerable:!0,configurable:!0,writable:!0,value:a}):e[n]=a,b=(e,n)=>{for(var a in n||(n={}))M.call(n,a)&&F(e,a,n[a]);if(B)for(var a of B(n))I.call(n,a)&&F(e,a,n[a]);return e},C=(e,n)=>y(e,A(n));import{P as S}from"./index.44a6cc14.js";import{a as O,aG as u,v as Y,c as H,eK as P,a7 as R,eL as j,ds as k,e5 as h,aw as $,cz as U,aH as W,br as z,o as E,h as V,t as q,aF as G,cD as K,w as L,bc as J,ay as w,j as Q,z as f,n as i,i as _}from"./index.26fcfc43.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useSize.25d44e1a.js";import"./eagerComputed.c053c9c2.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./transButton.2853c7d5.js";const p=1e3,d=p*60,v=d*60,D=v*24,X=O({name:"Time",props:{value:u.oneOfType([u.number,u.instanceOf(Date),u.string]).isRequired,step:u.number.def(60),mode:u.oneOf(["date","datetime","relative"]).def("relative")},setup(e){const n=Y(""),{t:a}=H();P(m,e.step*p),R(()=>e.value,()=>{m()},{immediate:!0});function T(){const{value:t}=e;let s=0;if(U(t)){const l=t.toString().length>10?t:t*1e3;s=new Date(l).getTime()}else W(t)?s=new Date(t).getTime():z(t)&&(s=t.getTime());return s}function m(){const{mode:t,value:s}=e,l=T();t==="relative"?n.value=g(l):t==="datetime"?n.value=j(s):t==="date"&&(n.value=k(s))}function g(t){const s=new Date().getTime(),l=h(t).isBefore(s);let o=s-t;l||(o=-o);let r="",c=a(l?"component.time.before":"component.time.after");return o<p?r=a("component.time.just"):o<d?r=parseInt(o/p)+a("component.time.seconds")+c:o>=d&&o<v?r=Math.floor(o/d)+a("component.time.minutes")+c:o>=v&&o<D?r=Math.floor(o/v)+a("component.time.hours")+c:o>=D&&o<262386e4?r=Math.floor(o/D)+a("component.time.days")+c:o>=262386e4&&o<=3156786e4&&l?r=h(t).format("MM-DD-HH-mm"):r=h(t).format("YYYY"),r}return{date:n}}});function Z(e,n,a,T,m,g){return E(),V("span",null,q(e.date),1)}var x=$(X,[["render",Z]]);const ee=G(x),te=O({components:{PageWrapper:S,Time:ee,CollapseContainer:K},setup(){const e=new Date().getTime(),n=L({time1:e-60*3*1e3,time2:e-86400*3*1e3});return C(b({},J(n)),{now:e})}}),ne=_("br",null,null,-1),ae=_("br",null,null,-1),oe=_("br",null,null,-1),se=_("br",null,null,-1);function ie(e,n,a,T,m,g){const t=w("Time"),s=w("CollapseContainer"),l=w("PageWrapper");return E(),Q(l,{title:"\u65F6\u95F4\u7EC4\u4EF6\u793A\u4F8B"},{default:f(()=>[i(s,{title:"\u57FA\u7840\u793A\u4F8B"},{default:f(()=>[i(t,{value:e.time1},null,8,["value"]),ne,i(t,{value:e.time2},null,8,["value"])]),_:1}),i(s,{title:"\u5B9A\u65F6\u66F4\u65B0",class:"my-4"},{default:f(()=>[i(t,{value:e.now,step:1},null,8,["value"]),ae,i(t,{value:e.now,step:5},null,8,["value"])]),_:1}),i(s,{title:"\u5B9A\u65F6\u66F4\u65B0"},{default:f(()=>[i(t,{value:e.now,mode:"date"},null,8,["value"]),oe,i(t,{value:e.now,mode:"datetime"},null,8,["value"]),se,i(t,{value:e.now},null,8,["value"])]),_:1})]),_:1})}var he=$(te,[["render",ie]]);export{he as default};
