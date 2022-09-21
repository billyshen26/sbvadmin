import{a as E,f as h,w as at,v as V,a7 as L,b$ as et,_ as c,n as d,R as W,J as q,aj as nt,ck as M,G as T,cq as rt,T as P,S as p,c6 as ot,Q as lt,ep as it,Y as st,eb as ct,ec as ut,x as bt,y as dt}from"./index.26fcfc43.js";function J(n){var t=n.prefixCls,o=n.value,a=n.current,e=n.offset,v=e===void 0?0:e,r;return v&&(r={position:"absolute",top:"".concat(v,"00%"),left:0}),d("p",{style:r,class:W("".concat(t,"-only-unit"),{current:a})},[o])}function ft(n,t,o){for(var a=n,e=0;(a+10)%10!==t;)a+=o,e+=o;return e}var gt=E({name:"SingleNumber",props:{prefixCls:String,value:String,count:Number},setup:function(t){var o=h(function(){return Number(t.value)}),a=h(function(){return Math.abs(t.count)}),e=at({prevValue:o.value,prevCount:a.value}),v=function(){e.prevValue=o.value,e.prevCount=a.value},r=V();return L(o,function(){clearTimeout(r.value),r.value=setTimeout(function(){v()},1e3)},{flush:"post"}),et(function(){clearTimeout(r.value)}),function(){var f,i={},s=o.value;if(e.prevValue===s||Number.isNaN(s)||Number.isNaN(e.prevValue))f=[J(c(c({},t),{current:!0}))],i={transition:"none"};else{f=[];for(var b=s+10,u=[],g=s;g<=b;g+=1)u.push(g);var k=u.findIndex(function(m){return m%10===e.prevValue});f=u.map(function(m,y){var N=m%10;return J(c(c({},t),{value:N,offset:y-k,current:y===k}))});var x=e.prevCount<a.value?1:-1;i={transform:"translateY(".concat(-ft(e.prevValue,s,x),"00%)")}}return d("span",{class:"".concat(t.prefixCls,"-only"),style:i,onTransitionend:function(){return v()}},[f])}}}),mt=globalThis&&globalThis.__rest||function(n,t){var o={};for(var a in n)Object.prototype.hasOwnProperty.call(n,a)&&t.indexOf(a)<0&&(o[a]=n[a]);if(n!=null&&typeof Object.getOwnPropertySymbols=="function")for(var e=0,a=Object.getOwnPropertySymbols(n);e<a.length;e++)t.indexOf(a[e])<0&&Object.prototype.propertyIsEnumerable.call(n,a[e])&&(o[a[e]]=n[a[e]]);return o},pt={prefixCls:String,count:T.any,component:String,title:T.any,show:Boolean},vt=E({name:"ScrollNumber",inheritAttrs:!1,props:pt,setup:function(t,o){var a=o.attrs,e=o.slots,v=q("scroll-number",t),r=v.prefixCls;return function(){var f,i=c(c({},t),a);i.prefixCls;var s=i.count,b=i.title;i.show;var u=i.component,g=u===void 0?"sup":u,k=i.class,x=i.style,m=mt(i,["prefixCls","count","title","show","component","class","style"]),y=c(c({},m),{style:x,"data-show":t.show,class:W(r.value,k),title:b}),N=s;if(s&&Number(s)%1===0){var Z=String(s).split("");N=Z.map(function(l,B){return d(gt,{prefixCls:r.value,count:Number(s),value:l,key:Z.length-B},null)})}x&&x.borderColor&&(y.style=c(c({},x),{boxShadow:"0 0 0 1px ".concat(x.borderColor," inset")}));var z=nt((f=e.default)===null||f===void 0?void 0:f.call(e));return z&&z.length?M(z,{class:W("".concat(r.value,"-custom-component"))},!1):d(g,y,{default:function(){return[N]}})}}});function _(n){return rt.indexOf(n)!==-1}var yt=globalThis&&globalThis.__rest||function(n,t){var o={};for(var a in n)Object.prototype.hasOwnProperty.call(n,a)&&t.indexOf(a)<0&&(o[a]=n[a]);if(n!=null&&typeof Object.getOwnPropertySymbols=="function")for(var e=0,a=Object.getOwnPropertySymbols(n);e<a.length;e++)t.indexOf(a[e])<0&&Object.prototype.propertyIsEnumerable.call(n,a[e])&&(o[a[e]]=n[a[e]]);return o},ht=function(){return{prefix:String,color:{type:String},text:T.any,placement:{type:String,default:"end"}}},xt=E({name:"ABadgeRibbon",inheritAttrs:!1,props:ht(),slots:["text"],setup:function(t,o){var a=o.attrs,e=o.slots,v=q("ribbon",t),r=v.prefixCls,f=v.direction,i=h(function(){return _(t.color)}),s=h(function(){var b;return[r.value,"".concat(r.value,"-placement-").concat(t.placement),(b={},p(b,"".concat(r.value,"-rtl"),f.value==="rtl"),p(b,"".concat(r.value,"-color-").concat(t.color),i.value),b)]});return function(){var b,u,g=a.class,k=a.style,x=yt(a,["class","style"]),m={},y={};return t.color&&!i.value&&(m.background=t.color,y.color=t.color),d("div",P({class:"".concat(r.value,"-wrapper")},x),[(b=e.default)===null||b===void 0?void 0:b.call(e),d("div",{class:[s.value,g],style:c(c({},m),k)},[d("span",{class:"".concat(r.value,"-text")},[t.text||((u=e.text)===null||u===void 0?void 0:u.call(e))]),d("div",{class:"".concat(r.value,"-corner"),style:y},null)])])}}}),kt=function(){return{count:T.any,showZero:{type:Boolean,default:void 0},overflowCount:{type:Number,default:99},dot:{type:Boolean,default:void 0},prefixCls:String,scrollNumberPrefixCls:String,status:{type:String},size:{type:String,default:"default"},color:String,text:T.any,offset:Array,numberStyle:{type:Object,default:void 0},title:String}},St=E({name:"ABadge",Ribbon:xt,inheritAttrs:!1,props:kt(),slots:["text","count"],setup:function(t,o){var a=o.slots,e=o.attrs,v=q("badge",t),r=v.prefixCls,f=v.direction,i=h(function(){return t.count>t.overflowCount?"".concat(t.overflowCount,"+"):t.count}),s=h(function(){return t.status!==null&&t.status!==void 0||t.color!==null&&t.color!==void 0}),b=h(function(){return i.value==="0"||i.value===0}),u=h(function(){return t.dot&&!b.value}),g=h(function(){return u.value?"":i.value}),k=h(function(){var l=g.value===null||g.value===void 0||g.value==="";return(l||b.value&&!t.showZero)&&!u.value}),x=V(t.count),m=V(g.value),y=V(u.value);L([function(){return t.count},g,u],function(){k.value||(x.value=t.count,m.value=g.value,y.value=u.value)},{immediate:!0});var N=h(function(){var l;return l={},p(l,"".concat(r.value,"-status-dot"),s.value),p(l,"".concat(r.value,"-status-").concat(t.status),!!t.status),p(l,"".concat(r.value,"-status-").concat(t.color),_(t.color)),l}),Z=h(function(){return t.color&&!_(t.color)?{background:t.color}:{}}),z=h(function(){var l;return l={},p(l,"".concat(r.value,"-dot"),y.value),p(l,"".concat(r.value,"-count"),!y.value),p(l,"".concat(r.value,"-count-sm"),t.size==="small"),p(l,"".concat(r.value,"-multiple-words"),!y.value&&m.value&&m.value.toString().length>1),p(l,"".concat(r.value,"-status-").concat(t.status),!!t.status),p(l,"".concat(r.value,"-status-").concat(t.color),_(t.color)),l});return function(){var l,B,R,O=t.offset,D=t.title,$=t.color,G=e.style,U=ot(a,t,"text"),C=r.value,w=x.value,S=lt((B=a.default)===null||B===void 0?void 0:B.call(a));S=S.length?S:null;var Y=!!(!k.value||a.count),I=function(){if(!O)return c({},G);var A={marginTop:it(O[1])?"".concat(O[1],"px"):O[1]};return f.value==="rtl"?A.left="".concat(parseInt(O[0],10),"px"):A.right="".concat(-parseInt(O[0],10),"px"),c(c({},A),G)}(),Q=D!=null?D:typeof w=="string"||typeof w=="number"?w:void 0,F=Y||!U?null:d("span",{class:"".concat(C,"-status-text")},[U]),K=st(w)==="object"||w===void 0&&a.count?M(w!=null?w:(R=a.count)===null||R===void 0?void 0:R.call(a),{style:I},!1):null,H=W(C,(l={},p(l,"".concat(C,"-status"),s.value),p(l,"".concat(C,"-not-a-wrapper"),!S),p(l,"".concat(C,"-rtl"),f.value==="rtl"),l),e.class);if(!S&&s.value){var X=I.color;return d("span",P(P({},e),{},{class:H,style:I}),[d("span",{class:N.value,style:Z.value},null),d("span",{style:{color:X},class:"".concat(C,"-status-text")},[U])])}var tt=ct(S?"".concat(C,"-zoom"):"",{appear:!1}),j=c(c({},I),t.numberStyle);return $&&!_($)&&(j=j||{},j.background=$),d("span",P(P({},e),{},{class:H}),[S,d(ut,tt,{default:function(){return[bt(d(vt,{prefixCls:t.scrollNumberPrefixCls,show:Y,class:z.value,count:m.value,title:Q,style:j,key:"scrollNumber"},{default:function(){return[K]}}),[[dt,Y]])]}}),F])}}});export{St as B,xt as R};
