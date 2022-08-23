$(document).ready(function () {
    $('#addbtn').click(function(){
        $('.container').addClass('grey_bg');
        $('#add_goods').removeClass('nodisplay');    //使用 id 选择器获得提示文字，去掉它的隐藏样式
    })
})

$(document).ready(function () {
    $('#add_cancel').click(function(){
        $('.container').removeClass('grey_bg');
        $('#add_goods').addClass('nodisplay');    //使用 id 选择器获得提示文字，去掉它的隐藏样式
    })
})

$(document).ready(function () {
    $('.editbtn').click(function(){
        $('.container').addClass('grey_bg');
        $('#edit_goods').removeClass('nodisplay');    //使用 id 选择器获得提示文字，去掉它的隐藏样式
    })
})

$(document).ready(function () {
    $('#edit_cancel').click(function(){
        // $(".dialog").show(200)
        $('.container').removeClass('grey_bg');
        $('#edit_goods').addClass('nodisplay');    //使用 id 选择器获得提示文字，去掉它的隐藏样式
    })
})

$(document).ready(function () {
    $('#add_confirm').click(function(){
        var name = 'gs_name=' + document.getElementById("goodsname").value;
        var des = 'gs_description=' + document.getElementById("description").value;
        var price = 'gs_price=' + document.getElementById("price").value;
        $('#add_confirm').href = '${pageContext.request.contextPath}/Add?' + name +'&' + des + '&' + price;
    })
})