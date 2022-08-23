<%--
  Created by IntelliJ IDEA.
  User: kiwi
  Date: 2022/5/28
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册登录界面Ⅱ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_regist.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>
<body>
<div class="container">
    <div class="welcome">
        <div class="pinkbox">
            <!-- 注册 -->
            <div class="signup nodisplay">
                <h1>Register</h1>
                <form autocomplete="off">
                    <input type="text" placeholder="Username">
                    <input type="email" placeholder="Email">
                    <input type="password" placeholder="Password">
                    <input type="password" placeholder="Confirm Password">
                    <button class="button submit">Create Account</button>
                </form>
            </div>

            <!-- 登录 -->
            <div class="signin">
                <h1>Sign In</h1>
                <form class="more-padding" autocomplete="off">
                    <input type="text" placeholder="Username">
                    <input type="password" placeholder="Password">
                    <div class="checkbox">
                        <input type="checkbox" id="remember" /><label for="remember">Remember Me</label>
                    </div>
                    <button class="buttom sumbit">Login</button>
                </form>
            </div>
        </div>

        <div class="leftbox">
            <h2 class="title"><span>BLOOM</span>&<br>BOUQUET</h2>
            <p class="desc">Pick your perfect <span>bouquet</span></p>
            <img class="flower smaller" src="https://hbimg.huabanimg.com/c09305167a883e60179a45374df73252304001359acca-W3qbYm_fw658/format/webp" />
            <p class="account">Have an account?</p>
            <button class="button" id="signin">Login</button>
        </div>

        <div class="rightbox">
            <h2 class="title"><span>BLOOM</span>&<br>BOUQUET</h2>
            <p class="desc">Pick your perfect <span>bouquet</span></p>
            <img class="flower" src="https://hbimg.huabanimg.com/b28be92c8198975a74ad656eba00b352c9b9e589819af-lDXUAS_fw658/format/webp" />
            <p class="account">Don't have an account?</p>
            <button class="button" id="signup">Sign Up</button>
        </div>
    </div>
</div>

<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script><script src="${pageContext.request.contextPath}/js/login_regist.js"></script>
</body>
</html>