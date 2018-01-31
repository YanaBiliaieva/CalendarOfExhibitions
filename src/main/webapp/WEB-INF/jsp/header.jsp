<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 14.01.2018
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="column side"></div>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<table>
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'USER'}">
            <fmt:message key="hello" />,<c:out value="${sessionScope.user.firstname}"/>
            <form action="logout" method="get">
                <button type="submit">
                    <fmt:message key="logout" />
                </button>
            </form>
            <form action="order" method="get">
                <button type="submit">
                    <fmt:message key="your_orders" />
                </button>
            </form>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <fmt:message key="hello" />, <c:out value="${sessionScope.user.firstname}"/>
            <form action="adminpanel" method="get">
                <button type="submit">
                    <fmt:message key="admin_panel" />
                </button>
            </form>
            <form action="logout" method="get">
                <button type="submit">
                    <fmt:message key="logout" />
                </button>
            </form>
            <form action="cabinet" method="get">
                <button type="submit">
                    <fmt:message key="your_orders" />
                </button>
            </form>
        </c:when>

        <c:otherwise>
            <form action="login" method="get">
                <button type="submit">
                    <fmt:message key="login" />
                </button>
            </form>
            <form action="register" method="get">
                <button type="submit">
                    <fmt:message key="registration" />
                </button>
            </form>

        </c:otherwise>
    </c:choose>
    <form action="exhibitions" method="get">
        <button type="submit">
            <fmt:message key="home_page" />
        </button>
    </form>
</table>
</div>





