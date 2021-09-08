<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bInfo</title>
<link href="${contextPath }/resources/css/styles.css" rel="stylesheet" />
</head>
<body>
	<div align="center" style="padding-top: 100px">
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header" align="center">
						<h1>문의 게시판</h1>
						<br>
					</div>
					<div class="bs-component">
						<table class="table table-hover" style="width: 700px; text-align: center">
							<colgroup>
								<col width="20%" >
								<col width="80%">
							</colgroup>
							<tr class="table-default">
								<td>글 번호</td>
								<td>${bdto.boardId}</td>
							</tr>
							<tr class="table-default">
								<td>조회수</td>
								<td>${bdto.readCount}</td>
							</tr>
							<tr class="table-default">
								<td>작성자</td>
								<td>${bdto.writer}</td>
							</tr>
							<tr class="table-default">
								<td>작성일</td>
								<td><fmt:formatDate value="${bdto.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
							<tr class="table-default">
								<td>E-mail</td>
								<td>${bdto.email}</td>
							</tr>
							<tr class="table-default">
								<td>제목</td>
								<td>${bdto.subject}</td>
							</tr>
							<tr class="table-default">
								<td>문의 내용</td>
								<td>${bdto.content}</td>
							</tr>
							<tr class="table-default">
								<td colspan="2">
									<c:choose>
										<c:when test="${isLogOn==true and memberInfo.memberId =='admin' }">  	
											  <input type="button" value="답글달기"  class="btn btn-primary btn-sm" onClick="location.href='${contextPath }/board/boardReplyWrite.do?boardId=${bdto.boardId}'">
										</c:when>
									</c:choose>		
									<input type="button" class="btn btn-primary btn-sm" value="수정" onclick="location.href='${contextPath }/board/boardUpdate.do?boardId=${bdto.boardId}'">
									<input type="button" class="btn btn-primary btn-sm" value="삭제" onclick="location.href='${contextPath }/board/boardDelete.do?boardId=${bdto.boardId}'">
									<input type="button" class="btn btn-primary btn-sm" value="게시판 보기" onclick="location.href='${contextPath }/board/boardList.do'">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>