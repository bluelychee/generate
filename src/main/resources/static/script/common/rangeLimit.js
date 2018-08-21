/**
 * 日期范围限制*/
/*起始日期*/
function pickedFunc1(){
	var fromDateStr = $("#startDate").val();
	var toDateStr = $("#endDate").val();
	
	var fromDate=new Date(fromDateStr);
	var toDate=new Date(toDateStr);
	fromDate.setDate(fromDate.getDate()+30);
	if(toDate>fromDate){
		var m=fromDate.getMonth()+1;
		var d=fromDate.getDate();
		if(m<10) m="0"+m;
		if(d<10) d="0"+d;
		$("#endDate").val(fromDate.getFullYear()+"-"+m+"-"+d);
	}
}

/*截止日期*/
function pickedFunc2(){
	var fromDateStr = $("#startDate").val();
	var toDateStr = $("#endDate").val();
	
	var fromDate=new Date(fromDateStr);
	var toDate=new Date(toDateStr);
	toDate.setDate(toDate.getDate()-30);
	if(fromDate<toDate){
		var m=toDate.getMonth()+1;
		var d=toDate.getDate();
		if(m<10) m="0"+m;
		if(d<10) d="0"+d;
		$("#startDate").val(toDate.getFullYear()+"-"+m+"-"+d);
	}
}

/*起始日期*/
function pickedFuncMonth1(){
	var fromDateStr = $("#startDate").val();
	var toDateStr = $("#endDate").val();
	
	var fromDate=new Date(fromDateStr);
	var toDate=new Date(toDateStr);
	fromDate.setMonth(fromDate.getMonth()+12);
	if(toDate>fromDate){
		var m=fromDate.getMonth()+1;
		if(m<10) m="0"+m;
		$("#endDate").val(fromDate.getFullYear()+"-"+m);
	}
}

/*截止日期*/
function pickedFuncMonth2(){
	var fromDateStr = $("#startDate").val();
	var toDateStr = $("#endDate").val();
	
	var fromDate=new Date(fromDateStr);
	var toDate=new Date(toDateStr);
	toDate.setMonth(toDate.getMonth()-12);
	if(fromDate<toDate){
		var m=toDate.getMonth()+1;
		if(m<10) m="0"+m;
		$("#startDate").val(toDate.getFullYear()+"-"+m);
	}
}