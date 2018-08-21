var BaseData = {
	module:null,
	formId:null,
	propFun:null,
	
	init : function(module,formId,propFun) {
		this.module = module;
		this.formId =formId;
		this.propFun=propFun;
		this.bindEvent();
		this.load();
	},
	load : function() {
		var _this = this;
		var url = "/"+BaseData.module+"/listPageJson";
		var data =$('#'+BaseData.formId).serializeJson();
		var index = layer.load(1, {
			shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
		
		$.post(url, data, function(datas) {
			layer.close(index);
			_this.list_tpl(datas);
			_this.page_load(datas);
		});
	},
	page_load: function(resultData) {
		$("#totalResult").val(resultData.totalRecord);
		$("#showCount").val(resultData.pageSize);
		var _this = this;
	    var laypage = layui.laypage,
	    layer = layui.layer;
	    //完整功能
	    laypage.render({
	        elem: 'tcdPageCode',
	        count: resultData.totalRecord,
	        curr: resultData.page, //初始化当前页
	        limit: resultData.pageSize,
	        layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
	        jump: function(obj, first) {
	        	if(!first){
	        		$("#currentPage").val(obj.curr);
	        		$("#showCount").val(obj.limit);
		        	_this.load();
	        	}
	        }
	    });
	},
	list_tpl : function(datas) {
		var _this = this;
		$('#totalRecord').html(datas.totalRecord);
		if(BaseData.propFun!=null && BaseData.propFun!=undefined) BaseData.propFun(datas);
		
		var output = Mustache.render($('#jUserListTpl').html(), datas);
		$('#jUserListBody').html(output);
	},
	bindEvent : function() {
		var _this = this;
		$('#'+BaseData.formId).on('click', '.btnSearch', function() {
			$("#currentPage").val(1);
			_this.load();
		});
		
		$('#'+BaseData.formId).on('click', '.btnAdd', function() {
			var title = '添加用户';
			var url = '/'+BaseData.module+'/savePage';
			layer_show(title,url,800,500);
		});
		
		$('#'+BaseData.formId).on('click', '.btnDel', function() {
			var ids="";
			$.each($(".ids"),function(){
                if(this.checked){
                	if(ids!="")ids+=",";
                	ids+=$(this).val();
                }
            });
			
			if(ids.length>0){
				layer.confirm('确认要删除吗？', function(index) {
					var url = "/"+BaseData.module+"/saveJson";
					$.post(url, {"ids":""+ids,"isDel":1}, function(datas) {
						_this.load();
						parent.layer.msg('删除成功!',{icon:1,time:1000});
						layer.close(index);
					});
				});
			}else{
				layer.alert('请先勾选要删除的数据!');
			}
		});
		
		$('#'+BaseData.formId).on('click', '.aStatus', function() {
			var status = $(this).data('status');
			var id = $(this).data('id');;
			
			layer.confirm('确认要'+(status == 1?'禁用':'启用')+'吗？', function(index) {
				var url = "/"+BaseData.module+"/saveJson";
				$.post(url, {"id":id,"status":(status == 1?'0':'1')}, function(datas) {
					parent.layer.msg((status == 1?'禁用':'启用')+'成功!',{icon:1,time:1000});
					_this.load();
					layer.close(index);
				});
			});
		});

		$('#'+BaseData.formId).on('click', '.aEdit', function() {
			var id = $(this).data('id');
			var title = '编辑用户';
			var url = '/'+BaseData.module+'/savePage?id=' + id;
			layer_show(title,url,800,500);
		});

		$('#'+BaseData.formId).on('click', '.aDel', function() {
			var id = $(this).data('id');
			layer.confirm('确认要删除吗？', function(index) {
				var url = "/"+BaseData.module+"/saveJson";
				$.post(url, {"id":id,"isDel":1}, function(datas) {
					_this.load();
					parent.layer.msg('删除成功!',{icon:1,time:1000});
					layer.close(index);
				});
			});
		});
	}
}