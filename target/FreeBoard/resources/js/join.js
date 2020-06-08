function init(){
	userJoin();			//회원가입
	formValidation();	//회원가입 폼 유효성 검증
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});

//회원가입 폼 유효성 검증
function formValidation(){
	
	//아이디
	var abc = "userID";
	$(`#${abc}`).blur(function(e){
		var userID = $('#userID').val();
		if(userID==""){
			$('.userID_feedback').text('아이디를 기입해주세요.').addClass('validation_fail');
			return;
		}
		ajaxSendVaidation("userID");

	});
	
	//닉네임
	$('#nickName').blur(function(e){
		var nickName = $('#nickName').val();
		if(nickName==""){
			$('.nickName_feedback').text('닉네임을 기입해주세요.').addClass('validation_fail');
			return;
		}
		ajaxSendVaidation("nickName");

	});
	
	
	//비밀번호 확인
	$('#passwordCheck').blur(function(e){
		var password = $('#password').val();
		var passwordCheck = $('#passwordCheck').val();
		if(password==""){
			$('.passwordCheck_feedback').text('비밀번호를 모두 기입해주세요.').addClass('validation_fail');
			return;
		}
		if(password==passwordCheck){
			if($('.passwordCheck_feedback').hasClass('validation_fail'))
				$('.passwordCheck_feedback').removeClass('validation_fail');
			$('.passwordCheck_feedback').text('사용가능한 비밀번호 입니다.').addClass('validation_success');
		}else{
			if($('.passwordCheck_feedback').hasClass('validation_success'))
				$('.passwordCheck_feedback').removeClass('validation_success');
			$('.passwordCheck_feedback').text('비밀번호가 일치하지 않습니다.').addClass('validation_fail');
		}
		
	});
	
	//이메일 확인
	$('#email').blur(function(e){
		var email = $('#email').val();
		if(email==""){
			$('.email_feedback').text('이메일을 기입해주세요.').addClass('validation_fail');
			return;
		}
		else if(emailRegexCheck(email)==false)//이메일 정규식 검증
			return;
		
		ajaxSendVaidation("email");

	});
	
	
}
//폼 유효성 검증
function ajaxSendVaidation(type){
	var formVal = $(`#${type}`).val();
	var koreanText = korean_Text(type);
	$.ajax({
		url:'/formValidation',
		method:'post',
		contentType:'application/json',
		data:JSON.stringify({type:type,
							formVal:formVal}),
		success : function(data){
			if(data==0){
				if($(`.${type}_feedback`).hasClass('validation_fail'))
					$(`.${type}_feedback`).removeClass('validation_fail');
				$(`.${type}_feedback`).text(`사용가능한 ${koreanText} 입니다.`).addClass('validation_success');
			}else if(data==1){
				if($(`.${type}_feedback`).hasClass('validation_success'))
					$(`.${type}_feedback`).removeClass('validation_success');
				$(`.${type}_feedback`).text(`해당 ${koreanText}(가/이) 이미 존재합니다.`).addClass('validation_fail');
			}
		}
	});

}
//한글 텍스트
function korean_Text(type){
	var text="";
	if(type=="userID")
		text="아이디";
	else if(type=="nickName")
		text="닉네임";
	else 
		text="이메일";
	return text;
}
//회원가입 
function userJoin(){
	$('#joinBtn').click(function(e){
		e.preventDefault();
		if($('#userID').val()==""||
			$('#nickName').val()==""||	
			$('#password').val()==""||
			$('#passwordCheck').val()==""||			
			$('#email').val()==""){
			alert('내용을 모두 기입해주세요!');
			return;
		}
		//성공 할경우	
		if(!$('.userID_feedback').hasClass('validation_fail') &&
		   !$('.passwordCheck_feedback').hasClass('validation_fail')&&
		   !$('.email_feedback').hasClass('validation_fail')&&
		   !$('.nickName_feedback').hasClass('validation_fail')){
			$('.joinForm').submit(); //전송
			alert("가입을 성공했습니다.");
		}

	});
}
//이메일 정규식 검증
function emailRegexCheck(email){
	var pattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,3}$/;
	if(pattern.test(email)==false){
		$('.email_feedback').text('이메일 형식이 잘못되었습니다.').addClass('validation_fail');
		return false;
	}
	return true;
}