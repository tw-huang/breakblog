//网站运行时间
function show_runtime() {
    window.setTimeout("show_runtime()", 1000);
    let X = new Date("9/19/2019 00:00:00");
    let Y = new Date();
    let T = (Y.getTime() - X.getTime());
    let M = 24 * 60 * 60 * 1000;
    let a = T / M;
    let A = Math.floor(a);
    let b = (a - A) * 24;
    let B = Math.floor(b);
    let c = (b - B) * 60;
    let C = Math.floor((b - B) * 60);
    let D = Math.floor((c - C) * 60);
    $('#runtime').html("Runtime : " + A + "天" + B + "时" + C + "分" + D + "秒");
}

//图片自动添加类class="img-fluid"
function add_img_class() {
    $('img').addClass('img-fluid');
}

//格式化文章时间2020-03-05 02:16:08 -> 2020年3月5日 or
function format_timestamp() {
    moment.locale("zh-cn");
    $('.moment').each(function () {
        $(this).text(eval('moment("' + $(this).data('timestamp') + '").' + $(this).data('format') + ';'));
    })
}

//入口
$(function () {
    show_runtime();
    add_img_class();
    format_timestamp();
});
