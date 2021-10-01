<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table#tbl {
	border-collapse:collapse;
}
table#tblView td{
	border:1px solid #b269bf;
}
table#tblView th{
	color:white;
	background-color:#b269bf;
	border:1px solid #611a6e;
}

</style>
<body>
<form method=POST action="/app/update_view">
<input type=hidden id=bbs_id name=bbs_id value='${post.bbs_id}'>
<table align=center valign=top>
	<tr><td colspan=2>
		<table id=tblView>
			<tr><td>제목</td><td><input type=text id=title name=title value='${post.title}'></td></tr>
			<tr><td>내용</td><td><input type=text id=content name=content value='${post.content}'></td></tr>
			<tr><td>작성자</td><td>${post.writer}</td></tr>
			<tr><td>작성시각</td><td>${post.created}</td></tr>
			<tr><td>수정시각</td><td>${post.updated}</td></tr>
		</table>
	</td></tr>
	<tr>
		<td><input type=submit value="수정완료"></td>
		<td><input type=button id=btnDelete value="삭제하기"></td>
	</tr>
</table>
</form>
</body>
<script src='https://code.jquery.com/jQuery-3.5.0.js'></script>
<script>
$(document)
//.on('click','#btnUpdate',function(){
//	let bbs_id=$('#bbs_id').val();
//	let title=$('#title').val();
//	let content=$('#content').val();
//	document.location="/app/view/"+$('#bbs_id').val();
//	return false;
//})

.on('click','#btnDelete',function(){
	let bbs_id=$('#bbs_id').val();
	console.log(bbs_id);
	document.location="/app/delete/"+bbs_id;
	return false;
});

</script>
</html>