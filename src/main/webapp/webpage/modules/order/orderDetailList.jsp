<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import="com.twinking.eshop.modules.order.enums.FinishStateEnum" %>
<%@ page import="com.twinking.eshop.modules.order.enums.TradeStateEnum" %>
<html>
<head>
    <title>订单详情列表</title>
    <%@ include file="/webpage/include/head.jsp" %>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>订单详情列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-integral">
                    <li><a href="#">选项1</a>
                    </li>
                    <li><a href="#">选项2</a>
                    </li>
                </ul>
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>

        <div class="ibox-content">
            <sys:message content="${message}"/>
            <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                <tbody>
                <tr>
                    <td class="width-15 active"><label class="pull-right">订单号：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.orderNumber}
                    </td>
                    <td class="width-15 active"><label class="pull-right">购买用户：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.username}
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">获得积分：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.totalIntegral}
                    </td>
                    <td class="width-15 active"><label class="pull-right">创建时间：</label></td>
                    <td class="width-35">
                        <fmt:formatDate value="${orderDetail.order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">交易状态：</label></td>
                    <td class="width-35">
                        ${TradeStateEnum.getValueByKey(orderDetail.order.tradeState)}
                    </td>
                    <td class="width-15 active"><label class="pull-right">订单状态：</label></td>
                    <td class="width-35">
                        ${FinishStateEnum.getValueByKey(orderDetail.order.orderState)}
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">订单总价：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.totalPrice}
                    </td>
                    <td class="width-15 active"><label class="pull-right">完成时间：</label></td>
                    <td class="width-35">
                        <fmt:formatDate value="${orderDetail.order.finishDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">收获地址：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.address}
                    </td>
                    <td class="width-15 active"><label class="pull-right">备注信息：</label></td>
                    <td class="width-35">
                        ${orderDetail.order.remarks}
                    </td>
                </tr>
                </tbody>
            </table>
            <br>
            <!-- 表格 -->
            <table id="contentTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th class="sort-column goodsPicture">商品展示</th>
                    <th class="sort-column goodsName">商品名</th>
                    <th class="sort-column goodsTypeName">商品类型</th>
                    <th class="sort-column goodsIntroduce">商品介绍</th>
                    <th class="sort-column goodsOriginalPrice">原价（￥）</th>
                    <th class="sort-column goodsSellPrice">售价（￥）</th>
                    <th class="sort-column goodsByNumber">购买数量</th>
                    <th class="sort-column goodsStandardName">商品规格</th>
                    <th class="sort-column changeIntegral">获得积分</th>
                    <th class="sort-column goodsTotalPrice">总价</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderDetail.goodsList}" var="integral">
                    <tr>
                        <td>
                            <img src="${integral.goodsPicture}" height="100px" width="100px" class="img-thumbnail">
                        </td>
                        <td>${integral.goodsName}</td>
                        <td>${integral.goodsTypeName}</td>
                        <td>${integral.goodsIntroduce}</td>
                        <td>${integral.goodsOriginalPrice}</td>
                        <td>${integral.goodsSellPrice}</td>
                        <td>${integral.goodsByNumber}</td>
                        <td>${integral.goodsStandardName}</td>
                        <td>${integral.changeIntegral}</td>
                        <td>${integral.goodsTotalPrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <br/>
        </div>
    </div>
</div>
</body>
</html>