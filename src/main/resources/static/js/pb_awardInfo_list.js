$(function () {
    $cashingBTN = $(".cashingBTN");
    $cashingForm = $("#cashingForm");
    $recommender_id = $("#recommender_id");
    $wrong_recommender =$("#wrong_recommender");
    // $info_area = $(".info_area");
    // var body_width = $("body").css("width");
    // var info_area_rate = 0.55
    // //设置奖品信息尺寸
    // $info_area.css("width",body_width*info_area_rate);
    //推荐号提交及验证
    if ($wrong_recommender.length !=0) {
        alert("推荐号错误，请重新输入！");
    }
    $cashingBTN.click(function () {
        var isOk = prompt("请输入推荐号：");
        if (isOk != null) {
            $recommender_id.val(isOk);
            $cashingForm.attr("action",$(this).attr("put_uri")).submit();
        }
        return false;
    });
})