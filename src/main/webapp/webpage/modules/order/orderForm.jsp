<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>订单管理</title>
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
<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">订单号：</label></td>
            <td class="width-35">
                    ${order.orderNumber}
            </td>
            <td class="width-15 active"><label class="pull-right">购买用户：</label></td>
            <td class="width-35">
                    ${order.username}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">获得积分：</label></td>
            <td class="width-35">
                    ${order.totalIntegral}
            </td>
            <td class="width-15 active"><label class="pull-right">创建时间：</label></td>
            <td class="width-35">
                <fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>交易状态：</label></td>
            <td class="width-35">
                <form:select path="tradeState" class="form-control required" >
                    <form:option value="" label=""/>
                    <form:options items="${fns:getTradeStateList()}"
                                  itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>订单状态：</label></td>
            <td class="width-35">
                <form:select path="orderState" class="form-control required">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getOrderStateList()}"
                                  itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>订单总价：</label></td>
            <td class="width-35">
                <form:input path="totalPrice" htmlEscape="false" class="form-control required"/>
            </td>
            <td class="width-15 active"><label class="pull-right">完成时间：</label></td>
            <td class="width-35">
                <input name="changFinishDate" class=" laydate-icon form-control layer-date"
                       onclick="laydate({istime: true, format: 'YYYY/MM/DD hh:mm:ss'})"
                       value="<fmt:formatDate value="${order.finishDate}" pattern="yyyy/MM/dd HH:mm:ss"/>">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>收获地址：</label></td>
            <td class="width-35">
                <form:textarea path="address" htmlEscape="false" rows="4" class="form-control "/>
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