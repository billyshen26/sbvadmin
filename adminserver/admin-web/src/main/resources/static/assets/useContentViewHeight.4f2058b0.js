var H=(t,i,e)=>new Promise((a,o)=>{var f=n=>{try{u(e.next(n))}catch(s){o(s)}},w=n=>{try{u(e.throw(n))}catch(s){o(s)}},u=n=>n.done?a(n.value):Promise.resolve(n.value).then(f,w);u((e=e.apply(t,i)).next())});import{bh as d,v as r,f as m,k as c}from"./index.26fcfc43.js";import{u as v}from"./useWindowSizeFn.d178ecd3.js";const l=Symbol();function p(t){return d(t,l,{native:!0})}const g=r(0),h=r(0);function b(){function t(e){g.value=e}function i(e){h.value=e}return{headerHeightRef:g,footerHeightRef:h,setHeaderHeight:t,setFooterHeight:i}}function k(){const t=r(window.innerHeight),i=r(window.innerHeight),e=m(()=>c(t)-c(g)-c(h)||0);v(()=>{t.value=window.innerHeight},100,{immediate:!0});function a(o){return H(this,null,function*(){i.value=o})}p({contentHeight:e,setPageHeight:a,pageHeight:i})}export{b as a,k as u};
