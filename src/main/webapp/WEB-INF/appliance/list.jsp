<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="appliance/listDevice" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
					<select
						name="building" >
							<c:forEach var="building" items="${buildings}">
								<option value="${building}">${building}</option>
							</c:forEach>
					</select>
					幢
					</td>
					<td>
					<select
						name="unit" >
							<c:forEach var="unit" items="${units}">
								<option value="${unit}">${unit}</option>
							</c:forEach>
					</select>
					单元
					</td>
					<td>
					<select
						name="floor" >
							<c:forEach var="floor" items="${floors}">
								<option value="${floor}">${floor}</option>
							</c:forEach>
					</select>
					层
					</td>
					<td>
					<select
						name="room" >
							<c:forEach var="room" items="${rooms}">
								<option value="${room}">${room}</option>
							</c:forEach>
					</select>
					号房间
					</td>
				</tr>
			</table>
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
<c:if test="${device != null }">
	<div class="pageContent">
	
		<form method="post" action="appliance/send"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<p>
				<c:if test="${device.getFrontDoorState()!=null}">
					前门
					<select name="frontDoorState">
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
				</c:if>
				<br />
				<c:if test="${device.getBackDoorState()!=null}">
					后门 
					<select name="backDoorState">
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
				</c:if>
			</p>
			<br /> <input type="hidden" name="roomNo"
				value="${device.getRoomNo()}" />
			<p>
				<c:set var="map" value="${device.generateDeskStateMap()}" />
				<c:forEach var="deskNo" items="${map.keySet()}">
<!-- 					<c:set var="desk" value="${fn:replace(deskNo,'l','号左侧桌')}" /> -->
<!-- 					<c:set var="desk" value="${fn:replace(desk,'r','号右侧桌')}" /> -->
				${desk}号桌状态: 
				<select name="D${deskNo}">
						<c:choose>
							<c:when test="${map.get(deskNo).equals(\"on\")}">
								<option value="on" selected="selected">开</option>
								<option value="of">关</option>
							</c:when>
							<c:otherwise>
								<option value="on">开</option>
								<option value="of" selected="selected">关</option>
							</c:otherwise>
						</c:choose>
					</select>
					<br />
				</c:forEach>
			</p>
			<br />
			<p>
				<c:set var="map" value="${device.generateLightStateMap()}" />
				
				<c:forEach var="lightNo" items="${map.keySet()}">
<!-- 					<c:set var="light" value="${fn:replace(lightNo,'l','号左侧灯')}" /> -->
<!-- 					<c:set var="light" value="${fn:replace(light,'r','号右侧灯')}" /> -->
					${light}号灯状态: 
					<select name="L${lightNo}">
						<c:choose>
							<c:when test="${map.get(lightNo).equals(\"on\")}">
								<option value="on" selected="selected">开</option>
								<option value="of">关</option>
							</c:when>
							<c:otherwise>
								<option value="on">开</option>
								<option value="of" selected="selected">关</option>
							</c:otherwise>
						</c:choose>
					</select>
					<br />
				</c:forEach>
			</p>
	
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
</c:if>
<c:if test="${error != null }">
	<h1>${error}</h1>
</c:if>
