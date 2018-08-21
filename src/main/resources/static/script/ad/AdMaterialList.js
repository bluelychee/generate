var propFun = function(datas){
	if(datas==null || datas==undefined) return;
	
	datas.typeStr = function(){
		if(this.type == 1 ){
			return "视频";
		}else if(this.type == 0 ){
			return "图片";
		}else{
			return "未知";
		}
	};
	
	/*datas.statusBtn = function(){
		if(this.status == 0 ){
			return "<a style='text-decoration:none' data-id='"+this.id+"' data-status='"+this.status+"' class='aStatus' href='javascript:;' title='启用'><i class='Hui-iconfont'>&#xe615;</i></a>";
		}else{
			return "<a style='text-decoration:none' data-id='"+this.id+"' data-status='"+this.status+"' class='aStatus' href='javascript:;' title='停用'><i class='Hui-iconfont'>&#xe631;</i></a>";
		}
	};*/
	
}