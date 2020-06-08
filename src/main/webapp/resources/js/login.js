function init(){
	
	loginFormValidation();			//로그인 폼 유효성 검증
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});

//로그인 폼 유효성 검증
function loginFormValidation(){
	$('#loginBtn').click(function(e){
		e.preventDefault();
		var userID = $('#userID').val();
		var password = $('#password').val();
		
		if(userID=="" || password==""){
			$('.form_feedback').text('아이디와 비밀번호를 기입해주세요.').addClass('validation_fail');
			return;
		}

		$.ajax({
			url:'/loginCheck',
			method:'post',
			contentType:'application/json',
			data:JSON.stringify({userID:userID,
								 password:password}),
			success : function(data){
				if(data==0){
					$('.form_feedback').text('아이디 또는 비밀번호가 일치하지 않습니다.').addClass('validation_fail');
				}else if(data==1){
					alert('로그인 성공!');
					$('.loginForm').submit();
				}
			}
		});

	});
	
}