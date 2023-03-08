import{M as E,a as F}from"./index.910a177c.js";import{P as k}from"./index.894818cb.js";import{au as C,a as w,v as p,aw as t,o as g,j as B,z as r,i as s,n as a,B as d,k as A}from"./index.8936adfc.js";import{C as D}from"./index.774a9abb.js";import"./index.9d376619.js";/* empty css              */import"./index.144a57bb.js";import"./useWindowSizeFn.c4d53914.js";import"./FullscreenOutlined.03730ed4.js";import"./index.467c9ef6.js";import"./index.a51b36c2.js";import"./useSize.205cd3d8.js";import"./eagerComputed.5918221c.js";import"./useContentViewHeight.6afc3857.js";import"./ArrowLeftOutlined.398710a4.js";import"./index.01ed4ff8.js";import"./transButton.f5a81c82.js";import"./index.683b5e58.js";import"./index.0b5ddde1.js";import"./Col.4fe65e36.js";import"./useFlexGapSupport.3a88a899.js";import"./index.b51f6c0b.js";import"./useRefs.215ca6c8.js";import"./PlusOutlined.a447f765.js";const M=w({components:{MarkDown:E,PageWrapper:k,MarkdownViewer:F,ACard:D},setup(){const e=p(null),u=p(`
# \u6807\u9898h1

##### \u6807\u9898h5

**\u52A0\u7C97**
*\u659C\u4F53*
~~\u5220\u9664\u7EBF~~
[\u94FE\u63A5](https://github.com/vbenjs/vue-vben-admin)
\u2193\u5206\u5272\u7EBF\u2193

---


* \u65E0\u5E8F\u5217\u88681
  * \u65E0\u5E8F\u5217\u88681.1

1. \u6709\u5E8F\u5217\u88681
2. \u6709\u5E8F\u5217\u88682

* [ ] \u4EFB\u52A1\u5217\u88681
* [x] \u4EFB\u52A1\u5217\u88682

> \u5F15\u7528\u793A\u4F8B

\`\`\`js
// \u4EE3\u7801\u5757:
(() => {
  var htmlRoot = document.getElementById('htmlRoot');
  var theme = window.localStorage.getItem('__APP__DARK__MODE__');
  if (htmlRoot && theme) {
    htmlRoot.setAttribute('data-theme', theme);
    theme = htmlRoot = null;
  }
})();
\`\`\`

| \u8868\u683C | \u793A\u4F8B | \u{1F389}\uFE0F |
| --- | --- | --- |
| 1 | 2 | 3 |
| 4 | 5 | 6 |
`);function m(){const o=A(e);if(!o)return;o.getVditor().setTheme("dark","dark","dracula")}function l(o){u.value=o}function i(){u.value=""}return{value:u,toggleTheme:m,markDownRef:e,handleChange:l,clearValue:i}}}),V={class:"mt-2"};function R(e,u,m,l,i,o){const n=t("a-button"),c=t("MarkDown"),f=t("MarkdownViewer"),_=t("a-card"),h=t("PageWrapper");return g(),B(h,{title:"MarkDown\u7EC4\u4EF6\u793A\u4F8B"},{default:r(()=>[s("div",null,[a(n,{onClick:e.toggleTheme,class:"mb-2",type:"primary"},{default:r(()=>[d(" \u9ED1\u6697\u4E3B\u9898 ")]),_:1},8,["onClick"]),a(n,{onClick:e.clearValue,class:"mb-2",type:"default"},{default:r(()=>[d(" \u6E05\u7A7A\u5185\u5BB9 ")]),_:1},8,["onClick"]),a(c,{value:e.value,"onUpdate:value":u[0]||(u[0]=v=>e.value=v),onChange:e.handleChange,ref:"markDownRef",placeholder:"\u8FD9\u662F\u5360\u4F4D\u6587\u672C"},null,8,["value","onChange"])]),s("div",V,[a(_,{title:"Markdown Viewer \u7EC4\u4EF6\u6F14\u793A"},{default:r(()=>[a(f,{value:e.value},null,8,["value"])]),_:1})])]),_:1})}var ee=C(M,[["render",R]]);export{ee as default};
