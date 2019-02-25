<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>管理员管理</title>
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
        });
    </script>
</head>
<body class="hideScroll">
<form:form id="inputForm" modelAttribute="user" action="${ctx}/customer/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="integral"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>登录名：</label></td>
            <td class="width-35">
                <form:input path="username" htmlEscape="false" class="form-control required"/> &nbsp;
            </td>
            <td class="width-15 active"><label class="pull-right">昵称：</label></td>
            <td class="width-35">
                <form:input path="nickname" htmlEscape="false" class="form-control "/>
            </td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>密码：</label></td>
            <td class="width-35">
                <form:input path="password" htmlEscape="false" class="form-control required"/>
            </td>
            <td class="width-15 active"><label class="pull-right">性别：</label></td>
            <td class="width-35">
                <form:select path="sex" class="form-control">
                    <form:options items="${fns:getSex()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">电话：</label></td>
            <td class="width-35">
                <form:input path="phone" htmlEscape="false" class="form-control "/>
            </td>
            <td class="width-15 active"><label class="pull-right">邮箱：</label></td>
            <td class="width-35">
                <form:input path="mail" htmlEscape="false" class="form-control "/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">个性签名：</label></td>
            <td class="width-35">
                <form:textarea path="sign" htmlEscape="false" rows="4" class="form-control "/>
            </td>
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