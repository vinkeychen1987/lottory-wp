$.fn.extend({contextMenu:function(a,b){if(a.menu==undefined){return false}if(a.inSpeed==undefined){a.inSpeed=150}if(a.outSpeed==undefined){a.outSpeed=75}if(a.inSpeed==0){a.inSpeed=-1}if(a.outSpeed==0){a.outSpeed=-1}$(this).each(function(){var c=$(this);var d=$(c).offset();$(this).mousedown(function(g){var f=g;f.stopPropagation();$(this).mouseup(function(j){j.stopPropagation();var i=$(this);$(this).unbind("mouseup");if(f.button==2){$(".ui-context").hide();var l=$("#"+a.menu);if($(c).hasClass("disabled")){return false}var k={},h,m;if(self.innerHeight){k.pageYOffset=self.pageYOffset;k.pageXOffset=self.pageXOffset;k.innerHeight=self.innerHeight;k.innerWidth=self.innerWidth}else{if(document.documentElement&&document.documentElement.clientHeight){k.pageYOffset=document.documentElement.scrollTop;k.pageXOffset=document.documentElement.scrollLeft;k.innerHeight=document.documentElement.clientHeight;k.innerWidth=document.documentElement.clientWidth}else{if(document.body){k.pageYOffset=document.body.scrollTop;k.pageXOffset=document.body.scrollLeft;k.innerHeight=document.body.clientHeight;k.innerWidth=document.body.clientWidth}}}(j.pageX)?h=j.pageX:h=j.clientX+k.scrollLeft;(j.pageY)?m=j.pageY:m=j.clientY+k.scrollTop;$(document).unbind("click");$(l).css({top:m,left:h}).fadeIn(a.inSpeed);$(l).find("A").mouseover(function(){$(l).find("LI.hover").removeClass("hover");$(this).parent().addClass("hover")}).mouseout(function(){$(l).find("LI.hover").removeClass("hover")});$(document).keypress(function(n){switch(n.keyCode){case 38:if($(l).find("LI.hover").size()==0){$(l).find("LI:last").addClass("hover")}else{$(l).find("LI.hover").removeClass("hover").prevAll("LI:not(.disabled)").eq(0).addClass("hover");if($(l).find("LI.hover").size()==0){$(l).find("LI:last").addClass("hover")}}break;case 40:if($(l).find("LI.hover").size()==0){$(l).find("LI:first").addClass("hover")}else{$(l).find("LI.hover").removeClass("hover").nextAll("LI:not(.disabled)").eq(0).addClass("hover");if($(l).find("LI.hover").size()==0){$(l).find("LI:first").addClass("hover")}}break;case 13:$(l).find("LI.hover A").trigger("click");break;case 27:$(document).trigger("click");break}});$("#"+a.menu).find("A").unbind("click");$("#"+a.menu).find("LI:not(.disabled) A").click(function(){$(document).unbind("click").unbind("keypress");$(".ui-context").hide();if(b){b($(this).attr("href").substr(1),$(i),{x:h-d.left,y:m-d.top,docX:h,docY:m})}return false});setTimeout(function(){$(document).click(function(){$(document).unbind("click").unbind("keypress");$(l).fadeOut(a.outSpeed);return false})},0)}})});if($.browser.mozilla){$("#"+a.menu).each(function(){$(this).css({MozUserSelect:"none"})})}else{if($.browser.msie){$("#"+a.menu).each(function(){$(this).bind("selectstart.disableTextSelect",function(){return false})})}else{$("#"+a.menu).each(function(){$(this).bind("mousedown.disableTextSelect",function(){return false})})}}$(c).add($("ul.ui-context")).bind("contextmenu",function(){return false})});return $(this)}});