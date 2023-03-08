import{_ as i,a as T,c1 as g,dJ as V,O as E,v as k,J as D,S as d,$ as J,Y as R,n as O,cV as w,T as L,Q}from"./index.21dbddea.js";var _=function(){return null};_.isSelectOption=!0;_.displayName="AAutoCompleteOption";var p=_,y=function(){return null};y.isSelectOptGroup=!0;y.displayName="AAutoCompleteOptGroup";var S=y;function U(o){var e,u;return((e=o==null?void 0:o.type)===null||e===void 0?void 0:e.isSelectOption)||((u=o==null?void 0:o.type)===null||u===void 0?void 0:u.isSelectOptGroup)}var W=function(){return i(i({},g(V(),["loading","mode","optionLabelProp","labelInValue"])),{dataSource:Array,dropdownMenuStyle:{type:Object,default:void 0},dropdownMatchSelectWidth:{type:[Number,Boolean],default:!0},prefixCls:String,showSearch:{type:Boolean,default:void 0},transitionName:String,choiceTransitionName:{type:String,default:"zoom"},autofocus:{type:Boolean,default:void 0},backfill:{type:Boolean,default:void 0},filterOption:{type:[Boolean,Function],default:!1},defaultActiveFirstOption:{type:Boolean,default:!0}})},C=T({name:"AAutoComplete",inheritAttrs:!1,props:W(),slots:["option"],setup:function(e,u){var n=u.slots,b=u.attrs,B=u.expose;E(!("dataSource"in n),"AutoComplete","`dataSource` slot is deprecated, please use props `options` instead."),E(!("options"in n),"AutoComplete","`options` slot is deprecated, please use props `options` instead.");var f=k(),G=function(){var t,r=Q((t=n.default)===null||t===void 0?void 0:t.call(n)),s=r.length?r[0]:void 0;return s},F=function(){var t;(t=f.value)===null||t===void 0||t.focus()},j=function(){var t;(t=f.value)===null||t===void 0||t.blur()};B({focus:F,blur:j});var I=D("select",e),c=I.prefixCls;return function(){var a,t,r,s,x=e.size,A=e.dataSource,h=e.notFoundContent,P=h===void 0?(t=n.notFoundContent)===null||t===void 0?void 0:t.call(n):h,v,N=b.class,z=(a={},d(a,N,!!N),d(a,"".concat(c.value,"-lg"),x==="large"),d(a,"".concat(c.value,"-sm"),x==="small"),d(a,"".concat(c.value,"-show-search"),!0),d(a,"".concat(c.value,"-auto-complete"),!0),a);if(e.options===void 0){var m=((r=n.dataSource)===null||r===void 0?void 0:r.call(n))||((s=n.options)===null||s===void 0?void 0:s.call(n))||[];m.length&&U(m[0])?v=m:v=A?A.map(function(l){if(J(l))return l;switch(R(l)){case"string":return O(p,{key:l,value:l},{default:function(){return[l]}});case"object":return O(p,{key:l.value,value:l.value},{default:function(){return[l.text]}});default:throw new Error("AutoComplete[dataSource] only supports type `string[] | Object[]`.")}}):[]}var $=g(i(i(i({},e),b),{mode:w.SECRET_COMBOBOX_MODE_DO_NOT_USE,getInputElement:G,notFoundContent:P,class:z,ref:f}),["dataSource","loading"]);return O(w,$,L({default:function(){return[v]}},g(n,["default","dataSource","options"])))}}}),Y=i(C,{Option:p,OptGroup:S,install:function(e){return e.component(C.name,C),e.component(p.displayName,p),e.component(S.displayName,S),e}});export{Y as A};
