<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${cnt>0 }">
	<script>
	alert("게시글 삭젱");
	location.href="/webApp/replyBoard/replyList";
	</script>
</c:if>
<c:if test="${cnt<=0 }">
	<script>
	alert("삭제실패");
	history.back();
	</script>
</c:if>