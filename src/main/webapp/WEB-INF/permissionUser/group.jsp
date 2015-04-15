<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageContent" style="font-size:20px">
  <div class="page unitBox">
    <div class="accountInfo">
    </div>
    <div class="pageFormContent" layoutH="80" style="margin-right:230px">
      <form method="post" action="permissionUser/deleteGroup"
            class="pageForm required-validate"
            onsubmit="return validateCallback(this, navTabAjaxDone);">
        <ul>
          <c:forEach var="group" items="${groups}">
            <li>
            <input name="groupToDelete" type="checkbox" value="${group.getName()}">
            ${group.getName()}
            </li>
          </c:forEach>
        </ul>
        <input type="submit" value="删除">
      </form>
    </div>
  </div>
</div>
