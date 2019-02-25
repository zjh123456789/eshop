<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录--小说 后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${ctxStatic}/main/favicon.ico">
    <link rel="stylesheet" href="${ctxStatic}/main/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/main/css/public.css" media="all" />
</head>
<body class="loginBody">
<form class="layui-form" method="post" action="login">
    <div class="login_face"><img src="${ctxStatic}/main/images/face.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="userName">用户名</label>
        <input type="text" placeholder="请输入用户名" autocomplete="off" name="username" id="username" class="layui-input" lay-verify="required">
    </div>
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" name="password" id="password" class="layui-input" lay-verify="required">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
    </div>
    <h5 style="color:red ">${message}</h5>
</form>
<script type="text/javascript" src="${ctxStatic}/main/layui/layui.js"></script>
<script type="text/javascript" src="${ctxStatic}/main/js/login.js"></script>
<script type="text/javascript" src="${ctxStatic}/main/js/cache.js"></script>
</body>
</html>
