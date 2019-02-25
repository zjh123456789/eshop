<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>EShop -- 身份认证失败</title>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-2.1.1.min.js" ></script>
</head>
<body>
<h1>未登陆或对话已过期，请重新登陆</h1>
<script>
    $(document).ready(function() {
        setTimeout(function () {
            window.location.href = "login";
        }, 2000);
    });
</script>
</body>
</html>
