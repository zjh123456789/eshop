<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import = "com.twinking.eshop.common.constant.Constants"  %>
<html>
<head>
    <title>商品规格列表</title>
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
            <h5>商品规格列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-goodsStandard">
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
                    <form:form id="searchForm" modelAttribute="goodsStandard" action="${ctx}/goodsStandard/list/" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}"
                                          callback="sortOrRefresh();"/><!-- 支持排序 -->
                        <div class="form-group">
                            <span>商品规格名：</span>
                            <form:input path="name" htmlEscape="false" maxlength="64"
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
                        <table:addRow url="${ctx}/goodsStandard/form" title="商品规格"></table:addRow><!-- 增加按钮 -->
                        <table:editRow url="${ctx}/goodsStandard/form" title="商品规格" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                        <table:delRow url="${ctx}/goodsStandard/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
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
                    <th class="sort-column name">名称</th>
                    <th class="sort-column remarks">描述</th>
                    <th class="sort-column createBy.username">创建者</th>
                    <th class="sort-column createDate">创建日期</th>
                    <th class="sort-column updateDate">最后更新日期</th>
                    <th class="sort-column stateFlag">状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="goodsStandard">
                    <tr>
                        <td><input type="checkbox" id="${goodsStandard.id}" class="i-checks"></td>
                        <td>${goodsStandard.name}</td>
                        <td>${goodsStandard.remarks}</td>
                        <td>${goodsStandard.createBy.username}</td>
                        <td>
                            <fmt:formatDate value="${goodsStandard.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${goodsStandard.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${goodsStandard.stateFlag == Constants.STATE_FLAG_NORMAL}">
                                    <a href="${ctx}/goodsStandard/ban?id=${goodsStandard.id}"
                                       onclick="return confirmx('确认要禁用该商品规格吗？', this.href)"
                                       class="btn btn-primary btn-xs"><i class="fa fa-goodsStandard"></i> 正常</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/goodsStandard/use?id=${goodsStandard.id}"
                                       onclick="return confirmx('确认要启用该商品规格吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-goodsStandard-times"></i> 禁用</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <button onclick="openDialogView('查看商品规格', '${ctx}/goodsStandard/form?id=${goodsStandard.id}','600px', '300px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 查看
                            </button>
                            <c:if test="${goodsStandard.id != Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID}">
                                <button
                                        onclick="openDialog('修改商品规格', '${ctx}/goodsStandard/form?id=${goodsStandard.id}','600px', '300px')"
                                        class="btn btn-success btn-xs"><i class="fa fa-edit"></i> 修改
                                </button>
                                <a href="${ctx}/goodsStandard/delete?id=${goodsStandard.id}"
                                   onclick="return confirmx('确认要删除该商品规格吗？', this.href)"
                                   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
                            </c:if>
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