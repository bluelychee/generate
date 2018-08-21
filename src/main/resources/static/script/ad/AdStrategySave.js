$(function(){
	$.get("/adMaterial/listJson", function(data){
		var html="";
		for(var i in data){
			html+="<option value=\""+data[i].id+"\""+(data[i].id ==adMaterialId?"selected":"")+">"+data[i].name+"</option>";
		}
		
		$("#adMaterialId").html(html);
	});
	
	$("#form-admin-add").validate({
		rules:{
			custName:{
				required:true,
			},
			custTel:{
				required:true,
				isPhone:true,
			},
			adMaterialId:{
				required:true,
			},
			timeStart:{
				required:true,
			},
			timeEnd:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: ctx+"/adStrategy/saveJson" ,
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