$(document).ready(function () {
    $.ajax({
       url: 'http://' + window.location.host + '/admin/showPlatformDetail.do',
       type: 'GET',
       dataType: "json",
       success: function (res) {
           document.getElementById('totalTrade').innerHTML = "￥" + res.totalTrade;
           document.getElementById('totalUsers').innerHTML = res.totalUsers;
           document.getElementById('totalBusinesses').innerHTML = res.totalBusinesses;
           document.getElementById('totalGoods').innerHTML = res.totalGoods;
       }
    });
});