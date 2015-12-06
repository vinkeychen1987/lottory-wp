$(document).ready(function (){
	// 上传头像
	// 创建文件上传插件
	var uploadEl = $("#selectFaceImageFileBtn");
	var sessionId = uploadEl.attr('_s');
    uploadEl.uploadify({
    	'formData': {'jsessionid':sessionId},
    	'fileObjName' : 'image', 
        'swf'       : '/uploadify/uploadify.swf',
        'uploader'  : '/ajax_upload_head_image',
        'width'     : 102,
        'height'    : 40,
        'buttonText': '',
        'buttonImage' : '/images/select-file.png',
        'fileTypeDesc':'Image Files',    //可上传文件格式的描述
        'fileTypeExts':'*.jpeg; *.jpg; *.png',    //可上传的文件格式 
        'multi':false,
        'fileSizeLimit' : '2048KB',
        'onUploadSuccess' : function(file, data, response) {
        	data = $.parseJSON(data);
        	if (data.success == true) {
				$("#previewImg").attr("src",data.imageURL);
				$("#headImage").attr("src",data.imageURL);
				// 将头像地址设置到隐藏域
				jQuery('#headImageData_headImageURL').attr("value", data.imageURL);
				$("#submitFaceButton").attr("disabled",false);
			} else {
				alert(data.desc);
			}
        },
        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#head_image_progress').html('正在上传...'+parseInt(parseInt(bytesUploaded)/parseInt(bytesTotal)*100) + '%');
        },
        'onSelectError' : uploadify_onUploadError,
        'onUploadError' : uploadify_onUploadError,  
        'overrideEvents' : ['onSelectError','onUploadError','onDialogClose'],
        'onDialogClose'  : function(queueData) {
            
        }
    });
    function uploadify_onUploadError(file, errorCode, errorMsg) {
    	if(errorCode == SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED){
    		alert("\n只能选择一个文件");
    	} else if(errorCode == SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT){
    		alert("\n文件超过2M限制");
    	} else if(errorCode == SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE){
    		alert("\n文件大小为0字节");
    	} else if(errorCode == SWFUpload.QUEUE_ERROR.INVALID_FILETYPE){
    		alert("请选择jpg、jpeg和png文件格式的图片");
    	} else {
    		alert(errorMsg);
    	}
    	return false;
    }

	function checkUploadStatus(){
		$.getJSON("/upload/progress?X-Progress-ID=" + uploadImgUuid , function (data){
			if(data){
				var bar = $("#progress_bar");
				var bar_value = $("#progress_bar_value");
				bar.show();
				var received = 0;
                var total = 1;
                if (data.state == 'done' || data.state == 'uploading'){
                	received = data.received;
                	total = data.size;
                }
				if(data.state == 'uploading'){
					var progress = parseInt(parseInt(received)/parseInt(total)*100);
					bar_value.css("width",progress + "%");
					bar_value.html(progress + "%");
					setTimeout(checkUploadStatus,20);
				} else if(data.state == 'done'){
					bar_value.css("width",100 + "%");
					bar_value.html(100 + "%");
				}
			}
		}).error(function (){
			$("#progress_bar").hide();
		});
	}
	function updateImageStatus() {
		var w = $('img#headImage').width();
		var h = $('img#headImage').height();
		var minScale = w > h ? h : w;

		jQuery('#headImageData_x1').attr("value", minScale * 0.2);
		jQuery('#headImageData_y1').attr("value", minScale * 0.2);
		jQuery('#headImageData_x2').attr("value", minScale * 0.8);
		jQuery('#headImageData_y2').attr("value", minScale * 0.8);

		var _ias = $('img#headImage').imgAreaSelect({
			instance : true
		});

		_ias.setSelection(minScale * 0.2, minScale * 0.2,
				minScale * 0.8, minScale * 0.8, true);
		_ias.setOptions({
			show : true
		});
		_ias.update();
	}
	// 切割并保存
	$("#submitFaceButton").click(function(event) {
		$("#profile-form").attr("enctype","application/x-www-form-urlencoded");
		$("#profile-form").attr("action","/save_face_after_regist");
		$("#profile-form").submit();
		$(this).attr("disabled", true);
	});
	
	jQuery('#headImageData_x1').attr("value", 20);
	jQuery('#headImageData_y1').attr("value", 20);
	jQuery('#headImageData_x2').attr("value", 160);
	jQuery('#headImageData_y2').attr("value", 160);
	jQuery("img#headImage").imgAreaSelect({
		x1 : 20,
		y1 : 20,
		x2 : 160,
		y2 : 160,
		aspectRatio : "1:1",
		onSelectChange : function(img, selection) {
			//var width= ((180 - selection.width))/selection.width*50 + 50;
			//var height= ((180 - selection.width))/selection.height*50 + 50;
			//jQuery("img#previewImg").css("width",width + "px");
			//jQuery("img#previewImg").css("height",height + "px");

			realWidth = img.width;
			realHeight = img.height;
			sizeWidth = 50;
			sizeHeight = 50;

			var scaleX = sizeWidth / selection.width;
			var scaleY = sizeHeight / selection.height;
			jQuery('img#previewImg').css({
				width : Math.round(scaleX * realWidth) + 'px',
				height : Math.round(scaleY * realHeight) + 'px',
				marginLeft : -Math.round(scaleX * selection.x1) + 'px',
				marginTop : -Math.round(scaleY * selection.y1) + 'px'
			});
			jQuery('#headImageData_x1').attr("value", selection.x1);
			jQuery('#headImageData_y1').attr("value", selection.y1);
			jQuery('#headImageData_x2').attr("value", selection.x2);
			jQuery('#headImageData_y2').attr("value", selection.y2);
			$("#submitFaceButton").attr("disabled", false);
		}
	});
});
