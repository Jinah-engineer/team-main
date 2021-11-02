// 리뷰 수정하기 (Modify)
// 리뷰 삭제하기 (Remove)
$(document).ready(function() {
    const $reModifyUrl = appRoot + "/review/modify";
    const $reRemoveUrl = appRoot + "/review/remove";
    const $maintainUrl = appRoot + "/review/maintain";

    const $reModifyBtn = $("#review-modify-btn");
    const $reRemoveBtn = $("#review-remove-btn");
    const $maintainBtn = $("#re-input-before");

    let clicked = false;

    $maintainBtn.on('click', function () {
        alert("기존의 이미지를 그대로 사용합니다.");
        clicked = true;
    })

    $reModifyBtn.click(function () {  

        console.log(clicked);
        
        if (clicked === true) {
                $("#re-modify-form1").attr("action", $maintainUrl);
                $("#re-modify-form1").submit();

         } else {
                $("#re-modify-form1").submit();
         }
    })
    $reRemoveBtn.click(function () {
            $("#re-modify-form1").attr("action", $reRemoveUrl);
            $("#re-modify-form1").submit();
    })
})