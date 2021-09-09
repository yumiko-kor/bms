<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>문의 게시판</title>
<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
<link href="${contextPath}/resources/css/styles.css" rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script>

	$().ready(function(){
		
		$("#onePageViewCount").change(function(){
			
			var onePageViewCount = $("#onePageViewCount").val();
			var searchKeyword = $("#searchKeyword").val();
			var searchWord = $("#searchWord").val();
			var url = "${contextPath}/board/boardList.do?";
				url	+= "onePageViewCount="+onePageViewCount;
				url	+= "&searchKeyword="+searchKeyword;
				url	+= "&searchWord="+searchWord;
			location.href = url;
			
		}) ;
		
		$("#getSearchBoard").click(function(){
			var onePageViewCount = $("#onePageViewCount").val();
			var searchKeyword = $("#searchKeyword").val();
			var searchWord = $("#searchWord").val();
			var url = "${contextPath}/board/boardList.do?";
				url +="onePageViewCount="+onePageViewCount;
				url += "&searchKeyword="+searchKeyword;
				url += "&searchWord="+searchWord;
			location.href=url;
		});
			
		
	});
	
</script>
</head>
<body class="sb-nav-fixed">
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid">
                    <h1 class="mt-4">문의게시판</h1>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table mr-1"></i>
                            전체 <span style="color: red; font-weight: bold">${totalBoardCount}</span> 개
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                                	<div class="row">
                                		<div class="col-sm-12 col-md-6">
                                			<div class="dataTables_length" id="dataTable_length">
                                				<label>목록 
                                 				<select id="onePageViewCount" name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
                                 					<option <c:if test="${onePageViewCount eq 5}"> selected</c:if> value="5">5</option>
													<option <c:if test="${onePageViewCount eq 7}"> selected</c:if> value="7">7</option>
													<option <c:if test="${onePageViewCount eq 10}"> selected</c:if> value="10">10</option>
                                 				</select> 개 보기
                                 				</label>
                                 			</div>		                               
                              			</div>
                              			<div class="col-sm-12 col-md-6">
                              				<input type="button" class="btn btn-primary" style="float: right" value="글쓰기" onclick="location.href='${contextPath }/board/boardWrite.do'">
                              			</div>
                              		</div>
	                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                                    <colgroup>
											<col width="15%">
											<col width="30%">
											<col width="15%">
											<col width="25%">
											<col width="15%">
										</colgroup>
	                                    <thead>                                     
	                                        <tr align="center">
	                                            <th>글번호</th>
	                                            <th>제목</th>
	                                            <th>작성자</th>
	                                            <th>작성일</th>
	                                            <th>조회수</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody align="center">
	                                        <c:set var="order" value="${totalBoardCount - (currentPageNumber-1) * onePageViewCount}"/>
	                                        <c:forEach var="bdto" items="${boardList}">	                                        	
												<tr>
													<td><fmt:parseNumber integerOnly="true" value="${order}"/></td>	<c:set var="order" value="${order - 1}"/>
													<td>
														 <c:if test="${bdto.reStep > 1}">
														 	<c:forEach var="j" begin="0" end="${(bdto.reLevel-1 ) * 5 }">
														 		&nbsp;
														 	</c:forEach>
														 	»
														 </c:if>
														<a href="${contextPath }/board/boardInfo.do?boardId=${bdto.boardId}"> ${bdto.subject}</a>
													</td>
													
													<td>${bdto.writer}</td>
													<td><fmt:formatDate value="${bdto.regDate}" pattern="yyyy-MM-dd"/></td>
													<td>${bdto.readCount}</td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="5" align="center">	
													<select id="searchKeyword" class="form-control" style="width: 150px; display: inline;">
														<option <c:if test="${searchKeyword eq 'total'}"> selected</c:if> value="total">전체</option>
														<option <c:if test="${searchKeyword eq 'writer'}"> selected</c:if> value="writer">작성자</option>
														<option <c:if test="${searchKeyword eq 'subject'}"> selected</c:if> value="subject">제목</option>
													</select>
			                                 		<input type="text" style="width: 300px; display: inline;" class="form-control" id="searchWord" name="searchWord" value="${searchWord}" >
													<input type="button" class="btn btn-outline-info btn-sm" value="검색" id="getSearchBoard">
												</td>
											</tr>
	                                    </tbody>										
	                                </table>
                                	<div style="display: table; margin-left: auto; margin-right: auto">
                                 	<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                 		<c:if test="${totalBoardCount gt 0 }">
                                  		<ul class="pagination">
                                  			<c:if test="${startPage gt 10 }">
                                   			<li class="paginate_button page-item previous" id="dataTable_previous">
                                   				<a href="${contextPath }/board/boardList.do?currentPageNumber=${startPage - 10}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">이전</a>
                                   			</li>
                                  			</c:if>
                                  			<c:forEach var="i" begin="${startPage}" end="${endPage }" >
                                   			<li class="paginate_button page-item">
                                   				<a href="${contextPath }/board/boardList.do?currentPageNumber=${i}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${i}</a>
                                   			</li>
                                   		</c:forEach>
                                  			<c:if test="${endPage le totalBoardCount && endPage ge 10}"> 
                                   			<li class="paginate_button page-item next" id="dataTable_next">
                                   				<a href="${contextPath }/board/boardList.do?currentPageNumber=${startPage + 10}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">다음</a>
                                   			</li>
                                  			</c:if>
                                  		</ul>
                                  	</c:if>
                                 	</div>
                                 </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </main>
        </div>
    </div>
</body>
</html>