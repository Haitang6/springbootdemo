        function comment () {
            var pid=$("#pid").val();
            var context=$("#context").val();
            if (context == null){
                alert("评论内容不能为空");
            }else {
                $.ajax({
                    type:"POST",
                    url:"/comment",
                    contentType:"application/json",
                    data:JSON.stringify({
                        "pid":pid,
                        "context":context,
                        "type":1
                    }),
                    success:function () {
                        window.location.reload();
                    },
                    dataType:"json"
                })
            }
        }
