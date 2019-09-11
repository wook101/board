function init(){
	moveToUpdateForm();	//수정 폼으로
	deletePost();		//게시글 삭제
	replyRegister();	//댓글 등록
	replyDeleteEvent(); //댓글 삭제
}
document.addEventListener("DOMContentLoaded",function(){
	init();
});
//댓글 삭제
function replyDeleteEvent(){
	var len = $('#replyView img').length;
	if (len!=0){
		for(var i=0;i<len;i++){
			$('#replyView img')[i].addEventListener("click",function(){
				if(confirm("댓글을 삭제하시겠습니까?")){
					var reply_id = $(this).attr('data-replyid');
					var delete_li = $(this).parents('li');
					$.ajax({
						url:"/FreeBoard/deleteReply/"+reply_id,
						method:"delete",
						success:function(result){
							if (result==1){
								delete_li.remove();
							}
							else
								alert("삭제를 실패했습니다.");
						}
					});
					
				}
			});
		}
	}
	
	
}
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
				if(confirm("댓글을 등록 하시겠습니까?")){
					$('#replyForm').submit();
				}
			}
			else{
				alert('로그인 후 이용 가능 합니다.');
				location.href="/FreeBoard/login";
			}
		}
	});
}