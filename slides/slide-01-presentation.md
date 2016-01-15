## Tout d'abord ...

Qu'est ce qu'une JSP ?

```jsp
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <title>Hello World ! </title>
    </head>
    <body>
    <p>Hello World !</p>
    <% String f = "EEEEE d MMMMM y - kk:mm"; %>
    <% SimpleDateFormat f = new SimpleDateFormat(f, Locale.FRANCE); %>
    <p>Il est actuellement : <%= f.format( new Date() ) %></p>
    </body>
</html>
```

____

## Et vous, ....

![Avez vous déjà vu](/images/avez-vous-deja-vu.png)

* Une JSP "propre" ?
* Un template engine simple ?

____

## L'intégration de Thymeleaf !

![Spring](/images/spring-logo.png)   ![Play](/images/play-logo.png)   ![Spark](/images/spark-logo.png)   ![Ratpack](/images/ratpack-logo.png)

* spring-boot-starter-thymeleaf
* play-thymeleaf-plugin
* ...

____

## Ou en Java ...

![Java](/images/java-logo.png)

![Assemblage](/images/java-thymeleaf.png)

____

## Mais finalement ?

A quoi ça ressemble ???

