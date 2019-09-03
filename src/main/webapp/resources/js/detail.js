function init(){
	//수정 폼으로
	$('#updateBtn').click(function(){
		location.href="/FreeBoard/updateForm/" + $(this).val();
	});
	//삭제
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
document.addEventListener("DOMContentLoaded",function(){
	init();
});