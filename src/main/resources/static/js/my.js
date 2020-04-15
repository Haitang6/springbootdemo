window.onload = function () {
    //发布页面整合并提交from表单
    $("#send").click(
        function () {
            $("#real_title").val($("#title").val());
            $("#real_context").val($("#context").val());
        }
    );
};




