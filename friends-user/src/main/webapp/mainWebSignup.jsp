<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/User/signup/web/user" method="post">
		userId:<input type="text" name="userId" value="${user.userId }" />
		userName:<input type="text" name="userName" value="${user.userName }" />
		nickName:<input type="text" name="nickName" value="${user.nickName }" />
		
		phone:<input type="text" name="phone" value="${user.phone }" />
		location:<input type="text" name="location" value="${user.location }" />
		birthday:<input type="text" name="birthday" value="${user.birthday }" />
		
		country:<input type="text" name="country" value="${user.country }" />
		<input type="submit" name="save" value="save" />
	</form>
</body>
</html>