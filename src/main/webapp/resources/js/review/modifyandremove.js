// 리뷰 수정하기 (Modify)
// 리뷰 삭제하기 (Remove)
$(document).ready(function () {
    const $reModifyUrl = appRoot + "/review/modify";
    const $reRemoveUrl = appRoot + "/review/remove";
    const $maintainUrl = appRoot + "/review/maintain";

    const $reModifyBtn = $("#review-modify-btn");
    const $reRemoveBtn = $("#review-remove-btn");
    const $maintainBtn = $("#re-input-before");
    const $newimageBtn = $("#newimagebtn");
    const $imagePicked = $("#image-picked");

    let clicked = false;

    $newimageBtn.click(function (e) {
        e.preventDefault();
        $imagePicked.val("new");
        $('#re-input2').click();
    });

    $maintainBtn.on('click', function () {
        alert("기존의 이미지를 그대로 사용합니다.");
        $imagePicked.val("maintain");
        console.log($imagePicked.val());
    })

    $(function () {

        $reModifyBtn.click(function (e) {
            if ($imagePicked.val() === "maintain") {
                console.log("image value == " + $imagePicked.val());
                $("#re-modify-form1").attr("action", $maintainUrl);
                $("#re-modify-form1").submit();
            } else if ($imagePicked.val() === "new") {
                console.log("image value == " + $imagePicked.val());
                $("#re-modify-form1").submit();
            } else {
                e.preventDefault();
                alert("이미지 업로드 방식을 선택해주세요.");
                e.stopPropagation();
                $('#staticBackdrop1').modal('hide');
            }
        })
        $reRemoveBtn.click(function () {
            $("#re-modify-form1").attr("action", $reRemoveUrl);
            $("#re-modify-form1").submit();
        })
    })
})