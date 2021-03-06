<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>待出售商品管理</title>
    <%@ include file="/webpage/include/head.jsp" %>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var validateForm;

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if (validateForm.form()) {
                $("#inputForm").submit();
                return true;
            }
            return false;
        }

        $(document).ready(function () {
            validateForm = $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

            laydate({
                elem: '#startDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
            laydate({
                elem: '#endDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
        });
    </script>
</head>
<body class="hideScroll">
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
<tbody>
<tr>
    <td class="width-15 active"><label class="pull-right">商品展示：</label></td>
    <td class="width-35">
        <img src="${operate.goods.picture}" height="100px" width="100px" class="img-thumbnail">
    </td>
    <td class="width-15 active"><label class="pull-right">商品描述：</label></td>
    <td class="width-35">${operate.goods.introduce}</td>
</tr>
</tbody>
</table>
<form:form id="inputForm" modelAttribute="operate" action="${ctx}/operate/save" method="post" class="form-horizontal" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>待出售商品名：</label></td>
            <td class="width-35">
                <form:select path="goods.id" class="form-control">
                    <form:options items="${fns:getGoodsList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select></td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>产生积分：</label></td>
            <td class="width-35">
                <form:input path="integral" htmlEscape="false" class="form-control required"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>售价（￥）：</label></td>
            <td class="width-35">
                <form:input path="sellPrice" htmlEscape="false" class="form-control required"/>
            </td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>原价（￥）：</label></td>
            <td class="width-35">
                <form:input path="originalPrice" htmlEscape="false" class="form-control required"/>
            </td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>起始日期：</label></td>
            <td class="width-35">
                <input name="startDate" class=" laydate-icon form-control layer-date required"
                       onclick="laydate({istime: true, format: 'YYYY/MM/DD hh:mm:ss'})"
                       value="<fmt:formatDate value="${operate.startDate}" pattern="yyyy/MM/dd HH:mm:ss"/>">
            </td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>截止日期：</label></td>
            <td class="width-35">
                <input name="endDate" class=" laydate-icon form-control layer-date required"
                       onclick="laydate({istime: true, format: 'YYYY/MM/DD hh:mm:ss'})"
                       value="<fmt:formatDate value="${operate.endDate}" pattern="yyyy/MM/dd HH:mm:ss"/>">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">备注信息：</label></td>
            <td class="width-35">
                <form:textarea path="remarks" htmlEscape="false" rows="4" class="form-control "/>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>