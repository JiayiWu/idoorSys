<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


	<form method="post" action="permission/update" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="id" type="text" size="30" value="${permission.id}" readonly="readonly" />
			</p>
			<p>
				<label>房间id：</label> <input name="roomId" class="required" type="text" size="30" value="${permission.room.id}" />
			</p>
			<p>
				<label>类型：</label> <input name="type" type="text" size="30" value="${permission.type}" />
			</p>
			<p>
				<label>卡号：</label> <input name="cardNum" type="text" size="30" value="${permission.permission_user.card_num}" />
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
