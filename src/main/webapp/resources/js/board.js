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
			url:'/loginStateCheck',
			method:'post',
			dataType:'text',
			success: function(data){
				var check = (data ==='true');
				if(check){						//로그인 상태
					location.href='/write';
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
	var path = location.pathname + location.search;
	if(path==location.pathname){
		path = "/board?start=0";
	}
	$('a[href="'+path+'"]').addClass('active');
}