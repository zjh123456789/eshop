<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ page import = "com.twinking.eshop.common.constant.Constants"  %>
<html>
<head>
    <title>会员列表</title>
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
            <h5>会员列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
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
                    <form:form id="searchForm" modelAttribute="user" action="${ctx}/customer/list/" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}"
                                          callback="sortOrRefresh();"/><!-- 支持排序 -->
                        <div class="form-group">
                            <span>用户名：</span>
                            <form:input path="username" htmlEscape="false" maxlength="64"
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
                        <table:addRow url="${ctx}/customer/form" title="会员"></table:addRow><!-- 增加按钮 -->
                        <table:editRow url="${ctx}/customer/form" title="会员" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                        <table:delRow url="${ctx}/customer/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                        <table:exportExcel url="${ctx}/customer/export"></table:exportExcel><!-- 导出按钮 -->
                        <button id="refresh_btn" class="btn btn-white btn-sm " data-toggle="tooltip"
                                data-placement="left"
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
                    <th class="sort-column username">登录名</th>
                    <th class="sort-column nickname">昵称</th>
                    <th class="sort-column sex">性别</th>
                    <th class="sort-column phone">手机</th>
                    <th class="sort-column mail">邮箱</th>
                    <th class="sort-column createDate">注册时间</th>
                    <th class="sort-column integral">积分</th>
                    <th class="sort-column stateFlag">状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="user">
                    <tr>
                        <td><input type="checkbox" id="${user.id}" class="i-checks"></td>
                        <td>${user.username}</td>
                        <td>${user.nickname}</td>
                        <td>${user.sex}</td>
                        <td>${user.phone}</td>
                        <td>${user.mail}</td>
                        <td>
                            <fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <button onclick="openDialogViewFillScreen('查看积分详情', '${ctx}/integral/detailIntegralList?userId=${user.id}','1200px', '500px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 积分详情
                            </button> &nbsp;
                            <span class="label label-warning"> ${user.integral}</span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.stateFlag == Constants.STATE_FLAG_NORMAL}">
                                    <a href="${ctx}/customer/ban?id=${user.id}"
                                       onclick="return confirmx('确认要禁用该用户吗？', this.href)"
                                       class="btn btn-primary btn-xs"><i class="fa fa-user"></i> 正常</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/customer/use?id=${user.id}"
                                       onclick="return confirmx('确认要启用该用户吗？', this.href)"
                                       class="btn btn-danger btn-xs"><i class="fa fa-user-times"></i> 禁用</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <button onclick="openDialogView('查看用户', '${ctx}/customer/form?id=${user.id}','800px', '500px')"
                                    class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i> 会员信息
                            </button>
                            <button
                                    onclick="openDialog('修改用户', '${ctx}/customer/form?id=${user.id}','800px', '500px')"
                                    class="btn btn-success btn-xs"><i class="fa fa-edit"></i> 修改
                            </button>
                            <a href="${ctx}/customer/delete?id=${user.id}"
                               onclick="return confirmx('确认要删除该用户吗？', this.href)"
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