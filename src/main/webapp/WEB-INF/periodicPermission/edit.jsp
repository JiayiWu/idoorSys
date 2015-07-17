<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">


  <form method="post" action="periodicPermission/update" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
    <div class="pageFormContent" layoutH="56">
      <p>
        <label>id：</label> <input name="id" type="text" size="30" value="${permission.id}" readonly="readonly" />
      </p>
      <p>
        <label>房间id：</label> <input name="roomId" class="required" type="text" size="30" value="${permission.room.id}" />
      </p>
      <p>
        <label>卡号：</label> <input name="cardNum" type="text" size="30" value="${permission.permission_user.card_num}" />
      </p>
      <p>
        <label>星期几：</label>
        <select name="dayOfWeek">
          <c:forEach var="i" begin="1" end="7">
            <c:choose>
              <c:when test="${i == permission.day_of_week}">
                <option value="${i}" selected="selected">${week[i-1]}</option>
              </c:when>
              <c:otherwise>
                <option value="${i}">${week[i-1]}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </p>
      <p>
        <label>起始时间：</label> <input name="beginTime" type="text" pattern="(0[0-9]|1[0-9]|2[0-3])(\:[0-5][0-9]){2}"
                                    title="00:00:00-23:59:59" value="${permission.begin_time}"/>
      </p>
      <p>
        <label>结束时间：</label> <input name="endTime" type="text" pattern="(0[0-9]|1[0-9]|2[0-3])(\:[0-5][0-9]){2}"
                                    title="00:00:00-23:59:59" value="${permission.end_time}"/>
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
