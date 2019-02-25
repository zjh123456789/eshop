<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>积分详情</title>
    <%@ include file="/webpage/include/head.jsp" %>
    <meta name="decorator" content="default"/>
</head>
<body class="hideScroll">
<form:form id="inputForm" modelAttribute="integral" action="#" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">用户名：</label></td>
            <td class="width-35">
                    ${integral.username}
            </td>
            <td class="width-15 active"><label class="pull-right">积分变更：</label></td>
            <td class="width-35">
                    ${integral.changeIntegral}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">当前积分：</label></td>
            <td class="width-35">
                    ${integral.currentIntegral}
            </td>
            <td class="width-15 active"><label class="pull-right">订单号：</label></td>
            <td class="width-35">
                    ${integral.orderNumber}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">商品名：</label></td>
            <td class="width-35">
                    ${integral.goodsName}
            </td>
            <td class="width-15 active"><label class="pull-right">商品展示：</label></td>
            <td class="width-35">
                <img src="${integral.goodsPicture}" height="100px" width="100px" class="img-thumbnail">
            </td>
            、
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">商品类型：</label></td>
            <td class="width-35">
                    ${integral.goodsTypeName}
            </td>
            <td class="width-15 active"><label class="pull-right">原价（￥）：</label></td>
            <td class="width-35">
                    ${integral.goodsOriginalPrice}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">售价（￥）：</label></td>
            <td class="width-35">
                    ${integral.goodsSellPrice}
            </td>
            <td class="width-15 active"><label class="pull-right">商品规格：</label></td>
            <td class="width-35">
                    ${integral.goodsStandardName}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">购买数量：</label></td>
            <td class="width-35">
                    ${integral.goodsByNumber}
            </td>
            <td class="width-15 active"><label class="pull-right">总价：</label></td>
            <td class="width-35">
                    ${integral.goodsTotalPrice}
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">记录时间：</label></td>
            <td class="width-35">
                <fmt:formatDate value="${integral.recordDate}"  pattern="yyyy/MM/dd HH:mm:ss"/>
            </td>
            <td class="width-15 active"><label class="pull-right">商品介绍：</label></td>
            <td class="width-35">
                    ${integral.goodsIntroduce}
            </td>
        </tr>
        <tr>

        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>