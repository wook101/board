function init(){
	var delHashCode = $('#deleteImg').val();
	updateBtn(delHashCode);				//수정버튼
	textAreaLen();						//textArea길이 적용
	thumbnailAdd();						//썸네일 추가
	thumbnailClose();					//썸네일 닫기
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//이미지 타입 검사
function validation(type){
	var check = ["image/jpeg","image/gif","image/png"].indexOf(type) > -1;
	return check;
}
//썸네일 닫기
function thumbnailClose(){
	$('#closeBtn').click(function(){
		$('#addFile').val('');
		$('.thumbnail_area').css('display','none');
		$('#deleteImg').val("null");
		
	});
}
//수정버튼
function updateBtn(delHashCode){
	$('#updateBtn').click(function(e){
		e.preventDefault();
		if($('#title').val()==""){
			alert('제목을 입력해주세요');
			return;
		}
		
		//서버내에 이미지파일 삭제할 경우
		if(delHashCode!= undefined && $('#deleteImg').val()=="null"){
			$.ajax({
				url:'/deleteImg',
				method:'delete',
				contentType:'application/json',
				data:JSON.stringify({delHashCode:delHashCode}),
				success: function(data){
					$('.writeForm').submit();
				}
			});
		}else{
			$('.writeForm').submit();
		}
		
		//loginStateCheck();
	});	
}
//로그인 상태 확인
function loginStateCheck(){
	$.ajax({
		url:'/loginStateCheck',
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
		if(image.size > 3145728){	//파일용량 최대 3MB
			alert('파일 이미지는 3MB이내로 등록 가능합니다.');
			$(this).val('');
			return;
		}
		if(location.hostname=="localhost"){
			var url = window.URL.createObjectURL(image);
			$('#thumbnailImg').attr('src',url);
			$('.thumbnail_area').css('display','');
			$('#deleteImg').val("null");
		}else{
			//하드 용량 확인 [배포된 상태일때]
			fileSizeTest(image);
		}

	});
}
//textArea길이 적용
function textAreaLen(){
	$('#content').keyup(function(e){
		var textLen = e.target.value.length;
		$('#textNum').text(textLen); 
	});
}
//하드 용량 확인 [배포된 상태일때]
function fileSizeTest(image){
	$.ajax({
		url:"/check",
		method:"get",
		success:function(currentfileSize){
			if(Number(currentfileSize) + image.size < 190200000){
				var url = window.URL.createObjectURL(image);
				$('#thumbnailImg').attr('src',url);
				$('.thumbnail_area').css('display','');
				$('#deleteImg').val("null");
			}else{
				alert('현재 웹하드의 용량이 가득차서 이미지를 업로드 할 수 없습니다.');
				$('#addFile').val('')
			}	
		}
	});
}