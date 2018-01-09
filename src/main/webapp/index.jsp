<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<body>
<h2>Hello World!</h2>
<section id="topMenu" class="topMenu">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <div class="line"></div>
            </div>
            <div class="col-md-9 col-lg-9">
                <nav>
                    <ul class="menu d-flex align-items-center">
                        <li class="active"><a href="/con?controller.command=home" >${Main}</a ></li>
                        <li><a href="/con?controller.command=condition">${Condition}</a></li>
                        <li><a href="/con?controller.command=calculator">${Calculator}</a></li>
                        <li><a href="/con?controller.command=contact">${Contact}</a></li>
                        <%--<li><a href="/con?controller.command=order">${Order}</a></li>--%>
                    </ul>
                </nav>
            </div>
            <div class="col-md-3 col-lg-3 d-flex justify-content-end align-items-center ml-auto">
                <div class="lang">
                    <span class="lang__item"><a href="/con?controller.command=localeUa">Укр</a></span>
                    <span class="lang__item"><a href="/con?controller.command=localeEn">En</a></span>
                    <a href="/con?controller.command=signIn">${SignIn} <i class=" fa fa-sign-in"></i></a>
                </div>
            </div>
            <div class="col-md-12 col-lg-12">
                <div class="line"></div>
            </div>
        </div>
    </div>
</section>
<hr/>
<c:out value="${user}, Hello!"/>
<hr/>
<a href ="/con?controller.command=login">Login</a>
</body>
</html>
