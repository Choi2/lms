<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="navigation">
<ul>
	<c:if test="${param.menu eq 'rent'}">
		<li class="selected"><a href="${pageContext.request.contextPath }/admin/rent">대여 관리</a></li>
		<li><a href="${pageContext.request.contextPath }/admin/reserve">예약 관리</a></li>
	</c:if>
	
	<c:if test="${param.menu eq 'reserve'}">
		<li><a href="${pageContext.request.contextPath }/admin/rent">대여 관리</a></li>
		<li class="selected"><a href="${pageContext.request.contextPath }/admin/reserve">예약 관리</a></li>
	</c:if>
</ul>
</div>
<div id="footer">&nbsp;</div>