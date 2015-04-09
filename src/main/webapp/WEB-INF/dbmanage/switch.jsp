<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageHeader">
	测试用，后期切换楼层自动切换
	<form onsubmit="return navTabSearch(this);"
		action="dbmanage/switch" method="post">
		<div class="searchBar">
			<select name="dataSourceKey" >
				<c:forEach var="dataSource" items="${dbList}">
					<option value="${dataSource}">${dataSource}</option>
				</c:forEach>
			</select>
			<div class="subBar">
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">确定</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<c:if test="${error != null }">
	<h1>${error}</h1>
</c:if>
