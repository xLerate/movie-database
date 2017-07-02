YUI.add("node-flick",function(e,t){function b(e){b.superclass.constructor.apply(this,arguments)}var n="host",r="parentNode",i="boundingBox",s="offsetHeight",o="offsetWidth",u="scrollHeight",a="scrollWidth",f="bounce",l="minDistance",c="minVelocity",h="bounceDistance",p="deceleration",d="step",v="duration",m="easing",g="flick",y=e.ClassNameManager.getClassName;b.ATTRS={deceleration:{value:.98},bounce:{value:.7},bounceDistance:{value:150},minVelocity:{value:0},minDistance:{value:10},boundingBox:{valueFn:function(){return this.get(n).get(r)}},step:{value:10},duration:{value:null},easing:{value:null}},b.NAME="pluginFlick",b.NS="flick",e.extend(b,e.Plugin.Base,{initializer:function(t){this._node=this.get(n),this._renderClasses(),this.setBounds(),this._node.on(g,e.bind(this._onFlick,this),{minDistance:this.get(l),minVelocity:this.get(c)})},setBounds:function(){var e=this.get(i),t=this._node,n=e.get(s),r=e.get(o),f=t.get(u),l=t.get(a);f>n&&(this._maxY=f-n,this._minY=0,this._scrollY=!0),l>r&&(this._maxX=l-r,this._minX=0,this._scrollX=!0),this._x=this._y=0,t.set("top",this._y+"px"),t.set("left",this._x+"px")},_renderClasses:function(){this.get(i).addClass(b.CLASS_NAMES.box),this._node.addClass(b.CLASS_NAMES.content)},_onFlick:function(e){this._v=e.flick.velocity,this._flick=!0,this._flickAnim()},_flickAnim:function(){var t=this._y,n=this._x,r=this._maxY,i=this._minY,s=this._maxX,o=this._minX,u=this._v,a=this.get(d),l=this.get(p),c=this.get(f);this._v=u*l,this._snapToEdge=!1,this._scrollX&&(n-=u*a),this._scrollY&&(t-=u*a),Math.abs(u).toFixed(4)<=b.VELOCITY_THRESHOLD?(this._flick=!1,this._killTimer(!this._exceededYBoundary&&!this._exceededXBoundary),this._scrollX&&(n<o?(this._snapToEdge=!0,this._setX(o)):n>s&&(this._snapToEdge=!0,this._setX(s))),this._scrollY&&(t<i?(this._snapToEdge=!0,this._setY(i)):t>r&&(this._snapToEdge=!0,this._setY(r)))):(this._scrollX&&(n<o||n>s)&&(this._exceededXBoundary=!0,this._v*=c),this._scrollY&&(t<i||t>r)&&(this._exceededYBoundary=!0,this._v*=c),this._scrollX&&this._setX(n),this._scrollY&&this._setY(t),this._flickTimer=e.later(a,this,this._flickAnim))},_setX:function(e){this._move(e,null,this.get(v),this.get(m))},_setY:function(e){this._move(null,e,this.get(v),this.get(m))},_move:function(e,t,n,r){e!==null?e=this._bounce(e):e=this._x,t!==null?t=this._bounce(t):t=this._y,n=n||this._snapToEdge?b.SNAP_DURATION:0,r=r||this._snapToEdge?b.SNAP_EASING:b.EASING,this._x=e,this._y=t,this._anim(e,t,n,r)},_anim:function(t,n,r,i){var s=t*-1,o=n*-1,u={duration:r/1e3,easing:i};e.Transition.useNative?u.transform="translate("+s+"px,"+o+"px)":(u.left=s+"px",u.top=o+"px"),this._node.transition(u)},_bounce:function(e,t){var n=this.get(f),r=this.get(h),i=n?-r:0;return t=n?t+r:t,n||(e<i?e=i:e>t&&(e=t)),e},_killTimer:function(){this._flickTimer&&this._flickTimer.cancel()}},{VELOCITY_THRESHOLD:.015,SNAP_DURATION:400,EASING:"cubic-bezier(0, 0.1, 0, 1.0)",SNAP_EASING:"ease-out",CLASS_NAMES:{box:y(b.NS),content:y(b.NS,"content")}}),e.Plugin.Flick=b},"patched-v3.11.0",{requires:["classnamemanager","transition","event-flick","plugin"],skinnable:!0});