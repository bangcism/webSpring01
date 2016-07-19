<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글내용보기</h1>
<ul>
	<li>글쓴이 : ${vo.username }</li>
	<li>제목 : ${vo.subject }</li>
	<li>글내용 : ${vo.content }</li>
	<li>조회수 : ${vo.hit }</li>
	<li>등록일 : ${vo.writedate }</li>	
	<li>아이피 : ${vo.userip }</li>	
</ul>
<hr/>
<a href="/webApp/">홈</a>
<a href="/webApp/replyBoard/replyList">리스트</a>
<a href="/webApp/replyBoard/replyEdit?num=${vo.num}">글수정</a>
<a href="/webApp/replyBoard/replyWrite?num=${vo.num}">답변글쓰기</a>
<a href="/webApp/replyBoard/replyDel?num=${vo.num}">삭제</a>
</body>
</html>