<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 정보 추출
	MemberVO vo = (MemberVO)request.getAttribute("vo");
	String id=null, name=null, gender=null, email=null;
	if(vo!=null){
		id = vo.getUserid();
		name = vo.getName();
		gender = vo.getGender();
		email = vo.getEmail();
	}
	
	String msg = request.getParameter("msg");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/modify.js"></script>
<script>
$(function() {
	$("#modify").validate();
	
	$("document").ready(function() {
		var msg='<%=msg%>';
		if(msg=='fail')
			alert("수정하지 못했습니다.");
	});
	
});
	
</script>
</head>
<body>
<form id="modify" action="update.do" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userid" id="userid" value="<%=id%>" readonly></td>
		</tr>
		<tr>
			<td>비밀번호 변경</td>
			<td><input type="password" name="password" id="password">
			<small id="password" class="text-info"></small></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="confirm_password" id="confirm_password">
			<small id="confirm_password" class="text-info"></small></td>
			
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" value="<%=name%>" readonly></td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
			<%if(gender.equals("남")){ %>
				<input type="radio" name="gender" value="남" id="gender" checked>남
				<input type="radio" name="gender" value="여" disabled>여
			<%}else{%>
			<input type="radio" name="gender" value="남" id="gender" disabled>남
				<input type="radio" name="gender" value="여" checked>여
			<%}%>
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="email" name="email" id="email" value="<%=email%>">
			<small id="email" class="text-info"></small></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정하기">
				<input type="button" value="취소">
			</td>
		</tr>
	</table>
</form>	
</body>
</html>