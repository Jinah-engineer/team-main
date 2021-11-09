function showUploadedFile(uploadResultArr) {
    const $uploadResult = $(".uploadResult ul").empty();
    let str = "";

    $(uploadResultArr).each(function (i, obj) {
        str += "<li>" + obj.name + "</li>";
    });
    $uploadResult.append(str);
}

$(function () {

    const $imagePicked = $("#image-picked"); // 이미지 결정 기준
    const $newimageBtn = $("#re-input2"); // 새로운 이미지 button
    const $undoNewImage = $("#undo-newimagebtn"); // 새로운 이미지 선택 취소 button

    $newimageBtn.change(function () {
        //console.log(this);
        //console.log(this.files);
        showUploadedFile(this.files);
    })

    // 이미지 파일 업로드 button 클릭 시 버튼 전환
    $newimageBtn.on('click', function () {
        $newimageBtn.attr("hidden", "hidden");
        $("#re-input2-label").attr("hidden", "hidden");
        $undoNewImage.removeAttr("hidden");
    });

    // 선택 취소 ajax
    $undoNewImage.on('click', function () {
        $.ajax({
            type: "get",
            url: appRoot + "/review/write",
            success: function () {
                console.log("new image value == " + $newimageBtn.val());
                $undoNewImage.attr("hidden", "hidden");
                $newimageBtn.removeAttr("hidden");
                $("#re-input2-label").removeAttr("hidden");

                $newimageBtn.val("");
                showUploadedFile($newimageBtn.files);
                console.log("최종 이미지 값 == " + $newimageBtn.val());

            },
            error: function () {
                console.log("error - 선택 취소가 안됩니다.");

            }
        });
    });
});