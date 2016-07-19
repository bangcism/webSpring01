<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${result>0 }">
	<script>
		alert("글 수정")
		location.href="webApp/replyBoard/view?num=${num}";
	</script>

</c:if>


<c:if test="${result<=0 }">
	<script>
	alert("글수정 실패염");
	history.back();
	</script>

</c:if>