function init(){
	registerBtn();						//등록버튼
	textAreaLen();						//textArea길이 적용
	thumbnailAdd();						//썸네일 추가
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//이미지 타입 검사
function validation(type){
	var check = ["image/jpeg","image/gif","image/png"].indexOf(type) > -1;
	return check;
}
//등록버튼
function registerBtn(){
	$('#registerBtn').click(function(e){
		e.preventDefault();
		if($('#title').val()==""){
			alert('제목을 입력해주세요');
			return;
		}
		loginStateCheck();
	});	
}
//로그인 상태 확인
function loginStateCheck(){
	$.ajax({
		url:'/FreeBoard/loginStateCheck',
		method:'post',
		dataType:'text',
		success: function(data){
			var check = (data ==='true');
			if(check){						//로그인 상태
				$('.writeForm').submit();
			}
			else{
				alert('로그인을 다시 해주세요');
				location.href="/login";
			}
		}
	});
}
//썸네일 추가
function thumbnailAdd(){
	$('#addFile').change(function(e){
		var image = e.target.files[0];
		var imageType = image.type;
		if(!validation(imageType)){
			alert('gif, jpeg, png 형식만 첨부가능합니다.');
			$(this).val('');
			return;
		}
		if(image.size > 5242880){	//파일용량 최대 5MB
			alert('파일 이미지는 5MB이내로 등록 가능합니다.');
			$(this).val('');
			return;
		}
		var url = window.URL.createObjectURL(image);
		$('#thumbnailImg').attr('src',url).css('display','');
		
	});
}
//textArea길이 적용
function textAreaLen(){
	$('#content').keyup(function(e){
		var textLen = e.target.value.length;
		$('#textNum').text(textLen); 
	});
}
