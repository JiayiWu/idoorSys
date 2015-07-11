<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="appliance/listDevice" method="post">
		<input type="hidden" name="fromPage" value="door">
		<div class="searchBar">
			<table class="searchContent">
				<c:set var="roomNo" value="${deviceState.getRoomNo()}"/>
				<tr>
					<td>
					<select
						name="building" >
							<c:forEach var="building" items="${buildings}">
								<c:choose>
									<c:when test="${deviceState.getRoomNo().substring(0,2).equals(building) }">
										<option value="${building}" selected="selected">${building}</option>
									</c:when>
									<c:otherwise>
										<option value="${building}">${building}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					幢
					</td>
					<td>
					<select
						name="unit" >
							<c:forEach var="unit" items="${units}">
								<c:choose>
									<c:when test="${deviceState.getRoomNo().substring(2,4).equals(unit) }">
										<option value="${unit}" selected="selected">${unit}</option>
									</c:when>
									<c:otherwise>
										<option value="${unit}">${unit}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					单元
					</td>
					<td>
					<select
						name="floor" >
							<c:forEach var="floor" items="${floors}">
								<c:choose>
									<c:when test="${deviceState.getRoomNo().substring(4,5).equals(floor) }">
										<option value="${floor}" selected="selected">${floor}</option>
									</c:when>
									<c:otherwise>
										<option value="${floor}">${floor}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					层
					</td>
					<td>
					<select
						name="room" >
							<c:forEach var="room" items="${rooms}">
								<c:choose>
									<c:when test="${deviceState.getRoomNo().substring(5,7).equals(room) }">
										<option value="${room}" selected="selected">${room}</option>
									</c:when>
									<c:otherwise>
										<option value="${room}">${room}</option>
									</c:otherwise>
								</c:choose>
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
<c:if test="${deviceState != null }">
	<div class="pageContent">
	
		<form method="post" action="appliance/send"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">

			<input type="hidden" name="fromPage" value="door">
			<input type="hidden" name="roomNo"
				   value="${deviceState.getRoomNo()}" />
			<p>
				<c:if test="${deviceState.getFrontDoorState()!=null}">
					前门
					<select name="frontDoorState">
						<c:choose>
							<c:when test="${deviceState.getFrontDoorState().equals(\"on\")}">
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
				<c:if test="${deviceState.getBackDoorState()!=null}">
					后门 
					<select name="backDoorState">
						<c:choose>
							<c:when test="${deviceState.getBackDoorState().equals(\"on\")}">
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
