Jet().$package(function(J){
	var $ = jQuery, $E = Jooe, Editor = xhcms.cms.Editor, $base = base_path;
	var mask = null;
	var issueEditor = function(){};
	issueEditor.prototype = {
		init: function(option){
			mask = new xhcms.ui.MaskLayer({
				zIndex: 10000
			});
			option = option || {};
			option.title = option.title || '编辑期次';
			
			this._super.init(option);
			this.setXY(this.getX(),this.getY()+20);
			
			this.observer = {
				save: function(e, data){
					var url = $base + '/match/aj_editjx.do';
					if(option.data.mtype == 1){
						url = $base + '/match/aj_editctfb.do';
					}
					$.get(url, data, function(resp){
						$.parseJSONData($.parseJSON(resp), function(data){
							if($.isFunction(option.callback)){
								option.callback();
							}
						});
					});
					mask.hide();
					return true;
				}
			};
			
			$E.addObserver(this, Editor.EVENT.ClickOkButton, this.observer.save);
		},
		initData : function(){
			var idata = this;
			var o = this.option.data;
			var html = 
				'<div class="tbebox tbebox_B">'
				+ '<table class="tbel" cellspacing="0" cellpadding="0">'
				+ '<tr><td class="tdl tar" style="width:100px;">期次ID：</td><td class="tdr tal">{0}</td></tr>'
				+ '<tr><td class="tdl tar">期号：</td><td class="tdr tal">{1}</td></tr>';
			if(o.mtype == 0){
				html = html
				+ '<tr><td class="tdl tar">开始接票时间：</td><td class="tdr tal">{2}</td></tr>'; 
			}
			html = html
				+ '<tr><td class="tdl tar">截止时间：</td><td class="tdr tal">{3}</td></tr>'
				+ '<tr><td class="tdl tar">开奖号码：</td><td class="tdr tal">{4}&nbsp;</td></tr>'
				+ '<tr><td class="tdl tar">开奖信息：</td><td class="tdr tal">{5}</td></tr>'
				+ '<tr class="last"><td class="tdl tar">状态：</td><td class="tdr tal"><select name="status">'
				/*+ '<option value="0">未开售</option>'
				+ '<option value="1">销售中</option>'
				+ '<option value="2">已截止</option>'
				+ '<option value="3">已开奖</option>'
				+ '<option value="4">已派奖</option>'*/
				+'{7}'
				+ '</select></td></tr>'
				/*+ '<tr class="last"><td class="tdl tar">是否有效：</td><td class="tdr tal"><select name="valid" id="tvalid">'
				+'{8}'
				+ '</select></td></tr>'*/
				+ '<input type="hidden" name="id" value="{6}"/>'
				+ '<input type="hidden" id="valid" name="valid" value="{8}"/>'
				+ '</table></div>';
			var options="";
			 if(o.status==0){
				 options+="<option value='0' selected='selected'>未开售</option>";
			 }else{
				 options+="<option value='0'>未开售</option>";
			 }
			 if(o.status==1){
				 options+="<option value='1' selected='selected'>销售中</option>";
			 }else{
				 options+="<option value='1'>销售中</option>";
			 }
			 if(o.status==2){
				 options+="<option value='2' selected='selected'>已截止</option>";
			 }else{
				 options+="<option value='2'>已截止</option>";
			 }
			 if(o.status==3){
				 options+="<option value='3' selected='selected'>已开奖</option>";
			 }else{
				 options+="<option value='3'>已开奖</option>";
			 }
			 if(o.status==4){
				 options+="<option value='4' selected='selected'>已派奖</option>";
			 }else{
				 options+="<option value='4'>已派奖</option>";
			 }
			/*var options="";
			 if(o.valid == 'false'){
				 options+="<option value='0' selected='selected'>无效</option><option value='1'>有效</option>";
			 }else if(o.valid == 'true'){
				 options+="<option value='0'>无效</option><option value='1' selected='selected'>有效</option>";
			 }*/
			 var valid=false;
			 if(o.status==0){
				 valid=false;
			 }else{
				 valid=true;
			 }
			html = $.format(html, o.id, o.issueNumber, o.startTime, o.stopTime, o.bonusCode, o.bonusInfo, o.id,options,valid);
			
			if(o.mtype == 1){
				var murl = '/match/ctfbmatch.do?issueNumber='+o.issueNumber+'&playId='+o.playId;
				$.getJSON(murl, function(data){
					if(data.success){
						html = html
							+ '<div class="tbebox tbebox_B">'
							+ '<table cellspacing="0" class="tab"><tr class="">'
							+ '<th style="width:60px;text-align:center;"><span>赛事ID</span></th>'
							+ '<th style="width:70px;text-align:center;"><span>赛事编号</span></th>'
							+ '<th style="width:140px;text-align:center;"><span>联赛名称</span></th>'
							+ '<th style="width:200px;text-align:center;"><span>主队VS客队</span></th>'
							+ '<th style="width:110px;text-align:center;"><span>预计停售时间</span></th>'
							+ '<th style="width:110px;text-align:center;"><span>开始时间</span></th>'
							+ '</tr>';
						
						$.each(data.data, function(i, d){
							html = html
								+ '<tr><td style="text-align:center;">'+d.id+'</td>'
								+ '<td style="text-align:center;">'+d.matchId+'</td>'
								+ '<td style="text-align:center;">'+d.leagueName+'</td>'
								+ '<td style="text-align:center;">'+d.homeTeamName+'VS'+d.guestTeamName+'</td>'
								+ '<td style="text-align:center;">'+d.offtime.replace("T"," ")+'</td>'
								+ '<td style="text-align:center;">'+d.playingTime.replace("T"," ")+'</td>'
								+ '</tr>';
						});
						html = html + '</table></div>';
					}
				});
			}
			//时间延迟1秒
			setTimeout(function(){
				idata.setHtml(html);
			}, 1000);
			
			setTimeout(function(){
				//$('select[name="status"]', this.body).val(o.status);
				$("#valid").val(o.status);
			}, 1);
			mask.show();
		},
		clickOkButton: function(){
			var data = {};
			$(':input', this.body).each(function(e){
				data[this.name] = $.trim(this.value);
			});
			
			return this.trigger(Editor.EVENT.ClickOkButton, data);
		},
		Close: function(){
			mask.hide();
		}
	};
	issueEditor = Editor.extend(issueEditor.prototype);
	this.issueEditor = issueEditor;
	
	$("select[name='status']").live('change',function(){
		var v=$(this).val();
		if(v==0){
			$("#valid").val(false);
		}else{
			$("#valid").val(true);
		}
	});
});