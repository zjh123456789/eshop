<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import = "com.twinking.eshop.common.constant.Constants"  %>
<html>
<head>
    <title>待出售商品列表</title>
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
            <h5>待出售商品列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-operate">
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
                    <form:form id="searchForm" modelAttribute="operate" action="${ctx}/operate/list/" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}"
                                          callback="sortOrRefresh();"/><!-- 支持排序 -->
                        <div class="form-group">
                            <span>待出售商品名：</span>
                            <form:input path="goods.name" htmlEscape="false" maxlength="64"
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
                        <table:addRow url="${ctx}/operate/form" title="待出售商品"></table:addRow><!-- 增加按钮 -->
                        <table:editRow url="${ctx}/operate/form" title="待出售商品" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                        <table:delRow url="${ctx}/operate/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
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
                    <th class="sort-column goods.picture">展示</th>
                    <th class="sort-column goods.name">名称</th>
                    <th class="sort-column goods.type.name">类型</th>
                    <th class="sort-column goods.goodsStandard.name">规格</th>
                    <th class="sort-column sellPrice">售价（￥）</th>
                    <th class="sort-column originalPrice">原价（￥）</th>
                    <th class="sort-column integral">积分</th>
                    <th class="sort-column goods.introduce">描述</th>
                    <th class="sort-column startDate">开始日期</th>
                    <th class="sort-column endDate">截止日期</th>
                    <th class="sort-column sellState">销售状态</th>
                    <th class="sort-column createBy.username">创建者</th>
                    <th class="sort-column createDate">创建日期</th>
                    <th class="sort-column stateFlag">状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="operate">
                    <tr>
                        <td><input type="checkbox" id="${operate.id}" class="i-checks"></td>
                        <td>
                            <img src="${operate.goods.picture}" height="100px" width="100px" class="img-thumbnail">
                        </td>
                        <td>${operate.goods.name}</td>
                        <td>${operate.goods.type.name}</td>
                        <td>${operate.goods.goodsStandard.name}</td>
                        <td>${operate.sellPrice}</td>
                        <td>${operate.originalPrice}</td>
                        <td>${operate.integral}</td>
                        <td>${operate.goods.introduce}</td>
                        <td>
                            <fmt:formatDate value="${operate.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${operate.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${operate.sellState == Constants.OPERATE_GOODS_SELL_STATE_NORMAL}">
                                    <span class="label label-success"> 销售中 </span>
                                </c:when>
                                <c:when test="${operate.sellState == Constants.OPERATE_GOODS_SELL_STATE_BAND}">
                                    <span class="label label-danger"> 已禁用 </span>
                                </c:when>
                                <c:when test="${operate.sellState == Constants.OPERATE_GOODS_SELL_STATE_BE_OVERDUE}">
                                    <span class="label label-default"> 已过期 </span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${operate.createBy.username}</td>
                        <td>
                            <fmt:formatDate value="${operate.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${operate.stateFlag == Constants.STATE_FLAG_NORMAL}">
                                    <a href="${ctx}/operate/ban?id=${operate.id}"
                                       onclick="return confirmx('确认要禁用该待出售商品吗？', this.href)"
                                       class="btn btn-primary btn-xs"><i class="fa fa-operate"></i> 正常</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/operate/use?id=${operate.id}"
                                       onclick="return confirmx('确认要启用该待出售商品吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-operate-times"></i> 禁用</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <button onclick="openDialogView('查看待出售商品', '${ctx}/operate/form?id=${operate.id}','800px', '500px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 查看
                            </button>
                            <button
                                    onclick="openDialog('修改待出售商品', '${ctx}/operate/form?id=${operate.id}','800px', '500px')"
                                    class="btn btn-success btn-xs"><i class="fa fa-edit"></i> 修改
                            </button>
                            <a href="${ctx}/operate/delete?id=${operate.id}"
                               onclick="return confirmx('确认要删除该待出售商品吗？', this.href)"
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