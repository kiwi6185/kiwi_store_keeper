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
    <title>奇异果登录注册页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_regist.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_fail_warn.css">
</head>
<body>
<div class="container">
    <div class="welcome">
        <div class="pinkbox">
            <!-- 注册 -->
            <div class="signup nodisplay">
                <h1>Register</h1>
                <form autocomplete="off" action="${pageContext.request.contextPath}/Register" method="post">
                    <input type="text" name="username" placeholder="Username">
                    <input type="email" name="email" placeholder="Email">
                    <input type="password" name="password" placeholder="Password">
                    <input type="password" name="confirm_password" placeholder="Confirm Password">
                    <button class="button submit">Create Account</button>
                </form>
            </div>

            <!-- 登录 -->
            <div class="signin">
                <h1>Sign In</h1>
                <form class="more-padding" autocomplete="off" action="${pageContext.request.contextPath}/Login" method="post">
                    <input type="text" name="username" placeholder="Username">
                    <input type="password" name="password" placeholder="Password">
                    <div class="checkbox">
                        <input type="checkbox" id="remember" /><label for="remember">Remember Me</label>
                    </div>
                    <span id="is_login_succeed" class="verify nodisplay" >
                        密码或用户名错误，请重新输入
                    </span>
                    <button class="buttom sumbit">Login</button>
                </form>
            </div>
        </div>

        <div class="leftbox">
            <h2 class="title"><span>BLOOM</span>&<br>BOUQUET</h2>
            <p class="desc">Pick your perfect <span>bouquet</span></p>
            <img class="flower smaller" src="pics/kiwi_cute_nake.png" />
            <p class="account">Have an account?</p>
            <button class="button" id="signin">Login</button>
        </div>

        <div class="rightbox">
            <h2 class="title"><span>BLOOM</span>&<br>BOUQUET</h2>
            <p class="desc">Pick your perfect <span>bouquet</span></p>
            <img class="flower" src="pics/kiwi.png" />
            <p class="account">Don't have an account?</p>
            <button class="button" id="signup">Sign Up</button>
        </div>
    </div>
</div>

<!-- partial -->
<%--<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>--%>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
<script src="${pageContext.request.contextPath}/js/login_regist.js"></script>
<script>
    // request 对象是 tomcat 的东西，在 js 中是拿不到的，为什么在这里能拿到呢？
    // 因为 jsp 页面最后也会被编译成 servlet
    // servlet 中才可以拿到 request 对象
    // 获取 request 中的参数
    var is_login_succeed = <%=request.getAttribute("is_login_succeed")%>
</script>
<%--    我们可以在 js 中使用 is_login_succeed 变量--%>
<script src="js/login_fail_warn.js" type="text/javascript"></script>
</body>
</html>