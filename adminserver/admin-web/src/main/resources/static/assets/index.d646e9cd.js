import{C as r}from"./index.a07b5b75.js";import{a as P,J as D,S,c6 as o,n as e,G as s}from"./index.21dbddea.js";import{C as c}from"./Grid.b197fff5.js";var x=function(){return{prefixCls:String,title:s.any,description:s.any,avatar:s.any}},l=P({name:"ACardMeta",props:x(),slots:["title","description","avatar"],setup:function(n,p){var i=p.slots,C=D("card",n),t=C.prefixCls;return function(){var M=S({},"".concat(t.value,"-meta"),!0),v=o(i,n,"avatar"),d=o(i,n,"title"),m=o(i,n,"description"),g=v?e("div",{class:"".concat(t.value,"-meta-avatar")},[v]):null,u=d?e("div",{class:"".concat(t.value,"-meta-title")},[d]):null,f=m?e("div",{class:"".concat(t.value,"-meta-description")},[m]):null,y=u||f?e("div",{class:"".concat(t.value,"-meta-detail")},[u,f]):null;return e("div",{class:M},[g,y])}}});r.Meta=l;r.Grid=c;r.install=function(a){return a.component(r.name,r),a.component(l.name,l),a.component(c.name,c),a};
