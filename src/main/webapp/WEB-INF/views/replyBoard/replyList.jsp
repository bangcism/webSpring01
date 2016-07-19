<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/webApp/js_css/style.css" rel="stylesheet"/>
</head>
<body>
<h1>답변글 목록</h1>
<ul>
   <li>번호</li>
   <li>제목</li>
   <li>글쓴이</li>
   <li>조회수</li>
   <li>등록일</li>   
</ul>
<c:set var="recordNum" value="${startNumber+1}"/>
<c:forEach var="i" items="${list}">
<ul>
   <li>${recordNum}
   <c:set var="recordNum" value="${recordNum-1}" />
   </li>
   <li>
   <
   <c:if test="${i.lev>0 }">
   <c:forEach var ="space" begin="1" end="${i.lev }">
   		&nbsp;&nbsp;&nbsp;&nbsp;
   </c:forEach>
   		☞
   	</c:if>
   <a href="/webApp/replyBoard/View?num=${i.num}">${i.subject}</a></li>
   <li>${i.username}</li>
   <li>${i.hit}</li>
   <li>${i.writedate}</li>   
</ul>
</c:forEach>
<hr/>
<a href="/webApp/replyBoard/write">글쓰기</a>
<a href="/webApp/">홈</a>
</body>
</html>