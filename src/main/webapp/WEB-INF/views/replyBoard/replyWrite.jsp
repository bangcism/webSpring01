<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>답글게시판 글쓰기</h1>
<form method="post" action="/webApp/replyBoard/replyReWriteOk">
   <ul>
      <li>작성자 : <input type="text" name="username"/></li>
      <li>제목 : <input type="text" name="subject"/></li>
      <li>글내용 : <textarea name="content"></textarea>
      <li><input type="submit" value="답글저장하기"/>
      	 <input type="hidden" name="num" value=${num }"/>
      </li>
   </ul>
</form>
</body>
</html>