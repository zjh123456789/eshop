<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import = "com.twinking.eshop.common.constant.Constants"  %>
<html>
<head>
    <title>积分列表</title>
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
            <h5>积分列表 </h5>
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

            <!--查询条件-->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" modelAttribute="integral" action="${ctx}/integral/list/" method="post"
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
                                        class=" form-control input-sm"/>
                        </div>
                    </form:form>
                    <br/>
                </div>
            </div>

            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <table:delRow url="${ctx}/integral/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                        <table:exportExcel url="${ctx}/integral/export"></table:exportExcel><!-- 导出按钮 -->
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
                    <th class="sort-column username">用户名</th>
                    <th class="sort-column changeIntegral">积分变更</th>
                    <th class="sort-column currentIntegral">当前积分</th>
                    <th class="sort-column orderNumber">订单号</th>
                    <th class="sort-column goodsName">商品名</th>
                    <th class="sort-column goodsTypeName">商品类型</th>
                    <th class="sort-column goodsOriginalPrice">原价（￥）</th>
                    <th class="sort-column goodsSellPrice">售价（￥）</th>
                    <th class="sort-column goodsStandardName">商品规格</th>
                    <th class="sort-column goodsByNumber">购买数量</th>
                    <th class="sort-column goodsTotalPrice">总价</th>
                    <th class="sort-column goodsIntroduce">商品介绍</th>
                    <th class="sort-column recordDate">记录时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="integral">
                    <tr>
                        <td><input type="checkbox" id="${integral.id}" class="i-checks"></td>
                        <td>${integral.username}</td>
                        <td>${integral.changeIntegral}</td>
                        <td>${integral.currentIntegral}</td>
                        <td>${integral.orderNumber}</td>
                        <td>${integral.goodsName}</td>
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
                        <td>
                            <button onclick="openDialogView('积分详情', '${ctx}/integral/form?id=${integral.id}','800px', '500px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 查看
                            </button>
                            <a href="${ctx}/integral/delete?id=${integral.id}"
                               onclick="return confirmx('确认要删除该积分吗？', this.href)"
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