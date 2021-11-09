$(document).ready(function () {

    const $reviewResult = $('.review-result');

    // 관리자 공지사항 작성 시, 평점은 자동으로 5점 설정
    $(function () {
        let $userid = $("#stars-admin").val();
        console.log($userid);
        if ($userid == "admin") {
            $("#stars-submit").val(5);
        }
    });
        
        // 1~5번째 별 클릭 시, 자동으로 별 채워지게 toggling 
        $(".review-star-child").click(function() {
            $(".review-star-child").find("[data-fa-i2svg]").removeClass("fas").addClass("far");
            $(this).prevAll().find("[data-fa-i2svg]").removeClass("far").addClass("fas");
            $(this).find("[data-fa-i2svg]").removeClass("far").addClass("fas");
        });
    
    // 평점 Ajax 처리
    $reviewResult.click(function () {
        console.log("star!");
        $.ajax({
            type: "get",
            url: appRoot + "/review/write",
            success: function (cnt) {
                        cnt = $('[data-fa-i2svg][data-prefix=fas]').length;
                        $("#reStars").text(cnt);
                        console.log(cnt);
                        $("#stars-submit").val(cnt);
                        console.log($("#stars-submit").val());
            },
            error: function () {
                console.log("error - reviewstar");

            }
        });
    })
});

