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
<!DOCTYPE html>

<nav>
        <ul >
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <li>Hello, <c:out value="${sessionScope.user.firstName}"/></li>
                    <li><a href='#' data-activates='dropdown1'><i >account_circle</i></a></li>
                    <ul id='dropdown1'>
                        <li><a href="account"><i >edit</i>Account</a></li>
                        <c:if test="${sessionScope.user.role eq 'admin'}">
                            <li><a href="admin"><i >build</i>Admin</a></li>
                        </c:if>
                        <li ></li>
                        <li><a href="?command=logout"><i>exit_to_app</i>Exit</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <li><a href="/pages/login.jsp">Sign in</a></li>
                    <li>or</li>
                    <li><a href="registration">Sign up</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
</nav>


