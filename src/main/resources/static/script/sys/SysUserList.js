var propFun = function(datas){
	if(datas==null || datas==undefined) return;
	
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
	
}