(window.webpackJsonp=window.webpackJsonp||[]).push([[24],{158:function(e,t,n){"use strict";function r(){return(r=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e}).apply(this,arguments)}n.d(t,"a",(function(){return r}))},159:function(e,t,n){"use strict";function r(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}n.d(t,"a",(function(){return r}))},160:function(e,t,n){"use strict";n.d(t,"a",(function(){return l})),n.d(t,"b",(function(){return s}));var r=n(0),o=n.n(r);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function c(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?c(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):c(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function u(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var d=o.a.createContext({}),b=function(e){var t=o.a.useContext(d),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},l=function(e){var t=b(e.components);return o.a.createElement(d.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},f=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,c=e.parentName,d=u(e,["components","mdxType","originalType","parentName"]),l=b(n),f=r,s=l["".concat(c,".").concat(f)]||l[f]||p[f]||a;return n?o.a.createElement(s,i(i({ref:t},d),{},{components:n})):o.a.createElement(s,i({ref:t},d))}));function s(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,c=new Array(a);c[0]=f;var i={};for(var u in t)hasOwnProperty.call(t,u)&&(i[u]=t[u]);i.originalType=e,i.mdxType="string"==typeof e?e:r,c[1]=i;for(var d=2;d<a;d++)c[d]=n[d];return o.a.createElement.apply(null,c)}return o.a.createElement.apply(null,n)}f.displayName="MDXCreateElement"},91:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return c})),n.d(t,"metadata",(function(){return i})),n.d(t,"rightToc",(function(){return u})),n.d(t,"default",(function(){return b}));var r=n(158),o=n(159),a=(n(0),n(160)),c={title:"Frontend Docker Image",hide_title:!0,slug:"/docker/datahub-frontend",custom_edit_url:"https://github.com/linkedin/datahub/blob/master/docker/datahub-frontend/README.md"},i={unversionedId:"docker/datahub-frontend/README",id:"docker/datahub-frontend/README",isDocsHomePage:!1,title:"Frontend Docker Image",description:"DataHub Frontend Docker Image",source:"@site/genDocs/docker/datahub-frontend/README.md",slug:"/docker/datahub-frontend",permalink:"/docs/docker/datahub-frontend",editUrl:"https://github.com/linkedin/datahub/blob/master/docker/datahub-frontend/README.md",version:"current"},u=[{value:"Checking out DataHub UI",id:"checking-out-datahub-ui",children:[]}],d={rightToc:u};function b(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(a.b)("wrapper",Object(r.a)({},d,n,{components:t,mdxType:"MDXLayout"}),Object(a.b)("h1",{id:"datahub-frontend-docker-image"},"DataHub Frontend Docker Image"),Object(a.b)("p",null,Object(a.b)("a",{parentName:"p",href:"https://github.com/linkedin/datahub/actions?query=workflow%3A%22datahub-frontend+docker%22"},Object(a.b)("img",{parentName:"a",src:"https://github.com/linkedin/datahub/workflows/datahub-frontend%20docker/badge.svg",alt:"datahub-frontend docker"}))),Object(a.b)("p",null,"Refer to ",Object(a.b)("a",{parentName:"p",href:"https://github.com/linkedin/datahub/blob/master/datahub-frontend"},"DataHub Frontend Service")," to have a quick understanding of the architecture and\nresponsibility of this service for the DataHub."),Object(a.b)("h2",{id:"checking-out-datahub-ui"},"Checking out DataHub UI"),Object(a.b)("p",null,"After starting your Docker container, you can connect to it by typing below into your favorite web browser:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre"},"http://localhost:9001\n")),Object(a.b)("p",null,"You can sign in with ",Object(a.b)("inlineCode",{parentName:"p"},"datahub")," as username and password."))}b.isMDXComponent=!0}}]);