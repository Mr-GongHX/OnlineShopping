$(document).ready(function () {
    //AJAX请求
    // $.ajax({
    //     url: 'http://' + window.location.host + '/admin/commentCheck.do',
    //     type: 'GET',
    //     dataType: "json",
    //     success: function (res) {
    //         // document.getElementById('totalTrade').innerHTML = "￥" + res.totalTrade;
    //         // document.getElementById('totalUsers').innerHTML = res.totalUsers;
    //         // document.getElementById('totalBusinesses').innerHTML = res.totalBusinesses;
    //         // document.getElementById('totalGoods').innerHTML = res.totalGoods;
    //         console.log(res)
    //     }
    // });
});

/**
 * 评论通过审核
 * @param data
 */
function commentPass(data){
    $.ajax({
        url: 'http://' + window.location.host + '/admin/updateCommentStatus.do',
        type: 'POST',
        data: {
            commentId: data,
            action: "pass"
        },
        success: function (res) {
            window.location.reload();
        }
    });
}

/**
 * 评论审核不通过
 * @param data
 */
function commentRefuse(data) {
    $.ajax({
        url: 'http://' + window.location.host + '/admin/updateCommentStatus.do',
        type: 'POST',
        data: {
            commentId: data,
            action: "refuse"
        },
        success: function (res) {
            window.location.reload();
        }
    });
}