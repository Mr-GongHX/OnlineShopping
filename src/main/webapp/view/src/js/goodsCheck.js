/**
 * 商品通过审核
 * @param data
 */
function goodsPass(data){
    $.ajax({
        url: 'http://' + window.location.host + '/admin/updateGoodsStatus.do',
        type: 'POST',
        data: {
            goodsId: data,
            action: "pass"
        },
        success: function () {
            window.location.reload();
        }
    });
}

/**
 * 商品审核不通过
 * @param data
 */
function goodsRefuse(data) {
    $.ajax({
        url: 'http://' + window.location.host + '/admin/updateGoodsStatus.do',
        type: 'POST',
        data: {
            goodsId: data,
            action: "refuse"
        },
        success: function () {
            window.location.reload();
        }
    });
}