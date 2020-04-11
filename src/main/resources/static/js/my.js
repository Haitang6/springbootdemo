window.onload = function () {
    $("#send").click(
        function () {
            $("#real_title").val($("#title").val());
            $("#real_context").val($("#context").val());
        }
    );
};




