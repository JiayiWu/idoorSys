<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


	<form method="post" action="permission/add" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>roomId:</label>
				<select name="roomId">
				 	<c:forEach var="id" items="${ids}">
					 	<option value="${id}">${id}</option>
				 	</c:forEach>
				</select>
			</p>
			<p>
				<label>cardNum：</label> 
				<select name="cardNum">
				 	<c:forEach var="cardNum" items="${cardNums}">
					 	<option value="${cardNum}">${cardNum}</option>
				 	</c:forEach>
				</select>
			</p>
			<p>
				<label>类型：</label> 
				<select name="type">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>
			</p>

			<div class="divider"></div>

		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
