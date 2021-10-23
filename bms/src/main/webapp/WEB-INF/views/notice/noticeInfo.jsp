<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="${contextPath }/resources/css/styles.css" rel="stylesheet" />
</head>
<body>
	<div align="center" style="padding-top: 100px">
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header" align="center">
						<h1>${ndto.subject}</h1>
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
								<td>${ndto.noticeId}</td>
							</tr>
							<tr class="table-default">
								<td>조회수</td>
								<td>${ndto.readCount}</td>
							</tr>
							<tr class="table-default">
								<td>작성자</td>
								<td>${ndto.writer}</td>
							</tr>
							<tr class="table-default">
								<td>작성일</td>
								<td><fmt:formatDate value="${ndto.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
							<tr class="table-default">
								<td>제목</td>
								<td>${ndto.subject}</td>
							</tr>
							<tr class="table-default">
								<td>공지사항 내용</td>
								<td>${ndto.content}</td>
							</tr>
							<tr class="table-default">
								<td colspan="2">
									<c:choose>
										<c:when test="${isLogOn==true and memberInfo.memberId =='admin' }">  	
											<input type="button" class="btn btn-primary btn-sm" value="수정" onclick="location.href='${contextPath }/notice/noticeUpdate.do?noticeId=${ndto.noticeId}'">
										</c:when>
									</c:choose>		
									<c:choose>
										<c:when test="${isLogOn==true and memberInfo.memberId =='admin' }">  	
											<input type="button" class="btn btn-primary btn-sm" value="삭제" onclick="location.href='${contextPath }/notice/noticeDelete.do?noticeId=${ndto.noticeId}'">
										</c:when>
									</c:choose>		
									<input type="button" class="btn btn-primary btn-sm" value="공지사항" onclick="location.href='${contextPath }/notice/noticeList.do'">
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