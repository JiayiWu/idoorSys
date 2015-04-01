<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="appliance/listDevice"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
					<!-- 房间名称：<input type="text" name="name" /> -->
						房门号:
						<select name="roomId" style="width:100px">
							<c:forEach var="room" items="${rooms}">
								<option value="${room.id}">${room.id}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">

	<form method="post"
		action="appliance/send">
		<p>
		前门
		<select>
			<c:choose>
				<c:when test="${device.getFrontDoorState().equals(\"on\")}">
					<option value="on" selected="selected">开</option>
					<option value="of">关</option>
				</c:when>
				<c:otherwise>
					<option value="on">开</option>
					<option value="of" selected="selected">关</option>
				</c:otherwise>
			</c:choose>
		</select>
		
		后门
		<select>
			<c:choose>
				<c:when test="${device.getBackDoorState().equals(\"on\")}">
					<option value="on" selected="selected">开</option>
					<option value="of">关</option>
				</c:when>
				<c:otherwise>
					<option value="on">开</option>
					<option value="of" selected="selected">关</option>
				</c:otherwise>
			</c:choose>
		</select>
		</p>
		<c:set var="map" value="${device.generateDeskStateMap()}"/>
		<c:forEach var="desk" items="${map.keySet()}">
			${desk} ${map.get(desk)}<br />
		</c:forEach>
		
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">提交</button>
			</div>
		</div>
		<div class="button">
			<div class="buttonContent">
				<button type="button" class="close">取消</button>
			</div>
		</div>
	</form>
</div>
