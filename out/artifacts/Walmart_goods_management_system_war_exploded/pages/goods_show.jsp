<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kiwi
  Date: 2022/5/28
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>奇异果物品管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addnEdit_goods.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods_show.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/addnEdit_goods.js"></script>
</head>
<body id="body">

<div id="add_goods" class="login dialog nodisplay">
    <div class="login-screen">
        <div class="app-title">
            <h1>goods info</h1>
        </div>

        <form action="${pageContext.request.contextPath}/Add" method="post" enctype="multipart/form-data">
            <div class="login-form">

                <div class="control-group" class="editpicdiv">
                    <input type="file" class="editpicdiv" name="gs_pic" placeholder="pic" id="pic_add">
                    <label class="login-field-icon fui-lock" for="pic_add"></label>
                </div>

                <div class="control-group">
                    <input type="text" class="login-field" name="gs_name" placeholder="name" id="goodsname">
                    <label class="login-field-icon fui-user" for="goodsname"></label>
                </div>

                <div class="control-group">
                    <input type="text" class="login-field" name="gs_description" placeholder="description" id="description">
                    <label class="login-field-icon fui-lock" for="description"></label>
                </div>

                <div class="control-group">
                    <input type="text" class="login-field" name="gs_price" placeholder="price" id="price">
                    <label class="login-field-icon fui-lock" for="price"></label>
                </div>
<%--                <a id="add_confirm" class="btn btn-primary btn-large btn-block" href="">确定</a>--%>
                <button class="btn btn-primary btn-large btn-block">确定添加</button>
                <a id="add_cancel" class="btn btn-info btn-large btn-block">取消添加</a>
            <%--                <a class="login-link">添加后请刷新网页</a>--%>
            </div>
        </form>
    </div>
</div>

<div id="edit_goods" class="login dialog nodisplay">
    <div class="login-screen">
        <div class="app-title">
            <h1>goods info</h1>
        </div>
<%--        <form action="${pageContext.request.contextPath}/Edit" method="post">--%>
        <form action="${pageContext.request.contextPath}/Edit" method="post" enctype="multipart/form-data">
            <div class="login-form">
                <div class="control-group">
                    <span id="edit_tips"></span>
                </div>
                <div class="control-group">
                    <input type="hidden"    <%-- 这个称为表单隐藏域 --%>
                           class="login-field" name="gs_name" placeholder="name" id="goodsname_edit">
                    <label class="login-field-icon fui-user" for="goodsname_edit"></label>
                </div>

                <div class="control-group" class="editpicdiv">
                    <input type="file" class="editpicdiv" name="gs_pic" placeholder="pic" id="pic_edit">
                    <label class="login-field-icon fui-lock" for="pic_edit"></label>
                </div>

                <div class="control-group">
                    <input type="text" class="login-field" name="gs_description" placeholder="description" id="description_edit">
                    <label class="login-field-icon fui-lock" for="description_edit"></label>
                </div>

                <div class="control-group">
                    <input type="text" class="login-field" name="gs_price" placeholder="price" id="price_edit">
                    <label class="login-field-icon fui-lock" for="price_edit"></label>
                </div>
                <%--                <a id="add_confirm" class="btn btn-primary btn-large btn-block" href="">确定</a>--%>
                <button class="btn btn-primary btn-large btn-block">编辑完成</button>
                <a id="edit_cancel" class="btn btn-info btn-large btn-block">取消编辑</a>
                <%--                <a class="login-link">添加后请刷新网页</a>--%>
            </div>
        </form>
    </div>
</div>

<div class="container">
    <div class="well well-sm">
        <strong>Display</strong>
        <div class="btn-group">
            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                class="glyphicon glyphicon-th"></span>Grid</a>
    </div>
        <div class="float-right">
            <button type="button" id="addbtn" class="btn btn-success add_goods">添加</button>
<%--            <a class="btn btn-success btnjs" href="http://www.jquery2dotnet.com">新增</a>--%>
        </div>
    </div>
    <div id="products" class="row grid-group-item list-group-item">

<%--        <B><c:out value="不指定begin和end的迭代：" /></B><br>--%>
        <c:forEach var="list_Goods" items="${list_Goods}">
            <div class="item col-md-12 list-group-item">
                <div class="thumbnail">
<%--                    ${pageContext.request.contextPath} 在本地是很长一串，在服务器是 / --%>
                    <img class="group list-group-image" height="200" width="200" src="${pageContext.request.contextPath}/${list_Goods.gs_pic}" alt="" />
                    <div class="caption">
                        <h4 class="group inner list-group-item-heading">
                            &nbsp;<c:out value="${list_Goods.gs_name}"/><br>
                        </h4>
                        <p class="group inner list-group-item-text">
                            &nbsp;<c:out value="${list_Goods.gs_description}"/><br>
<%--                            ${list_Goods.gs_description} 这样的话就会出现sql注入的风险--%>
                        </p>
                        <div class="row">
                            <div class="col-xs-12 col-md-6">
                                <p class="lead">
                                    &nbsp;￥<c:out value="${list_Goods.gs_price}"/><br>
                                </p>
                            </div>
                            <div class="col-xs-12 col-md-6">
<%--                                <button id="editbtn" class="btn btn-warning">编辑</button>--%>
                                <a class="editbtn btn btn-warning" id="${list_Goods.gs_name}" class="btn btn-danger">编辑</a>
                                <a href="${pageContext.request.contextPath}/Delete?detele_name=${list_Goods.gs_name}" class="btn btn-danger">删除</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/goods_show.js"></script>
<script>
    $('.editbtn').click(
        function (e) {
            // test
            // alert($(e.target).attr('id'));
            // 传值
            var target = $(e.target).attr('id');    // $(e.target)：将DOM对象转换为jQuery对象，以在其上调用jQuery函数
            $('#goodsname_edit').val(target);
            $('#edit_tips').text("您正在编辑 " + target);
        }
    )
</script>
</body>
</html>