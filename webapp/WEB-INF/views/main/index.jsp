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
				<form id="search_form" action="${pageContext.request.contextPath}" method="get">
					<input type="text" id="word" name="word" value="" placeholder="찾을 타이틀을 입력하세요">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>대여/예약</th>
					</tr>
					
					<c:if test="${list.content.size() eq 0}">
					<tr>
						<td colspan="4" align="center">도서가 존재하지 않습니다.</td>
					</tr>
					</c:if>
					
					<c:forEach items="${list.content}" var="vo" varStatus="status">
					<tr>
						<td>${vo.no}</td>
						<td>${vo.title}</td>
						<td>${vo.type} (${vo.category.name})</td>
						<td style="text-align:center;">
							
							<c:choose>
							<c:when test="${(usedRentNoList ne null) and usedRentNoList.contains(vo.no) eq true}">
								<p> 이미 대여가 되어 있는 도서입니다. </p>
							</c:when>
							
							<c:when test="${(usedReserveNoList ne null) and usedReserveNoList.containsKey(vo.no) eq true}">
								<p> 이미 예약이 되어 있는 도서입니다.(예비순번 : ${usedReserveNoList.get(vo.no)}) </p>
							</c:when>
							
							<c:otherwise>
								<c:if test="${vo.isRent eq true}">
								<a href="${pageContext.servletContext.contextPath}/reserve?itemNo=${vo.no}" class="btn">예약</a>
								<a style="pointer-events:none; background:gray;" href="${pageContext.servletContext.contextPath}/rent?itemNo=${vo.no}" class="btn">
								<font style="color:white;">대여</font></a>
								</c:if>
								<c:if test="${vo.isRent eq false}">
								<a style="pointer-events:none; background:gray;" href="${pageContext.servletContext.contextPath}/reserve?itemNo=${vo.no}" class="btn">
								<font style="color:white;">예약</font></a>
								<a href="${pageContext.servletContext.contextPath}/rent?itemNo=${vo.no}" class="btn">대여</a>
								</c:if>
							</c:otherwise>
							
							</c:choose>
						</td>
					</tr>
					</c:forEach>
							
				</table>
				<div class="pager">
					<ul>
						<c:if test="${pager.leftArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}?page=${pager.startPage - 1}&word=${pager.word}">◀</a></li>
						</c:if>
						
						<c:forEach begin="${pager.startPage}" end="${pager.endPage}" varStatus="status">
							<li>
								<c:if test="${param.page == status.index}">	
									<a style="color:red;" href="${pageContext.servletContext.contextPath}?page=${status.index}&word=${pager.word}">${status.index}</a>
								</c:if>
									
								<c:if test="${param.page != status.index}">
									<a href="${pageContext.servletContext.contextPath}?page=${status.index}&word=${pager.word}">${status.index}</a>
								</c:if>
							</li>
						</c:forEach>
						
						<c:forEach begin ="${pager.endPage + 1}" end = '5'  varStatus="status">
							<li style="color:gray;">${status.index}</li>
						</c:forEach>
						
						<c:if test="${pager.rightArrow eq true}">
							<li><a href="${pageContext.servletContext.contextPath}?page=${pager.endPage + 1}&word=${pager.word}">▶</a></li>
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