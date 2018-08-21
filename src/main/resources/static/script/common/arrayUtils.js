Array.prototype.contains = function(obj){
	_this = this;
	var flag = false;
	for(var i in _this){
		if(obj == _this[i]) {
			flag =true;
			break;
		}
	}
	return flag;
}