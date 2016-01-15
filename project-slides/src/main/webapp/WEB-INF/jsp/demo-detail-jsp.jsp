<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Thymeleaf - A simple demo</title>
    <meta name="description" content="A presentation of a modern template engine to make your front simple than ever !"/>
    <meta name="author" content="Patrick Allain"/>

    <link rel="stylesheet" href="<c:url value="/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/bootstrap/3.3.6/css/bootstrap-theme.min.css"/>"/>

    <script src="<c:url value="/jquery/2.1.4/jquery.min.js"/>"></script>
    <script src="<c:url value="/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>

</head>
<body>

<div class="container">

    <div class="navbar navbar-inverse">
        <div class="container">
            <div class="collapse navbar-collapse">
                <a class="navbar-brand">Demo</a>
                <ul class="nav navbar-nav">
                    <li>
                        <spring:url value="/slides" var="slideLink"/>
                        <a href="${slideLink}">Slides</a>
                    </li>
                    <li>
                        <spring:url value="/demo" var="demoLink"/>
                        <a href="${demoLink}">Demo</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <h1>
        <spring:message code="demo.detail.title" arguments="${slide.fileName}"/>
    </h1>

    <div class="container">${slide.content}</div>

</div>

</body>
</html>