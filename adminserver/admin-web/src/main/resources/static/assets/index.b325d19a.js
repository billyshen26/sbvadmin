import{a as j,H as F,v as O,K as P,J as _,_ as S,c1 as I,R as B,S as s,n as u,T as g,G as E}from"./index.26fcfc43.js";import{V as G}from"./Checkbox.2a1a359d.js";var T=globalThis&&globalThis.__rest||function(t,e){var d={};for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&e.indexOf(r)<0&&(d[r]=t[r]);if(t!=null&&typeof Object.getOwnPropertySymbols=="function")for(var n=0,r=Object.getOwnPropertySymbols(t);n<r.length;n++)e.indexOf(r[n])<0&&Object.prototype.propertyIsEnumerable.call(t,r[n])&&(d[r[n]]=t[r[n]]);return d},U=function(){return{prefixCls:String,checked:{type:Boolean,default:void 0},disabled:{type:Boolean,default:void 0},isGroup:{type:Boolean,default:void 0},value:E.any,name:String,id:String,autofocus:{type:Boolean,default:void 0},onChange:Function,onFocus:Function,onBlur:Function,onClick:Function,"onUpdate:checked":Function,"onUpdate:value":Function}},A=j({name:"ARadio",props:U(),setup:function(e,d){var r=d.emit,n=d.expose,f=d.slots,h=F(),b=O(),c=P("radioGroupContext",void 0),w=_("radio",e),p=w.prefixCls,x=w.direction,v=function(){b.value.focus()},k=function(){b.value.blur()};n({focus:v,blur:k});var m=function(a){var l=a.target.checked;r("update:checked",l),r("update:value",l),r("change",a),h.onFieldChange()},y=function(a){r("change",a),c&&c.onRadioChange&&c.onRadioChange(a)};return function(){var o,a=c;e.prefixCls;var l=e.id,C=l===void 0?h.id.value:l,z=T(e,["prefixCls","id"]),i=S({prefixCls:p.value,id:C},I(z,["onUpdate:checked","onUpdate:value"]));a?(i.name=a.props.name,i.onChange=y,i.checked=e.value===a.stateValue.value,i.disabled=e.disabled||a.props.disabled):i.onChange=m;var R=B((o={},s(o,"".concat(p.value,"-wrapper"),!0),s(o,"".concat(p.value,"-wrapper-checked"),i.checked),s(o,"".concat(p.value,"-wrapper-disabled"),i.disabled),s(o,"".concat(p.value,"-wrapper-rtl"),x.value==="rtl"),o));return u("label",{class:R},[u(G,g(g({},i),{},{type:"radio",ref:b}),null),f.default&&u("span",null,[f.default()])])}}});export{A as R,U as r};
