<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页--小说 后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctxStatic}/main/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/main/css/public.css" media="all" />
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote layui-bg-green">
    <div id="nowTime"></div>
</blockquote>
<div class="layui-row layui-col-space10 panel_box">
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="http://fly.layui.com/case/u/3198216" target="_blank">
            <div class="panel_icon layui-bg-green">
                <i class="layui-anim seraph icon-good"></i>
            </div>
            <div class="panel_word">
                <span>99</span>
                <cite>点赞</cite>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="https://github.com/BrotherMa/layuicms2.0">
            <div class="panel_icon layui-bg-black">
                <i class="layui-anim seraph icon-github"></i>
            </div>
            <div class="panel_word">
                <span>99</span>
                <cite>小说类别</cite>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="https://gitee.com/layuicms/layuicms2.0">
            <div class="panel_icon layui-bg-red">
                <i class="layui-anim seraph icon-oschina"></i>
            </div>
            <div class="panel_word">
                <span>99</span>
                <cite>小说</cite>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="page/user/userList.html">
            <div class="panel_icon layui-bg-orange">
                <i class="layui-anim seraph icon-icon10" data-icon="icon-icon10"></i>
            </div>
            <div class="panel_word userAll">
                <span>99</span>
                <em>会员</em>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="page/systemSetting/icons.html">
            <div class="panel_icon layui-bg-cyan">
                <i class="layui-anim layui-icon" data-icon="&#xe857;">&#xe857;</i>
            </div>
            <div class="panel_word outIcons">
                <span>99</span>
                <em>评论</em>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;">
            <div class="panel_icon layui-bg-blue">
                <i class="layui-anim seraph icon-clock"></i>
            </div>
            <div class="panel_word">
                <span>99</span>
                <cite>访问量</cite>
            </div>
        </a>
    </div>
</div>

<script type="text/javascript" src="${ctxStatic}/main/layui/layui.js"></script>
<script type="text/javascript" src="${ctxStatic}/main/js/main.js"></script>
</body>
</html>
