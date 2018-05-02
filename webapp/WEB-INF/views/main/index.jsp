<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>예약/대여</th>
					</tr>
					
					<c:forEach items="${list.content}" var="vo" varStatus="status">
					<tr>
						<td>${vo.no}</td>
						<td>${vo.title}</td>
						<td>${vo.type} (${vo.category.name})</td>
						<td style="text-align:center;">
							<c:if test="${vo.isRent eq false}">
							<a href="${pageContext.servletContext.contextPath}/rent?itemNo=${vo.no}" class="btn">대여</a>
							</c:if>
							<c:if test="${vo.isRent eq true}">
							<a href="${pageContext.servletContext.contextPath}/reverse?itemNo=${vo.no}" class="btn">예약</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
							
				</table>
				<div class="pager">
					<ul>
						<c:if test="${pager.leftArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${pager.startPage - 1}&word=${pager.word}">◀</a></li>
						</c:if>
						
						<c:forEach begin="${pager.startPage}" end="${pager.endPage}" varStatus="status">
							<li>
								<c:if test="${param.page == status.index}">	
									<a style="color:red;" href="${pageContext.servletContext.contextPath}/board?page=${status.index}&word=${word}">${status.index}</a>
								</c:if>
									
								<c:if test="${param.page != status.index}">
									<a href="${pageContext.servletContext.contextPath}/board?page=${status.index}&word=${word}">${status.index}</a>
								</c:if>
							</li>
						</c:forEach>
						
						<c:forEach begin ="${pager.endPage + 1}" end = '5'  varStatus="status">
							<li style="color:gray;">${status.index}</li>
						</c:forEach>
						
						<c:if test="${pager.rightArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${pager.endPage + 1}&word=${pager.word}">▶</a></li>
						</c:if>
					</ul>
				</div>				
				<div class="bottom">
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>