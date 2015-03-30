<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">

	<form method="post" action="permissionUser/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="id" type="text" size="30"
					value="${room.id}" readonly="readonly" />
			</p>
			<p>
				<label>用户姓名：</label> <input name="name" class="required" type="text"
					size="30" value="${room.name}" />
			</p>
			<p>
				<label>用户学号：</label> <input name="stdNum" class="required"
					type="text" size="30" value="${room.name}" />
			</p>
			<p>
				<label>用户卡号：</label> <input id="cardNum" name="cardNum"
					class="required" type="text" size="30" value="${room.name}" />
			</p>
			<p>
				<label>用户类型：</label> <input name="type" type="text" size="30"
					value="${room.type}" />
			</p>
			<p>
				<label>用户电话：</label> <input name="phone" type="tel" size="30"
					value="${room.phone}" />
			</p>
			<p>
				<label>用户组别：</label> <select name="group">
					<option value="学生">学生</option>
					<option value="教师">教师</option>
					<option value="工作人员">工作人员</option>
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
<script type="text/javascript">
	$(function() {
		$("#cardNum").keydown(function(e){var key = window.event?e.keyCode:e.which;if(key==13){return false;}})
		$("#cardNum").change(function() {
			var cardNum = $(this).val();
			if(cardNum.length>=7){
				$.post("permissionUser/getCardNumOX",{'cardNum':cardNum},function(data){
					$("#cardNum").val(data.cardNumOX);
				},'json');
			}
		});

	});
</script>
