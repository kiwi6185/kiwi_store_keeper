/**
 * 注册表单验证 js
 */
$(document).ready(function () {
    // 提交按钮
    var reg_submit_button = $('#reg_submit_button');
    // 用户名检测
    $('#reg_username').blur(function () {
        var v_username = $('#v_username');  // 获取用户名验证提示标签
        if($(this).val().length < 6 || $(this).val().length > 18){
            v_username.removeClass('nodisplay');
            v_username.html("<em>用户名由6-18个字符组成</em>");
            reg_submit_button.attr("disabled", "disable");
            reg_submit_button.addClass('v_button_disabled');
            $(this).focus();
        }
        else{
            reg_submit_button.removeAttr("disabled");
            reg_submit_button.removeClass('v_button_disabled');
            v_username.addClass('nodisplay');
        }
    });
    // 密码检测
    $('#reg_password').blur(function () {
        //正则表达式，密码由字母、数字和下划线组成。
        var regs =  /^[a-zA-Z0-9_]+$/;
        var v_password = $('#v_password');  // 获取密码验证提示标签
        if($(this).val().length < 6 || $(this).val().length > 18 ||
           !regs.test($(this).val())){
            v_password.removeClass('nodisplay');
            v_password.html("<em>密码由6-18个字符、数字和下划线组成</em>");
            reg_submit_button.attr("disabled", "disable");
            reg_submit_button.addClass('v_button_disabled');
            $(this).focus();
        }
        else{
            reg_submit_button.removeAttr("disabled");
            reg_submit_button.removeClass('v_button_disabled');
            v_password.addClass('nodisplay');
        }
    });
    // 密码验证检测
    $('#confirm_password').blur(function () {
        var v_confirm_username = $('#v_confirm_username');
        if($(this).val() !== $('#reg_password').val()){
            v_confirm_username.removeClass('nodisplay');
            v_confirm_username.html("<em>两次输入的密码不一致</em>");
            reg_submit_button.attr("disabled", "disable");
            reg_submit_button.addClass('v_button_disabled');
        }
        else {
            reg_submit_button.removeAttr("disabled");
            reg_submit_button.removeClass('v_button_disabled');
            v_confirm_username.addClass('nodisplay');
        }
    });
    $('#email').blur(function () {
        var v_email = $('#v_email');
        //获取Email输入域的值
        var email = $('#email').val();
        //电子邮件的正则表达式
        var pattern = /^[a-zA-Z0-9#_\^\$\.\*\+\-\?\=\!\:\|\\\/\(\)\[\]\{\}]+@[a-zA-Z0-9]+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(email.length === 0){
            v_email.removeClass('nodisplay');
            v_email.html("<em>电子邮箱不能为空</em>");
            reg_submit_button.attr("disabled", "disable");
            reg_submit_button.addClass('v_button_disabled');
            $(this).focus();
        }
        else if(!pattern.test(email)){
            v_email.removeClass('nodisplay');
            v_email.html("<em>电子邮箱地址不合法</em>");
            reg_submit_button.attr("disabled", "disable");
            reg_submit_button.addClass('v_button_disabled');
            $(this).focus();
        }
        else{
            reg_submit_button.removeAttr("disabled");
            reg_submit_button.removeClass('v_button_disabled');
            v_email.addClass('nodisplay');
        }
    });
});
