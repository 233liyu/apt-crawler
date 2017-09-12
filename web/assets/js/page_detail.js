function get_detail() {
    message('info','正在加载');
    var id = getURLParameter('data_id');
    if (id == null){
        id = 0;
    }
    var json = {
        data_id : id
    };

    $.ajax({
        url : '/apt/get/data',
        type: "POST",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data: JSON.stringify(json),
        success : function (data) {
            console.log(data);
            if(data.state){
                // log in success
                message('success','加载成功');
                var result = data.element;
                console.log(result);
                render_page(result);
            } else {
                if (data.errMsg.toString() == 'error request'){
                    message('error','您还未登录');
                } else {
                    message('error','不存在该报告');
                }

            }
        },
        error : function () {
            console.log('error network');
            message('error','通信错误！')
        }
    })
}

    /*
        "title": "Positive Technologies发布APT组织Cobalt的分析报告",
        "author": "匿名用户",
        "content": "\n          Cobalt多针对银行、保险、金融交易所等金融机构发起攻击，在攻击中使用了供应链攻击(Supply Chain Attacks)等手段。\n        ",
        "data_id": "1672",
        "sites": "https://x.threatbook.cn",
        "url": "https://x.threatbook.cn/article?threatInfoID=57",
        "c_time": "2017-09-11 12:00:00",
        "p_time": "2017-09-11 12:00:00",
    */

function render_page(json) {
    var tag_array = json.tags;

    $("#data_title").html(json.title);
    $('#subtitle').html('');
    $('#subtitle').append(
        "<p>作者: "+json.author + ";</p>" +
        "<p>链接：<a href='"+json.url+"'>" + json.url + "</a>;</p>" +
        "<p>发布时间：" +json.p_time+ ";</p>" +
        "<p>爬取时间：" + json.c_time + "</p>"
    );

    for (var i = 0; i < tag_array.length; i++){
        var tag = tag_array[i];
        $('.panel-heading').append(
            "<span data-tagID=\""+ tag.tag_id+"\" class=\"label label-info ly_tag\">"+tag.tag_name+"</span>\n"
        )
    }

    $(".panel-body").html('');
    $(".panel-body").append(
        "<p>"+json.content+"</p>"
    );

    var like_btn = $('#like_bottom');
    like_btn.data('id', json.data_id);
    if(json.like){
        like_btn.attr('style','color:red;');
    } else {
        like_btn.attr('style','');
    }



}

function getURLParameter(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
}

$(".ly_btn").click(
    function (click) {
        var btn = click.target();
        if (btn.data('context') == 'like'){

        }
        console.log(click);
        message('info','click');
    }
);