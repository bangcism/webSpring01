<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${reqult>0}">
	<script>
	alert("답변글이 등록");
	location.href="/webApp/replyBoard/replyList";
	</script>
</c:if>

<c:if test="${result<=0}">
	<script>
	alert("답변글등록 실패");
	history.back();
	</script>
</c:if>