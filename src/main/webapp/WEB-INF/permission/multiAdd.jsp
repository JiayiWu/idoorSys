<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent" style="overflow:hidden">

	<div style="width:50%; float:left">
		<div id="panel_user" class="panel" style="display: block;">
			<div class="panelHeader">
				<div class="panelHeaderContent">
					<h1 style="cursor: move;">用户</h1>
				</div>
			</div>
			<div class="panelContent" style="height: auto;">
				<div class="panelBar">
					<ul class="toolBar">
						<li><span>用户组别： </span> <select class="" id="select_group">
								<option value="all">所有用户</option>
								<option value="学生">学生</option>
								<option value="教师">教师</option>
								<option value="工作人员">工作人员</option>
								<option value="其它">其它</option>
						</select></li>
						<li class=""><a class="add"><span>添加</span></a></li>
					</ul>

				</div>
				<div class="panelBar">
					<ul class="toolBar">
						<li><span>用户名：</span></li>
						<li><input id="searchUserName" type="text" /></li>
						<li></li>
						<button onclick="searchUser()">检索</button>
						</li>
					</ul>
				</div>
				<div class="grid">
					<div class="gridHeader">
						<div class="gridThead" style="position: relative; left: 0px;">
							<table style="width:100%">
								<thead>
									<tr>
										<th style="width: 18px; cursor: col-resize;" class=""><div
												class="gridCol" title="">
												<input type="checkbox" group="ids" class="checkboxCtrl">
											</div></th>
										<th orderfield="accountNo" class="asc"
											style=" cursor: pointer;"><div class="gridCol"
												title="客户号">卡号</div></th>
										<th orderfield="accountName" style=" cursor: pointer;"><div
												class="gridCol" title="客户名称">姓名</div></th>
										<th orderfield="accountType" style="cursor: pointer;"><div
												class="gridCol" title="客户类型">工号</div></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="gridTbody">
						<table style="width:100%;overflow: auto" layoutH="470">
							<tbody>
								<c:forEach var="puser" items="${pusers}">
									<tr target="sid_user" rel="${puser.cardNum}">
										<td style="width: 18px;"><div>
												<input name="ids" value="xxx" type="checkbox">
											</div></td>
										<td><div>${puser.cardNum}</div></td>
										<td><div>${puser.name}</div></td>
										<td><div>${puser.stdNum}</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<div class="combox">
							<div id="combox_5513881" class="select">
								<a href="javascript:" class="" name="numPerPage" value="20">20</a><select
									class="" name="numPerPage"
									onchange="navTabPageBreak({numPerPage:this.value})">
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<span>条，共${totalCount}条</span>
					</div>

					<div class="pagination" targettype="navTab" totalcount="200"
						numperpage="20" pagenumshown="10" currentpage="1">
						<ul>
							<li class="j-first disabled"><a class="first"
								href="javascript:;" style="display: none;"><span>首页</span></a> <span
								class="first"><span>首页</span></span></li>
							<li class="j-prev disabled"><a class="previous"
								href="javascript:;" style="display: none;"><span>上一页</span></a>
								<span class="previous"><span>上一页</span></span></li>
							<li class="selected j-num"><a href="javascript:;">1</a></li>
							<li class="j-num"><a href="javascript:;">2</a></li>
							<li class="j-num"><a href="javascript:;">3</a></li>
							<li class="j-num"><a href="javascript:;">4</a></li>
							<li class="j-num"><a href="javascript:;">5</a></li>
							<li class="j-num"><a href="javascript:;">6</a></li>
							<li class="j-num"><a href="javascript:;">7</a></li>
							<li class="j-num"><a href="javascript:;">8</a></li>
							<li class="j-num"><a href="javascript:;">9</a></li>
							<li class="j-num"><a href="javascript:;">10</a></li>
							<li class="j-next"><a class="next" href="javascript:;"><span>下一页</span></a>
								<span class="next" style="display: none;"><span>下一页</span></span>
							</li>
							<li class="j-last"><a class="last" href="javascript:;"><span>末页</span></a>
								<span class="last" style="display: none;"><span>末页</span></span>
							</li>
							<li class="jumpto"><input class="textInput" type="text"
								size="4" value="1"><input class="goto" type="button"
								value="确定"></li>
						</ul>
					</div>

				</div>
			</div>
			<div class="panelFooter">
				<div class="panelFooterContent"></div>
			</div>
		</div>
		<div id="panel_room" class="panel" style="display: block;">
			<div class="panelHeader">
				<div class="panelHeaderContent">
					<h1 style="cursor: move;">房间</h1>
				</div>
			</div>
			<div class="panelContent" style="height: auto;">
				<div class="panelBar">
					<ul class="toolBar">
						<li class=""><a class="add"><span>添加</span></a></li>
					</ul>
				</div>
				<div class="panelBar">
					<ul class="toolBar">
						<li><span>房间名：</span></li>
						<li><input id="searchRoomName" type="text" /></li>
						<li><button onclick="searchRoom()">检索</button></li>
					</ul>
				</div>
				<div class="grid">
					<div class="gridHeader">
						<div class="gridThead" style="position: relative; left: 0px;">
							<table style="width:100%;">
								<thead>
									<tr>
										<th style="width: 18px; cursor: col-resize;" class=""><div
												class="gridCol" title="">
												<input type="checkbox" group="ids" class="checkboxCtrl">
											</div></th>
										<th orderfield="accountNo" class="asc"
											style="cursor: pointer;"><div class="gridCol"
												title="客户号">房间名称</div></th>
										<th orderfield="accountName" style=" cursor: pointer;"><div
												class="gridCol" title="客户名称">房价类型</div></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="gridTbody">
						<table style="width:100%;" layoutH="470">
							<tbody>
								<c:forEach var="room" items="${rooms}">
									<tr target="sid_user" rel="${room.id}" class="">
										<td style="width: 18px;"><div>
												<input name="ids" value="xxx" type="checkbox">
											</div></td>
										<td style=""><div>${room.name }</div></td>
										<td style=""><div>${room.type }</div></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<div class="combox">
							<div id="combox_5513881" class="select">
								<a href="javascript:" class="" name="numPerPage" value="20">20</a><select
									class="" name="numPerPage"
									onchange="navTabPageBreak({numPerPage:this.value})">
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<span>条，共${totalCount}条</span>
					</div>

					<div class="pagination" targettype="navTab" totalcount="200"
						numperpage="20" pagenumshown="10" currentpage="1">
						<ul>
							<li class="j-first disabled"><a class="first"
								href="javascript:;" style="display: none;"><span>首页</span></a> <span
								class="first"><span>首页</span></span></li>
							<li class="j-prev disabled"><a class="previous"
								href="javascript:;" style="display: none;"><span>上一页</span></a>
								<span class="previous"><span>上一页</span></span></li>
							<li class="selected j-num"><a href="javascript:;">1</a></li>
							<li class="j-num"><a href="javascript:;">2</a></li>
							<li class="j-num"><a href="javascript:;">3</a></li>
							<li class="j-num"><a href="javascript:;">4</a></li>
							<li class="j-num"><a href="javascript:;">5</a></li>
							<li class="j-num"><a href="javascript:;">6</a></li>
							<li class="j-num"><a href="javascript:;">7</a></li>
							<li class="j-num"><a href="javascript:;">8</a></li>
							<li class="j-num"><a href="javascript:;">9</a></li>
							<li class="j-num"><a href="javascript:;">10</a></li>
							<li class="j-next"><a class="next" href="javascript:;"><span>下一页</span></a>
								<span class="next" style="display: none;"><span>下一页</span></span>
							</li>
							<li class="j-last"><a class="last" href="javascript:;"><span>末页</span></a>
								<span class="last" style="display: none;"><span>末页</span></span>
							</li>
							<li class="jumpto"><input class="textInput" type="text"
								size="4" value="1"><input class="goto" type="button"
								value="确定"></li>
						</ul>
					</div>

				</div>
			</div>
			<div class="panelFooter">
				<div class="panelFooterContent"></div>
			</div>
		</div>
	</div>
	<div style="width:50%; float:left">
		<div id="panel_user_ready" class="panel" style="display: block;">
			<div class="panelHeader">
				<div class="panelHeaderContent">
					<h1 style="cursor: move;">准备添加的用户</h1>
				</div>
			</div>
			<div class="panelContent" style="height: auto;">
				<div class="panelBar">
					<ul class="toolBar">
						<li class=""><a class="delete"><span>移除</span></a></li>
					</ul>
				</div>
				<div class="grid">
					<div class="gridHeader">
						<div class="gridThead" style="position: relative; left: 0px;">
							<table style="width:1180px;">
								<thead>
									<tr>
										<th style="width: 18px; cursor: col-resize;" class=""><div
												class="gridCol" title="">
												<input type="checkbox" group="ids" class="checkboxCtrl">
											</div></th>
										<th orderfield="accountNo" class="asc"
											style=" cursor: pointer;"><div class="gridCol"
												title="客户号">卡号</div></th>
										<th orderfield="accountName" style=" cursor: pointer;"><div
												class="gridCol" title="客户名称">姓名</div></th>
										<th orderfield="accountType" style="cursor: pointer;"><div
												class="gridCol" title="客户类型">工号</div></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="gridTbody">
						<table style="width:1180px;" layoutH="470">
							<tbody>

							</tbody>
						</table>
					</div>
					<div class="resizeMarker"
						style="height:300px; left:57px;display:none;"></div>
					<div class="resizeProxy"
						style="height:300px; left:377px;display:none;"></div>
				</div>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<div class="combox">
							<div id="combox_5513881" class="select">
								<a href="javascript:" class="" name="numPerPage" value="20">20</a><select
									class="" name="numPerPage"
									onchange="navTabPageBreak({numPerPage:this.value})">
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<span>条，共${totalCount}条</span>
					</div>

					<div class="pagination" targettype="navTab" totalcount="200"
						numperpage="20" pagenumshown="10" currentpage="1">
						<ul>
							<li class="j-first disabled"><a class="first"
								href="javascript:;" style="display: none;"><span>首页</span></a> <span
								class="first"><span>首页</span></span></li>
							<li class="j-prev disabled"><a class="previous"
								href="javascript:;" style="display: none;"><span>上一页</span></a>
								<span class="previous"><span>上一页</span></span></li>
							<li class="selected j-num"><a href="javascript:;">1</a></li>
							<li class="j-num"><a href="javascript:;">2</a></li>
							<li class="j-num"><a href="javascript:;">3</a></li>
							<li class="j-num"><a href="javascript:;">4</a></li>
							<li class="j-num"><a href="javascript:;">5</a></li>
							<li class="j-num"><a href="javascript:;">6</a></li>
							<li class="j-num"><a href="javascript:;">7</a></li>
							<li class="j-num"><a href="javascript:;">8</a></li>
							<li class="j-num"><a href="javascript:;">9</a></li>
							<li class="j-num"><a href="javascript:;">10</a></li>
							<li class="j-next"><a class="next" href="javascript:;"><span>下一页</span></a>
								<span class="next" style="display: none;"><span>下一页</span></span>
							</li>
							<li class="j-last"><a class="last" href="javascript:;"><span>末页</span></a>
								<span class="last" style="display: none;"><span>末页</span></span>
							</li>
							<li class="jumpto"><input class="textInput" type="text"
								size="4" value="1"><input class="goto" type="button"
								value="确定"></li>
						</ul>
					</div>

				</div>
			</div>
			<div class="panelFooter">
				<div class="panelFooterContent"></div>
			</div>
		</div>
		<div id="panel_room_ready" class="panel" style="display: block;">
			<div class="panelHeader">
				<div class="panelHeaderContent">
					<h1 style="cursor: move;">准备添加的房间</h1>
				</div>
			</div>
			<div class="panelContent" style="height: auto;">
				<div class="panelBar">
					<ul class="toolBar">
						<li class=""><a class="delete"><span>移除</span></a></li>
					</ul>
				</div>
				<div class="grid">
					<div class="gridHeader">
						<div class="gridThead" style="position: relative; left: 0px;">
							<table style="width:1180px;">
								<thead>
									<tr>
										<th style="width: 18px; cursor: col-resize;" class=""><div
												class="gridCol" title="">
												<input type="checkbox" group="ids" class="checkboxCtrl">
											</div></th>
										<th orderfield="accountNo" class="asc"
											style="cursor: pointer;"><div class="gridCol"
												title="客户号">房间名称</div></th>
										<th orderfield="accountName" style=" cursor: pointer;"><div
												class="gridCol" title="客户名称">房价类型</div></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="gridTbody" style="height: 150px; overflow: auto;">
						<table layoutH="470">
							<tbody>

							</tbody>
						</table>
					</div>
					<div class="resizeMarker"
						style="height:300px; left:57px;display:none;"></div>
					<div class="resizeProxy"
						style="height:300px; left:377px;display:none;"></div>
				</div>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<div class="combox">
							<div id="combox_5513881" class="select">
								<a href="javascript:" class="" name="numPerPage" value="20">20</a><select
									class="" name="numPerPage"
									onchange="navTabPageBreak({numPerPage:this.value})">
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
						</div>
						<span>条，共${totalCount}条</span>
					</div>

					<div class="pagination" targettype="navTab" totalcount="200"
						numperpage="20" pagenumshown="10" currentpage="1">
						<ul>
							<li class="j-first disabled"><a class="first"
								href="javascript:;" style="display: none;"><span>首页</span></a> <span
								class="first"><span>首页</span></span></li>
							<li class="j-prev disabled"><a class="previous"
								href="javascript:;" style="display: none;"><span>上一页</span></a>
								<span class="previous"><span>上一页</span></span></li>
							<li class="selected j-num"><a href="javascript:;">1</a></li>
							<li class="j-num"><a href="javascript:;">2</a></li>
							<li class="j-num"><a href="javascript:;">3</a></li>
							<li class="j-num"><a href="javascript:;">4</a></li>
							<li class="j-num"><a href="javascript:;">5</a></li>
							<li class="j-num"><a href="javascript:;">6</a></li>
							<li class="j-num"><a href="javascript:;">7</a></li>
							<li class="j-num"><a href="javascript:;">8</a></li>
							<li class="j-num"><a href="javascript:;">9</a></li>
							<li class="j-num"><a href="javascript:;">10</a></li>
							<li class="j-next"><a class="next" href="javascript:;"><span>下一页</span></a>
								<span class="next" style="display: none;"><span>下一页</span></span>
							</li>
							<li class="j-last"><a class="last" href="javascript:;"><span>末页</span></a>
								<span class="last" style="display: none;"><span>末页</span></span>
							</li>
							<li class="jumpto"><input class="textInput" type="text"
								size="4" value="1"><input class="goto" type="button"
								value="确定"></li>
						</ul>
					</div>
				</div>
				<div style="width:100%;float:left;margin-top:10px" layoutH="600">
					<span>权限类型： </span> <select class="" id="select_permissionType"
						value="always">
						<option value="always">always</option>
					</select> <a id="btn_submit" class="button" href="javascript:;"
						style="margin-left:"><span>添加权限</span></a>
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	function searchUser() {
		var userName = $("#searchUserName").val();
		$
				.post(
						"permissionUser/findByExampleJson",
						{
							"name" : userName
						},
						function(data) {
							$("#panel_user tbody").html("");
							var pusers = data.pusers;
							for (var i = 0; i < data.pusers.length; i++) {
								$("#panel_user tbody")
										.append(
												"<tr><td style='width: 18px;''><div><input name='ids' value='xxx' type='checkbox'></div></td><td><div>"
														+ pusers[i].cardNum
														+ "</div></td><td><div>"
														+ pusers[i].name
														+ "</div></td></tr>");
							}
						}, "json");
	}
	function searchRoom() {
		var roomName = $("#searchRoomName").val();
		$
				.post(
						"room/findByExampleJson",
						{
							"name" : roomName
						},
						function(data) {
							$("#panel_room tbody").html("");
							var rooms = data.rooms;
							for (var i = 0; i < rooms.length; i++) {
								$("#panel_room tbody")
										.append(
												"<tr><td style='width: 18px;''><div><input name='ids' value='xxx' type='checkbox'></div></td><td><div>"
														+ rooms[i].name
														+ "</div></td></tr>");
							}
						}, "json");
	}
</script>

<script type="text/javascript">
	$(function() {
		$("#panel_user .add").click(
				function() {
					$("#panel_user :checkbox[name='ids']:checked").parent()
							.parent().parent().each(function(i, item) {
								$("#panel_user_ready tbody").append(this)
							});
				});
		$("#panel_user_ready .delete").click(
				function() {
					$("#panel_user_ready :checkbox[name='ids']:checked")
							.parent().parent().parent().each(function(i, item) {
								$("#panel_user tbody").append(this)
							});
				});

		$("#panel_room .add").click(
				function() {
					$("#panel_room :checkbox[name='ids']:checked").parent()
							.parent().parent().each(function(i, item) {
								$("#panel_room_ready tbody").append(this);
							});
				});
		$("#panel_room_ready .delete").click(
				function() {
					$("#panel_room_ready :checkbox[name='ids']:checked")
							.parent().parent().parent().each(function(i, item) {
								$("#panel_room tbody").append(this);
							});
				});
		$("#btn_submit").click(
				function() {
					var users = [];
					var rooms = [];
					var type = $("#select_permissionType").val();
					$("#panel_user_ready :checkbox[name='ids']").parent()
							.parent().parent().each(function(i, item) {
								users.push($(this).attr("rel"));
							});
					$("#panel_room_ready :checkbox[name='ids']").parent()
							.parent().parent().each(function(i, item) {
								rooms.push($(this).attr("rel"));
							});
					$.post("permission/multiAdd", {
						"users" : users,
						"rooms" : rooms,
						"type" : type
					}, function(data) {
						alertMsg.correct('success');
					});
				});

		$("#select_group")
				.change(
						function() {
							var group = $(this).val();
							$
									.post(
											"permissionUser/getByGroup",
											{
												'group' : group
											},
											function(data) {
												$("#panel_user tbody").html("");
												for (var i = 0; i < data.pusers.length; i++) {
													var puser = data.pusers[i];
													var text = "<tr><td style='width: 18px;'><div><input name='ids' value='xxx' type='checkbox'></div></td><td><div>"
															+ puser.cardNum
															+ "</div></td><td><div>"
															+ puser.name
															+ "</div></td></tr>";
													$("#panel_user tbody")
															.append(text);
												}
												//console.log(data);
											}, 'json');

						});

	});
</script>
