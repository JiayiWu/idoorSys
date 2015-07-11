<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="periodicPermission/pagedList">
  <input type="hidden" name="status" value="${param.status}">
  <input type="hidden" name="keywords" value="${param.keywords}" />
  <input type="hidden" name="pageNum" value="1" />
  <input type="hidden" name="numPerPage" value="${param.numPerPage==null ? 20: param.numPerPage}" />
  <input type="hidden" name="totalCount" value="${requestScope.totalCount}" />
</form>


<div class="pageHeader">
  <form onsubmit="return navTabSearch(this);" action="periodicPermission/findByExample" method="post">
    <div class="searchBar">
      <table class="searchContent">
        <tr>
          <td>房间名称：<input type="text" name="room_name" /></td>
          <td>用户名称：<input type="text" name="user_name" /></td>
        </tr>
        <tr>
          <td>星期几：
            <select name="dayOfWeek">
              <option value="-1">一整周</option>
              <c:forEach var="i" begin="1" end="7">
                <option value="${i}">${week[i-1]}</option>
              </c:forEach>
            </select>
          </td>
        </tr>
      </table>
      <div class="subBar">
        <ul>
          <li>
            <div class="buttonActive">
              <div class="buttonContent">
                <button type="submit">检索</button>
              </div>
            </div>
          </li>
          <%--<li>--%>
            <%--<a class="button" href="demo_page6.html" target="dialog"--%>
                 <%--mask="true" title="查询框"><span>高级检索</span></a>--%>
          <%--</li>--%>
        </ul>
      </div>
    </div>
  </form>
</div>
<div class="pageContent">
  <div class="panelBar">
    <ul class="toolBar">
      <li><a class="add" href="periodicPermission/page/multiAdd" target="navTab"><span>添加</span></a></li>
      <li><a class="delete" href="periodicPermission/delete/{sid_user}/" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
      <li><a class="edit" href="periodicPermission/page/edit/{sid_user}/" target="navTab"><span>修改</span></a></li>
      <li class="line">line</li>
      <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" Type="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
    </ul>
  </div>
  <table class="table" width="100%" layoutH="138">
    <thead>
    <tr>
      <th width="100" align="center">ID</th>
      <th width="100" align="center">房间号</th>
      <th width="100" align="center">星期几</th>
      <th width="100" align="center">起始时间</th>
      <th width="100" align="center">结束时间</th>
      <th width="100" align="center">用户名</th>
      <th width="100" align="center">卡号</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="permission" items="${permissions}">
      <tr target="sid_user" rel="${permission.id}">
        <td>${permission.id}</td>
        <td>${permission.room.name}</td>
        <td>${permission.dayOfWeek}</td>
        <td>${permission.beginTime}</td>
        <td>${permission.endTime}</td>
        <td>${permission.permissionUser.name}</td>
        <td>${permission.permissionUser.cardNum}</td>

      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="panelBar">
    <div class="pages">
      <span>显示</span>
      <select class="combox" name="numPerPage"
              onchange="navTabPageBreak({numPerPage:this.value})">
        <c:forEach var="nPG" items="20,50,100,200">
          <c:choose>
            <c:when test="${nPG==numPerPage}">
              <option value="${nPG}" selected="selected">${nPG}</option>
            </c:when>
            <c:otherwise>
              <option value="${nPG}">${nPG}</option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </select> <span>条，共${totalCount}条</span>
    </div>

    <div class="pagination" targetType="navTab" totalCount="${totalCount}"
         numPerPage="${numPerPage}" pageNumShown="${pageNumShown}" currentPage="${currentPage}"></div>

  </div>
</div>
