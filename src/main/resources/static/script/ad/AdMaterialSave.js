Array.prototype.contains = function(item){
	for(var i in this){
		if(this[i] == item) return item;
	}
	return false;
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$('body').on('change', '#file1', function() {
		var supports= new Array("mp4","jpg");
		fileDom = this;
		var files = $(fileDom)[0].files;
		if(files!=null && files.length >0){
			var name = files[0].name;
			var suffix = name.replace(/^.*\./,"");
			if(!supports.contains(suffix)){
				var file = $(this)   
				file.after(file.clone().val(""));     
				file.remove();
				$("#uploadfile-2").val('');
				layer.msg('不支持的文件类型:'+suffix+'!',{icon:0,time:1000});
			}
		}
	});
	
	var xhrOnProgress = function(fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        return function() {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();
            //判断监听函数是否为函数
            if (typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if (xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }
	
	$("#form-admin-add").validate({
		rules:{
			name:{
				required:true,
			},
			locate:{
				required:true,
			},
			remark:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			var files = $("#file1")[0].files;
			
			if(files==null ||files == undefined ||files.length ==0){
				layer.msg('请选择文件上传!',{icon:0,time:1000});
				return;
			}
			
			var formData = new FormData($('#form-admin-add')[0]);
			$.ajax({
				type: 'post',
				url: ctx+"/adMaterial/saveJson" ,
				data:formData,
				 contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
	             processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
	             xhr: xhrOnProgress(function(e){// (详见：#3)
	            	 var percent=e.loaded / e.total;//计算百分比
	            	 console.log(percent);
	             }),
				
				success: function(data){
					var index = parent.layer.getFrameIndex(window.name);
					window.parent.BaseData.load();
					parent.layer.msg('添加成功!',{icon:1,time:1000});
					parent.layer.close(index);
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('error!',{icon:0,time:1000});
				}
			});
		}
	});
});