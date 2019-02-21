function submit() {
    console.log("into")
    console.log($('#form').serialize());
    $.ajax({
        type: "POST",                  //提交方式
        dataType: "json",              //预期服务器返回的数据类型
        url: "/adminLogin.do" ,          //目标url
        data: $('#form').serialize(), //提交的数据
        success: function (result) {
            console.log(result);       //打印服务端返回的数据(调试用)
            if (result.resultCode == 200) {
                alert("成功");
            }
            ;
        },
        error : function() {
            alert("异常！");
        }
    });
}