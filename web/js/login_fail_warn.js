// 用于登录失败提示

$(document).ready(function () {
    if(is_login_succeed == false){  //登录失败
        $('#is_login_succeed').removeClass('nodisplay');    //使用 id 选择器获得提示文字，去掉它的隐藏样式
    }else {     //其他情况
        $('#is_login_succeed').addClass('nodisplay');   //隐藏
    }
})