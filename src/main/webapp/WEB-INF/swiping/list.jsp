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
  <form onsubmit="return navTabSearch(this);" action="swiping/findByExample" method="post">
    <div class="searchBar">
      <table class="searchContent">
        <input name="currentPage" type="hidden" value="${currentPage}"/>
        <tr>
          <td>房间名称：<input type="text" name="roomName" />
          </td>
          <td>用户名称：<input type="text" name="userName" />
          </td>
        </tr>
        <tr>
          <td>开始时间：<input type="date" dateFmt="yyyy-MM-dd HH:mm" name="startTime"/>
          </td>
          <td>结束时间：<input type="date" dateFmt="yyyy-MM-dd HH:mm" name="endTime"/>
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

<input name="currentPage" type="hidden" value="${currentPage}"/>
<div class="pageContent">
  <div class="panelBar">
    <ul class="toolBar">
      <c:choose>
        <c:when test="${currentPage.equals(\"anonymous\")}">
          <form onsubmit="return navTabSearch(this);" method="post" action="swiping/list"><input type="submit" value="切换到注册用户记录"></form>
        </c:when>
        <c:otherwise>
          <form onsubmit="return navTabSearch(this);" method="post" action="swiping/listAnonymous"><input type="submit" value="切换到匿名刷卡记录"></form>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <table class="table" width="100%" layoutH="138">
    <thead>
    <tr>
      <th width="100" align="center">ID</th>
      <th width="100" align="center">刷卡时间</th>
      <th width="100" align="center">用户名</th>
      <th width="100" align="center">房间</th>
      <th width="100" align="center">卡号</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="record" items="${swipingRecords}">
      <tr>
        <td>${record.id}</td>
        <td>${record.swipingTime}</td>
        <td>${record.userName}</td>
        <td>${record.roomName}</td>
        <td>${record.cardid}</td>
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
