window.onload = function () {
    //发布页面整合并提交from表单,发布文章
    $("#send").click(
        function () {
            $("#real_title").val($("#title").val());
            $("#real_context").val($("#context").val());
        }
    );
    $("#save-article").click(
        function () {
            var title = $("#title").val();
            var context = $("#context").val();
            $.ajax({
                type: "POST",
                url: "/unFinishedArticle",
                contentType: "application/json",
                data: JSON.stringify({
                    "title": title,
                    "context": context,
                }),
                success: function (response) {
                    if (response.code === 200) {
                        window.location.href = "/"
                    }
                },
                dataType: "json"
            })

        }
    )
    //点赞
    $(".like").click(
        function () {
            // alert(this.value);
            if ($(".like").is(":checked")) {
                //点击喜欢
                $("#no-fa-thumb").addClass("fa-thumbs-up");
                // var aid=$(".aid").val();
                var aid = this.value;

                $.ajax({
                    type: "POST",
                    url: "/userAndArticleInc",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "aid": aid,
                        "type": 1,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })
            } else {
                //取消喜欢
                $("#yes-fa-thumb").removeClass("fa-thumbs-up");
                $("#yes-fa-thumb").addClass("fa-thumbs-o-up");
                var aid = this.value;
                $.ajax({
                    type: "POST",
                    url: "/userAndArticleDel",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "aid": aid,
                        "type": 1,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })

            }
        }
    )
    //收藏
    $(".collect").click(
        function () {
            if ($(".collect").is(":checked")) {
                $("#no-fa-star").addClass("fa-star");
                $("#no-fa-star").removeClass("fa-star-o");
                // var aid=$(".aid").val();
                var aid = this.value;
                $.ajax({
                    type: "POST",
                    url: "/userAndArticleInc",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "aid": aid,
                        "type": 2,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })
            } else {
                $("#yes-fa-star").removeClass("fa-star");
                $("#yes-fa-star").addClass("fa-star-o");
                var aid = this.value;
                $.ajax({
                    type: "POST",
                    url: "/userAndArticleDel",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "aid": aid,
                        "type": 2,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })
            }
        }
    )
    //关注
    $(".attention").click(
        function () {
            if ($(".attention").is(":checked")) {
                $("#plus").addClass("fa-check");
                $("#plus").removeClass("fa-plus");
                $("#no-attention").val("已关注");
                var upId = $("#upId").val();
                $.ajax({
                    type: "POST",
                    url: "/upAndFanInc",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "upid": upId,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })
            } else {
                $("#check").removeClass("fa-check");
                $("#check").addClass("fa-plus");
                $("#yes-attention").val("关注");
                var upId = $("#upId").val();
                $.ajax({
                    type: "POST",
                    url: "/upAndFanDel",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "upid": upId,
                    }),
                    success: function (response) {
                        if (response.code === 200) {
                            window.location.reload();
                        }
                    },
                    dataType: "json"
                })
            }
        }
    )


};




