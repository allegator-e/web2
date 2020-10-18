<%--
  Created by IntelliJ IDEA.
  User: Tiloaria
  Date: 18.10.2020
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная 1</title>
    <link rel="icon" href="https://cdn.iconscout.com/icon/premium/png-256-thumb/orthodox-cross-742265.png"/>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body>
<header class="hat">
    Вариант 2723<br>Курашов Олег, Стефанская Полина<br>Группа P3231
</header>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div class="base">
    <p><img src="resources/cattt.gif" id="cat" alt="cat"></p>
    <div class="svg">
        <svg width="400" height="400" class="graph">
            <line class="axes" x1="0" x2="400" y1="200" y2="200" stroke="black"></line>
            <line x1="200" x2="200" y1="0" y2="400" stroke="black"></line>
            <polygon points="200,0 194,25 206,25" stroke="black"></polygon>
            <polygon points="400,200 375,206 375,194" stroke="black"></polygon>

            <line x1="260" x2="260" y1="205" y2="195" stroke="black"></line>
            <line x1="320" x2="320" y1="205" y2="195" stroke="black"></line>

            <line x1="80"  x2="80"  y1="205" y2="195" stroke="black"></line>
            <line x1="140" x2="140" y1="205" y2="195" stroke="black"></line>

            <line x1="195" x2="205" y1="140" y2="140" stroke="black"></line>
            <line x1="195" x2="205" y1="80"  y2="80"  stroke="black"></line>

            <line x1="195" x2="205" y1="260" y2="260" stroke="black"></line>
            <line x1="195" x2="205" y1="320" y2="320" stroke="black"></line>

            <text x="250" y="190">R/2</text>
            <text x="315" y="190">R</text>

            <text x="70" y="190">-R</text>
            <text x="125" y="190">-R/2</text>

            <text x="210" y="145">R/2</text>
            <text x="210" y="85">R</text>

            <text x="210" y="265">-R/2</text>
            <text x="210" y="325">-R</text>

            <polygon points="140,200 140,320 200,320, 200,200" fill="purple" fill-opacity="0.3" stroke="purple"></polygon>

            <path d="M 200,80 Q 320 80 320 200 L 200 200 Z" fill="purple" fill-opacity="0.3" stroke="purple"></path>

            <polygon points="140,200 200,200 200,80" fill="purple" fill-opacity="0.3" stroke="purple"></polygon>

            <%
                System.out.println("Enter parameter:\nX:"+request.getParameter("x")+"\nY:"+request.getParameter("y")+"\nR:"+request.getParameter("r"));
                String point = (String) request.getServletContext().getAttribute("point");

                if (point != null ) {
                    out.println(point);
                } else out.println("<p>There are no points yet!</p>");
            %>
        </svg>
    </div>
    <table class="result" cellspacing="0">
        <tr class="result_head">
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>HIT</th>
            <th>TIME</th>
            <th>RUNTIME</th>
        </tr>
        <%
            String row = (String) request.getServletContext().getAttribute("row");
            if (row != null ) {
                out.println(row);
            } else out.println("<p>There are no points yet!</p>");
        %>
    </table>
</div>
</body>
<div>
    <button id="back_but" onclick="location.href='/web2_war/'">Back</button>
</div>
<script src="scripts/script.js"></script>
</html>
