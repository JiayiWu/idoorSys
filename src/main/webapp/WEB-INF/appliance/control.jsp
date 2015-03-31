<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">

	<form method="post" 
		action="appliance/list" class="pageForm"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<p>
			<label>房门号：</label> <select name="id">
				<c:forEach var="rooms" items="${room}">
					<option value="${rooms.id}">${rooms.id}</option>
				</c:forEach>
			</select>
		</p>
		
		
	</form>


</div>
