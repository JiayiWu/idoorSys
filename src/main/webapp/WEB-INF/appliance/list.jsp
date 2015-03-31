<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">

	<form method="post" 
		action="appliance/control" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<p>
			<label>房门号：</label> <select name="id">
				<c:forEach var="room" items="${rooms}">
					<option value="${room.id}">${room.id}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input id="box1" type="checkbox">
			<span>前门</span>
			<select name="choose1">
				<option value="on">开</option>
				<option value="of">关</option>
			</select>
			<span>后门</span>
			<select name="choose1">
				<option value="on">开</option>
				<option value="of">关</option>
			</select>
		</p>
		<p>
			<input id="box2" type="checkbox"><label>桌子：</label>
			<c:forEach var="desk" items="${desks}">
				<input type="text" value="${desks}" readonly=true>
				<select name="choose2">
					<option value="on">开</option>
					<option value="of">关</option>
				</select>
			</c:forEach>
		</p>
		<p>
			<input id="box3" type="checkbox"><label>灯：</label>
			<c:forEach var="light" items="${lights}">
				<input type="text" value="${light}" readonly=true>
				<select name="choose3">
					<option value="on">开</option>
					<option value="of">关</option>
				</select>
			</c:forEach>
		</p>
		<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
	</form>


</div>