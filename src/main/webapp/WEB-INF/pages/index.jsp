<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Welcome to Exhibitions center!</title>
</head>
<body>
<c:import url="header.jsp"/><br/>
<h3>Username from session: ${sessionScope.username}</h3>
<button type="submit" name="action"><a href="/pages/login.jsp">Login </a>
</button>
<c:set var="totalCount" scope="session" value="${lst.size()}"/>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>

<h1>Exhibitions</h1>
<table>
    <tr>
        <th>Number</th>
        <th>Id</th>
        <th>Theme</th>
        <th>Date start</th>
        <th>Date end</th>
        <th>Description</th>
        <th>Price</th>
        <th>Hall name</th>
        <th>Address</th>
        <th>City</th>
    </tr>
    <c:forEach var="objects" items="${lst}"
               begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr><c:forEach var="obj" items="${objects}">
            <td><c:out value="${obj}"></td></c:out>
        </c:forEach>
            <td>
                <button type="submit" name="action"><a href="/pages/login.jsp">Buy ticket </a></button>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage}
<a href="?start=${pageStart + perPage}">>></a>
</body>
</html>

