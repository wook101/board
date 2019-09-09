function init(){
	moveToUpdateForm();	//수정 폼으로
	deletePost();		//게시글 삭제
	replyRegister();	//댓글 등록
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//댓글 등록
function replyRegister(){
	$('#replyBtn').click(function(e){
		e.preventDefault();
		if($('#write_textarea').val()==""){
			alert('내용을 작성 해주세요');
			return;
		}
		loginStateCheck();
	});
}

//수정 폼으로 이동
function moveToUpdateForm(){
	$('#updateBtn').click(function(){
		location.href="/FreeBoard/updateForm/" + $(this).val();
	});
}
//게시글 삭제
function deletePost(){
	$('#deleteBtn').click(function(){
		var delHashCode = $('#imgTag').attr('data-hashCode');
		if(delHashCode==undefined)
			delHashCode="null"
		if(confirm("삭제 하시겠습니까?")){
			$.ajax({
				url:"/FreeBoard/delete/"+$(this).val(),
				method:"post",
				contentType:"application/json",
				data:JSON.stringify({delHashCode:delHashCode}),
				success:function(jsonData){
					if(jsonData.result){
						alert('삭제 되었습니다.');
						location.href="/FreeBoard/board";
					}else{
						alert('삭제 오류');
					}

				}
			});
		}
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
				if(confirm("등록 하시겠습니까?")){
					$('#replyForm').submit();
					alert('댓글이 등록 되었습니다.');
				}
			}
			else{
				alert('로그인 후 이용 가능 합니다.');
				location.href="/FreeBoard/login";
			}
		}
	});
}