<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


	<form method="post" action="room/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="id" type="text" size="30"
					value="${room.id}" readonly="readonly" />
			</p>
			<p>
				<label>房间名称：</label> <input name="name" class="required" type="text"
					size="30" value="${room.name}" />
			</p>
			<p>
				<label>房间英文名称：</label> <input name="num" class="required" type="text"
					size="30" value="${room.num}" />
			</p>
			<p>
				<label>房间类型：</label> <input name="type" type="text" size="30"
					value="${room.type}" />
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
