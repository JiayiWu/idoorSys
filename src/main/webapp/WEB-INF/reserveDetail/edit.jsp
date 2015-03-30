<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


	<form method="post" action="reserveDetail/update"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="id" type="text" size="30"
					value="${reserveDetail.id}" readonly="readonly" />
			</p>
			<p>
				<label>用户id：</label> <input name="userid" class="required"
					type="text" size="30" value="${reserveDetail.userid}" />
			</p>
			<p>
				<label>门号：</label> <input name="roomNum" type="text" size="30"
					value="${reserveDetail.roomNum}" />
			</p>
			<p>
				<label>进入时间：</label> <input name="inTime" type="text" size="30"
					datefmt="yyyy-MM-dd HH:mm:ss" class="date textInput valid"
					value="${reserveDetail.inTime}" /> <a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>出去时间：</label> <input name="outTime" type="text" size="30"
					datefmt="yyyy-MM-dd HH:mm:ss" class="date textInput valid"
					value="${reserveDetail.outTime}" /> <a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>门号</label> <input name="doorNum" type="text" size="30"
					value="${reserveDetail.doorNum}" />
			</p>
			<p>
				<label>桌号：</label> <input name="deskNum" type="text" size="30"
					value="${reserveDetail.deskNum}" />
			</p>
			<p>
				<label>桌子左右：</label> <input name="deskLeftRight" type="text"
					size="30" value="${reserveDetail.deskLeftRight}" />
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
