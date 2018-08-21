$(function() {
	BaseData.init();
});

var BaseData = {
	module:null,
	formId:null,
	
	init : function(module,formId) {
		this.module = module;
		this.formId =formId;
		this.bindEvent();
		this.load();
	},
	load : function() {
		var _this = this;
		var url = "/sysUser/listPageJson";
		var data =$("#sysUserForm").serializeJson();
		$.post(url, data, function(datas) {
			_this.list_tpl(datas);
			_this.page_load(datas);
		});
	},
	page_load: function(resultData) {
		$("#totalResult").val(resultData.page.totalResult);
		var _this = this;
	    var laypage = layui.laypage,
	    layer = layui.layer;
	    //完整功能
	    laypage.render({
	        elem: 'tcdPageCode',
	        count: resultData.page.totalResult,
	        curr: resultData.page.currentPage, //初始化当前页
	        limit: resultData.page.showCount,
	        layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
	        jump: function(obj, first) {
	        	console.log(obj);
	        	console.log(first);
	            if(!first){
	        		$("#currentPage").val(obj.curr);
		        	_this.load();
	        	}
	        }
	    });
	},
	list_tpl : function(datas) {
		var _this = this;
		//var resultData = JSON.parse(datas);
		$('#totalRecord').html(datas.page.totalResult);
		
		datas.statusStr = function(){
			if(this.status == 1 ){
				return "<span class=\"label label-success radius\">已启用</span></td>";
			}else{
				return "<span class=\"label radius\">已停用</span>";
			}
		};
		
		datas.statusBtn = function(){
			if(this.status == 0 ){
				return "<a style='text-decoration:none' data-id='"+this.id+"' data-status='"+this.status+"' class='aStatus' href='javascript:;' title='启用'><i class='Hui-iconfont'>&#xe615;</i></a>";
			}else{
				return "<a style='text-decoration:none' data-id='"+this.id+"' data-status='"+this.status+"' class='aStatus' href='javascript:;' title='停用'><i class='Hui-iconfont'>&#xe631;</i></a>";
			}
		};
		var output = Mustache.render($('#jUserListTpl').html(), datas);
		$('#jUserListBody').html(output);
	},
	bindEvent : function() {
		var _this = this;
		$('#sysUserForm').on('click', '.btnSearch', function() {
			$("#currentPage").val(1);
			_this.load();
		});
		
		$('#sysUserForm').on('click', '.btnAdd', function() {
			var title = '添加用户';
			var url = '/sysUser/savePage';
			layer_show(title,url,800,500);
		});
		
		$('#sysUserForm').on('click', '.btnDel', function() {
			layer.confirm('确认要删除吗？', function(index) {
				var url = "/sysUser/deleteUserInfo.html";
				$.post(url, data, function(datas) {
					_this.load();
				});
				layer.close(index);
			});
		});
		
		$('#sysUserForm').on('click', '.aStatus', function() {
			var status = $(this).data('status');
			var id = $(this).data('id');;
			
			layer.confirm('确认要'+(status == 1?'禁用':'启用')+'吗？', function(index) {
				var url = "/sysUser/saveJson";
				$.post(url, {"id":id,"status":(status == 1?'0':'1')}, function(datas) {
					parent.layer.msg((status == 1?'禁用':'启用')+'成功!',{icon:1,time:1000});
					_this.load();
					layer.close(index);
				});
			});
		});

		$('#sysUserForm').on('click', '.aEdit', function() {
			var id = $(this).data('id');
			var title = '编辑用户';
			var url = '/sysUser/savePage?id=' + id;
			layer_show(title,url,800,500);
		});

		$('#jUserListBody').on('click', '.aDel', function() {
			var id = $(this).data('id');
			layer.confirm('确认要删除吗？', function(index) {
				var url = "/sysUser/saveJson";
				$.post(url, {"id":id,"isDel":1}, function(datas) {
					_this.load();
					parent.layer.msg('删除成功!',{icon:1,time:1000});
					layer.close(index);
				});
			});
		});
	}
}