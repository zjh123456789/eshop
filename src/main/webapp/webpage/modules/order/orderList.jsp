<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import = "com.twinking.eshop.common.constant.Constants"  %>
<%@ page import = "com.twinking.eshop.modules.order.enums.TradeStateEnum"  %>
<%@ page import = "com.twinking.eshop.modules.order.enums.FinishStateEnum"  %>
<html>
<head>
    <title>订单列表</title>
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
            <h5>订单列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-order">
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

            <!--查询条件-->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" modelAttribute="order" action="${ctx}/order/list/" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}"
                                          callback="sortOrRefresh();"/><!-- 支持排序 -->
                        <div class="form-group">
                            <span>用户名：</span>
                            <form:input path="username" htmlEscape="false" maxlength="64"
                                        class=" form-control input-sm"/> &nbsp;
                            <span>订单号：</span>
                            <form:input path="orderNumber" htmlEscape="false" maxlength="64"
                                        class=" form-control input-sm"/> &nbsp;
                            <span>交易状态：</span>
                            <form:select path="tradeState" class="form-control" >
                                <form:option value="" label=""/>
                                <form:options items="${fns:getTradeStateList()}"
                                              itemLabel="label" itemValue="value" htmlEscape="false" />
                            </form:select> &nbsp;
                            <span>订单状态：</span>
                            <form:select path="orderState" class="form-control">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getOrderStateList()}"
                                              itemLabel="label" itemValue="value" htmlEscape="false"/>
                            </form:select>
                        </div>
                    </form:form>
                    <br/>
                </div>
            </div>

            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <table:delRow url="${ctx}/order/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                        <table:editRow url="${ctx}/order/edit" title="修改订单" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                        <table:exportExcel url="${ctx}/order/export"></table:exportExcel><!-- 导出按钮 -->
                        <button id="refresh_btn" class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left"
                                onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                        </button>

                    </div>
                    <div class="pull-right">
                        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                                class="fa fa-search"></i> 查询
                        </button>
                        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                                class="fa fa-refresh"></i> 重置
                        </button>
                    </div>
                </div>
            </div>

            <!-- 表格 -->
            <table id="contentTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th><input type="checkbox" class="i-checks"></th>
                    <th class="sort-column orderNumber">订单号</th>
                    <th class="sort-column username">用户名</th>
                    <th class="sort-column totalPrice">总价（￥）</th>
                    <th class="sort-column totalIntegral">积分</th>
                    <th class="sort-column address">收货地址</th>
                    <th class="sort-column remarks">备注</th>
                    <th class="sort-column tradeState">交易状态</th>
                    <th class="sort-column orderState">订单状态</th>
                    <th class="sort-column createDate">创建时间</th>
                    <th class="sort-column finishDate">完成时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="order">
                    <tr>
                        <td><input type="checkbox" id="${order.id}" class="i-checks"></td>
                        <td>${order.orderNumber}</td>
                        <td>${order.username}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.totalIntegral}</td>
                        <td>${order.address}</td>
                        <td>${order.remarks}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.tradeState == Constants.ORDER_UN_PAY_STATE}">
                                    <span class="label label-default">
                                            ${TradeStateEnum.getValueByKey(Constants.ORDER_UN_PAY_STATE)} </span>
                                </c:when>

                                <c:when test="${order.tradeState == Constants.ORDER_ALREADY_PAY_STATE}">
                                    <span class="label label-primary">
                                            ${TradeStateEnum.getValueByKey(Constants.ORDER_ALREADY_PAY_STATE)} </span>
                                </c:when>

                                <c:when test="${order.tradeState == Constants.ORDER_REFUNING_STATE}">
                                    <span class="label label-warning">
                                            ${TradeStateEnum.getValueByKey(Constants.ORDER_REFUNING_STATE)} </span> &nbsp;
                                    <a href="${ctx}/order/refund?id=${order.id}&listType=${order.listType}"
                                       onclick="return confirmx('确认要同意该订单退款请求吗？', this.href)"
                                       class="btn btn-success btn-xs"><i class="fa fa-check"></i> 同意</a> &nbsp;
                                    <a href="${ctx}/order/refuseRefund?id=${order.id}&listType=${order.listType}"
                                       onclick="return confirmx('确认要拒绝该订单退款请求吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 拒绝</a>
                                </c:when>

                                <c:when test="${order.tradeState == Constants.ORDER_ALREADY_REFUND_STATE}">
                                    <span class="label label-default">
                                            ${TradeStateEnum.getValueByKey(Constants.ORDER_ALREADY_REFUND_STATE)} </span>
                                </c:when>

                                <c:when test="${order.tradeState == Constants.ORDER_UNABLE_REFUND_STATE}">
                                    <span class="label label-default">
                                            ${TradeStateEnum.getValueByKey(Constants.ORDER_UNABLE_REFUND_STATE)} </span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${order.orderState == Constants.ORDER_UN_FINISH_STATE}">
                                    <span class="label label-info">
                                            ${FinishStateEnum.getValueByKey(Constants.ORDER_UN_FINISH_STATE)} </span> &nbsp;
                                   <a href="${ctx}/order/cancelOrder?id=${order.id}&listType=${order.listType}"
                                       onclick="return confirmx('确认要取消该订单吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 取消订单</a>
                                </c:when>

                                <c:when test="${order.orderState == Constants.ORDER_ALREADY_FINISH_STATE}">
                                    <span class="label label-primary">
                                            ${FinishStateEnum.getValueByKey(Constants.ORDER_ALREADY_FINISH_STATE)} </span>
                                </c:when>

                                <c:when test="${order.orderState == Constants.ORDER_CANCELING_STATE}">
                                    <span class="label label-warning"> 取消中 </span> &nbsp;
                                    <a href="${ctx}/order/cancelOrder?id=${order.id}&listType=${order.listType}"
                                       onclick="return confirmx('确认要同意该订单取消请求吗？', this.href)"
                                       class="btn btn-success btn-xs"><i class="fa fa-check"></i> 同意</a> &nbsp;
                                    <a href="${ctx}/order/refuseCancelOrder?id=${order.id}&listType=${order.listType}"
                                       onclick="return confirmx('确认要拒绝该订单取消请求吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 拒绝</a>
                                </c:when>

                                <c:when test="${order.orderState == Constants.ORDER_CANCEL_STATE}">
                                    <span class="label label-default">
                                            ${FinishStateEnum.getValueByKey(Constants.ORDER_CANCEL_STATE)} </span>
                                </c:when>

                                <c:when test="${order.orderState == Constants.ORDER_UNABLE_CANCEL_STATE}">
                                    <span class="label label-default">
                                            ${FinishStateEnum.getValueByKey(Constants.ORDER_UNABLE_CANCEL_STATE)} </span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${order.finishDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <button onclick="openDialogViewFillScreen('订单详情', '${ctx}/order/detail?orderId=${order.id}','1000px', '500px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 详情
                            </button>
                            <button
                                    onclick="openDialog('修改订单', '${ctx}/order/edit?id=${order.id}','800px', '500px')"
                                    class="btn btn-success btn-xs"><i class="fa fa-edit"></i> 修改
                            </button>
                            <a href="${ctx}/order/delete?id=${order.id}&listType=${order.listType}"
                               onclick="return confirmx('确认要删除该订单吗？', this.href)"
                               class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 分页代码 -->
            <table:page page="${page}"></table:page>
            <br/>
            <br/>
        </div>
    </div>
</div>
</body>
</html>