<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


	<form method="post" 
		action="remoteRoomUser/update" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="id" type="text" size="30"
					value="${ruser.id}" readonly="readonly" />
			</p>
			<p>
				<label>用户id：</label> <input name="userid" class="required" type="text"
					size="30" value="${ruser.userid}" />
			</p>
			<p>
				<label>用户名字：</label> <input name="username" type="text" size="30"
					value="${ruser.username}" />
			</p>
				<p>
				<label>用户卡号：</label> <input name="cardid" type="text" size="30"
					value="${ruser.cardid}" />
			</p>
				<p>
				<label>用户身份：</label> <input name="userIdentity" type="text" size="30"
					value="${ruser.userIdentity}" />
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
