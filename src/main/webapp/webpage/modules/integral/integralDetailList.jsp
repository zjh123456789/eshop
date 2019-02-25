<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import="com.twinking.eshop.common.constant.Constants" %>
<html>
<head>
    <title>积分详情列表</title>
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
            <h5>积分详情列表 </h5>
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
            <!-- 表格 -->
            <table id="contentTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th><input type="checkbox" class="i-checks"></th>
                    <th class="sort-column username">用户名</th>
                    <th class="sort-column changeIntegral">积分变更</th>
                    <th class="sort-column currentIntegral">当前积分</th>
                    <th class="sort-column orderNumber">订单号</th>
                    <th class="sort-column goodsName">商品名</th>
                    <th class="sort-column goodsPicture">商品展示</th>
                    <th class="sort-column goodsTypeName">商品类型</th>
                    <th class="sort-column goodsOriginalPrice">原价（￥）</th>
                    <th class="sort-column goodsSellPrice">售价（￥）</th>
                    <th class="sort-column goodsStandardName">商品规格</th>
                    <th class="sort-column goodsByNumber">购买数量</th>
                    <th class="sort-column goodsTotalPrice">总价</th>
                    <th class="sort-column goodsIntroduce">商品介绍</th>
                    <th class="sort-column recordDate">记录时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="integral">
                    <tr>
                        <td><input type="checkbox" id="${integral.id}" class="i-checks"></td>
                        <td>${integral.username}</td>
                        <td>${integral.changeIntegral}</td>
                        <td>${integral.currentIntegral}</td>
                        <td>${integral.orderNumber}</td>
                        <td>${integral.goodsName}</td>
                        <td>
                            <img src="${integral.goodsPicture}" height="100px" width="100px" class="img-thumbnail">
                        </td>
                        <td>${integral.goodsTypeName}</td>
                        <td>${integral.goodsOriginalPrice}</td>
                        <td>${integral.goodsSellPrice}</td>
                        <td>${integral.goodsStandardName}</td>
                        <td>${integral.goodsByNumber}</td>
                        <td>${integral.goodsTotalPrice}</td>
                        <td>${integral.goodsIntroduce}</td>
                        <td>
                            <fmt:formatDate value="${integral.recordDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
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