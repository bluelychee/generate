$(function(){
	$("#province").ProvinceCity();
	$("#province select").css("height","30px");
	$("#province select").css("margin-left","15px");
		
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").validate({
		rules:{
			imei:{
				required:true,
				minlength:12,
				maxlength:16
			},
			hotelName:{
				required:true,
			},
			hotelAddress:{
				required:true,
			},
			contacter:{
				required:true,
			},
			mobile:{
				required:true,
				isPhone:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: ctx+"/devInfo/saveJson" ,
				success: function(data){
					var index = parent.layer.getFrameIndex(window.name);
					window.parent.BaseData.load();
					parent.layer.msg('添加成功!',{icon:1,time:1000});
					parent.layer.close(index);
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('error!',{icon:1,time:1000});
				}
			});
		}
	});
});