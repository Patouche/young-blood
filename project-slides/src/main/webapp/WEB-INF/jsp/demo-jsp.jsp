<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Thymeleaf - A simple demo</title>
    <meta name="description" content="A presentation of a modern template engine to make your front simple than ever !"/>
    <meta name="author" content="Patrick Allain"/>

    <link rel="stylesheet" href="<c:url value="/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/bootstrap/3.3.6/css/bootstrap-theme.min.css"/>"/>

    <script src="<c:url value="/assets/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/assets/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
</head>
<body>

<div class="container">

    <div class="navbar navbar-inverse">
        <div class="container">
            <div class="collapse navbar-collapse">
                <a class="navbar-brand">Demo</a>
                <ul class="nav navbar-nav">
                    <li>
                        <c:url var="slideLink" value="/slides"/>
                        <a href="${slideLink}">Slides</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <h1>
        <spring:message code="demo.title"/>
    </h1>

    <div class="container">
        <p>Hello ${user} !</p>
        <% String f = "EEEEE d MMMMM y - kk:mm"; %>
        <% SimpleDateFormat df = new SimpleDateFormat(f); %>
        <p>Il est actuellement : <%= df.format(new Date()) %>
        </p>
    </div>

    <div class="container">
        <table class="table">
            <tr>
                <th>Index</th>
                <th>Fichier</th>
                <th>Nb slides</th>
                <th>Voir</th>
            </tr>
            <c:forEach var="s" items="${slides}">
                <tr>
                    <td>${s.index}</td>
                    <td>${s.fileName}</td>
                    <td>${s.pages.size()}</td>
                    <td>
                        <spring:url var="url" value="/demo/{index}">
                            <spring:param name="index" value="${s.index}"/>
                        </spring:url>
                        <a href="${url}">Voir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>


</body>
</html>