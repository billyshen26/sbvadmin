import{af as y,k as c,fo as W,fp as v,M as x,v as w,fq as b,at as $,e7 as g,aw as B,a as R,cD as I,ay as k,o as M,j as P,z as d,n as u,B as C}from"./index.26fcfc43.js";import{P as z}from"./index.44a6cc14.js";import"./index.2245f205.js";import"./index.f2fc0df8.js";import"./useSize.25d44e1a.js";import"./eagerComputed.c053c9c2.js";import"./useWindowSizeFn.d178ecd3.js";import"./useContentViewHeight.4f2058b0.js";import"./ArrowLeftOutlined.2e1e63d4.js";import"./index.672a21ec.js";import"./transButton.2853c7d5.js";const S=Symbol("watermark-dom");function D(n=w(document.body)){const o=b(function(){const t=c(n);if(!t)return;const{clientHeight:e,clientWidth:a}=t;i({height:e,width:a})}),s=S.toString(),l=y(),f=()=>{const t=c(l);l.value=void 0;const e=c(n);!e||(t&&e.removeChild(t),W(e,o))};function _(t){const e=document.createElement("canvas"),a=300,m=240;Object.assign(e,{width:a,height:m});const r=e.getContext("2d");return r&&(r.rotate(-20*Math.PI/120),r.font="15px Vedana",r.fillStyle="rgba(0, 0, 0, 0.15)",r.textAlign="left",r.textBaseline="middle",r.fillText(t,a/20,m)),e.toDataURL("image/png")}function i(t={}){const e=c(l);!e||(g(t.width)&&(e.style.width=`${t.width}px`),g(t.height)&&(e.style.height=`${t.height}px`),g(t.str)&&(e.style.background=`url(${_(t.str)}) left top repeat`))}const p=t=>{if(c(l))return i({str:t}),s;const e=document.createElement("div");l.value=e,e.id=s,e.style.pointerEvents="none",e.style.top="0px",e.style.left="0px",e.style.position="absolute",e.style.zIndex="100000";const a=c(n);if(!a)return s;const{clientHeight:m,clientWidth:r}=a;return i({str:t,width:r,height:m}),a.appendChild(e),s};function h(t){p(t),v(document.documentElement,o),$()&&x(()=>{f()})}return{setWatermark:h,clear:f}}const E=R({components:{CollapseContainer:I,PageWrapper:z},setup(){const n=w(null),{setWatermark:o,clear:s}=D();return{setWatermark:o,clear:s,areaRef:n}}}),L=C(" Create "),N=C(" Clear "),T=C(" Reset ");function V(n,o,s,l,f,_){const i=k("a-button"),p=k("CollapseContainer"),h=k("PageWrapper");return M(),P(h,{title:"\u6C34\u5370\u793A\u4F8B"},{default:d(()=>[u(p,{class:"w-full h-32 bg-white rounded-md",title:"Global WaterMark"},{default:d(()=>[u(i,{type:"primary",class:"mr-2",onClick:o[0]||(o[0]=t=>n.setWatermark("WaterMark Info"))},{default:d(()=>[L]),_:1}),u(i,{color:"error",class:"mr-2",onClick:n.clear},{default:d(()=>[N]),_:1},8,["onClick"]),u(i,{color:"warning",class:"mr-2",onClick:o[1]||(o[1]=t=>n.setWatermark("WaterMark Info New"))},{default:d(()=>[T]),_:1})]),_:1})]),_:1})}var X=B(E,[["render",V]]);export{X as default};
