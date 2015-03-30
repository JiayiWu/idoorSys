<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="status" value="${param.status}"> <input
		type="hidden" name="keywords" value="${param.keywords}" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${model.numPerPage}" /> <input type="hidden"
		name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
		<div class="searchBar">
			<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
			<table class="searchContent">
				<tr>
					<td>我的客户：<input type="text" name="keyword" />
					</td>
					<td><select class="combox" name="province">
							<option value="">所有省市</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="天津">天津</option>
							<option value="重庆">重庆</option>
							<option value="广东">广东</option>
					</select></td>
					<td>建档日期：<input type="text" class="date" readonly="true" />
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
					<li><a class="button" href="demo_page6.html" target="dialog"
						mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="reserveDetail/page/add/"
				target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="reserveDetail/delete/{sid_user}/"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="reserveDetail/page/edit/{sid_user}/"
				target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" Type="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">id</th>
				<th width="120" align="center">用户卡号</th>
				<th width="120" align="center">房间号码</th>
				<th width="120" align="center">进入时间</th>
				<th width="120" align="center">出来时间</th>
				<th width="120" align="center">门号</th>
				<th width="120" align="center">桌号</th>
				<th width="120" align="center">桌子左右</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reserveDetail" items="${reserveDetails}">
				<tr target="sid_user" rel="${reserveDetail.id}">
					<td>${reserveDetail.id}</td>
					<td>${reserveDetail.userid}</td>
					<td>${reserveDetail.roomNum}</td>
					<td>${reserveDetail.inTime}</td>
					<td>${reserveDetail.outTime}</td>
					<td>${reserveDetail.doorNum}</td>
					<td>${reserveDetail.deskNum}</td>
					<td>${reserveDetail.deskLeftRight}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="200"
			numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
