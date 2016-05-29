<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="static/css/default.css" rel="stylesheet"/>
    <script src="static/js/ajax.js"></script>
    <title>Shour</title>
</head>
<body>
<div class="topNav">
    小二
</div>
<div class="main">
    <div class="content">
        <div class="chatbot">
            <img src="static/image/shour.png" alt="shour">
        </div>
        <div class="dialog">
            <div class="history">
                <textarea id="history" rows="12" cols="60" disabled="true"></textarea>
            </div>
            <div class="question">
                <input id="question" type="text" style="height: 25px;" size="50px">
                <button style="height: 30px; width: 50px;" onclick="question()">提问</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>