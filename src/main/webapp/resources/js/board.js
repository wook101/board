function init(){
	loginStateCheck(); 			//로그인 상태 확인
	paginationCss();			//pagination클릭시 배경색 초록
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//로그인 상태 확인
function loginStateCheck(){
	$('#writeBtn').click(function(){
		$.ajax({
			url:'/FreeBoard/loginStateCheck',
			method:'post',
			dataType:'text',
			success: function(data){
				var check = (data ==='true');
				if(check){						//로그인 상태
					location.href='write';
				}
				else{
					alert('로그인을 하지 않았습니다.');
				}
			}
		});
	});
}
//pagination클릭시 배경색 초록
function paginationCss(){
	var path = decodeURI(location.pathname.replace("/FreeBoard/","")+location.search);
	console.log(path);
	if(path=="board"){
		path="board?start=0&searchKeyword=null";
	}else if(path.substr(0,6)=="search"){
		if(path.substr(7,5)!="start")
			path="search?start=0&"+decodeURI(location.search).replace("?","");
	}
	$('a[href="'+path+'"]').addClass('active');
}