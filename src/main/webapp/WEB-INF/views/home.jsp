<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Spring Homepage
	  
</h1>
<img src="/webApp/img/tosi.jpg" title="갑변형게시판으로" onclick="location.href='/webApp/replyBoard/replyList'"/>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
