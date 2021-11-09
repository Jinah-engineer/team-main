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
    const $undoOldImage = $("#undo-oldimagebtn"); // 기존 이미지 선택 취소 button
    const $undoNewImage = $("#undo-newimagebtn"); // 새로운 이미지 선택 취소 button

    $("#re-input2").change(function () {
        console.log(this);
        console.log(this.files);
        showUploadedFile(this.files);
    })

    // 이미지 파일 업로드 시 파일명 표시하기
    // $.ajax({
    //     url: appRoot + "/review/write",
    //     processData: false,
    //     contentType: false,
    //     data: FormData,
    //     type: "get",
    //     dataType: "json",
    //     success: function (result) {
    //         console.log(result);
    //         showUploadedFile(result);
    //         $("#uploadDiv").html(cloneObj.html());
    //     },
    //     error: function () {
    //         console.log("이미지 업로드 에러!");
    //     }
    // });
});