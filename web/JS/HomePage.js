
function function1() {
    var searchKey = "APT";
    searchFunction(searchKey)
}

function function2() {
    var searchKey = "漏洞";
    searchFunction(searchKey)
}

function function3() {
    var searchKey = "攻击";
    searchFunction(searchKey)
}

function function4() {
    var searchKey = "黑客";
    searchFunction(searchKey)
}

function function5() {
    var searchKey = "风险";
    searchFunction(searchKey)
}

function function6() {
    var searchKey = "威胁";
    searchFunction(searchKey)
}

function function7() {
    var searchKey = "安全";
    searchFunction(searchKey)
}

function function8() {
    var searchKey = "其他";
    searchFunction(searchKey)
}

//分类信息查询--------------------------------------------------------
function searchFunction(searchInfo){
    var data = {
        "demand": searchInfo
    };
    var json = JSON.stringify(data);
    $.ajax({
        url: "/apt/homepage/search_info",
        type: "post",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data: json,
        success: function (daa) {
            $("tr").remove(".formData");
            console.log("数据为", daa);
            var arr = daa.array;
            console.log(arr);
            for (var i = 0; i < arr.length; i++) {
                $("#Table").append("<tr id=\"FormData\" class=\"formData\">\n" +
                    "                            <td style=\"width: 20%\">+arr[i].Title+</td>\n" +
                    "                            <td style=\"width: 10%\">+arr[i].Tag+</td>\n" +
                    "                            <td style=\"width: 5%\">\n" +
                    "                                <a id=\"+arr[i].Title+\" onclick=\"formDataView(this)\">查看</a>\n" +
                    "                            </td>\n" +
                    "                            <td style=\"width: 5%\">\n" +
                    "                                <a id=\"+arr[i].Title+\" onclick=\"formDataCollection(this)\">收藏</a>\n" +
                    "                            </td>\n" +
                    "                        </tr>"
                )
            }
            $("#NowTitle").text(arr[0].Title);
            $("#NowAuthor").text("作者："+arr[0].Author);
            $("#NowPublishTime").text("发布时间："+arr[0].PulishTime);
            $("#NowTag").text("标签："+arr[0].Tag);
            $("#NowUrl").text("网址："+arr[0].Url);
            $("#NowContent").text(arr[0].Content)
        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//点击查看后显示信息-------------------------------------------------
function formDataView(obj){
    var searchInfo=obj.id;
    alert(searchInfo);
    var data={"dataId":searchInfo};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/apt//data/information",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data : json,
        success: function (daa) {
            $("tr").remove(".formData");
            console.log("数据为",daa);
            var arr = daa.array;
            console.log(arr);
            $("#NowTitle").text(arr[0].dataTitle);
            $("#NowAuthor").text("作者："+arr[0].dataAuthor);
            $("#NowPublishTime").text("发布时间："+arr[0].dataPulishdate);
            $("#NowTag").text("标签："+arr[0].dataTag);
            $("#NowUrl").text("网址："+arr[0].dataUrl);
            $("#NowContent").text(arr[0].dataContent);
            alert("查看成功");
        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//收藏信息查询-----------------------------------------------------
function collection(){
    var data={"demand":"searchCollection"};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/apt/homepage/usercollection",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data : json,
        success: function (daa) {
            $("tr").remove(".formData");
            console.log("数据为",daa);
            var arr = daa.array;
            console.log(arr);
            for (var i = 0; i < arr.length; i++) {
                $("#Table").append("<tr id=\"FormData\" class=\"formData\">\n" +
                    "                            <td style=\"width: 20%\">+arr[i].Title+</td>\n" +
                    "                            <td style=\"width: 10%\">+arr[i].Tag+</td>\n" +
                    "                            <td style=\"width: 5%\">\n" +
                    "                                <a id=\"+arr[i].Title+\" onclick=\"formDataView(this)\">查看</a>\n" +
                    "                            </td>\n" +
                    "                            <td style=\"width: 5%\">\n" +
                    "                                <a id=\"+arr[i].Title+\" onclick=\"collectionCancel(this)\">取消收藏</a>\n" +
                    "                            </td>\n" +
                    "                        </tr>"
                )
            }
            $("#NowTitle").text(arr[0].TiTle);
            $("#NowAuthor").text("作者："+arr[0].Author);
            $("#NowPublishTime").text("发布时间："+arr[0].PulishTime);
            $("#NowTag").text("标签："+arr[0].Tag);
            $("#NowUrl").text("网址："+arr[0].Url);
            $("#NowContent").text(arr[0].Content)
        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//取消收藏----------------------------------------------------------
function collectionCancel(data){
    var id=data.id;
    var json=JSON.stringify({
        "LikeChange":"DeleteLike",
        "DataTitle":id
    });
    $.ajax({
        type:"post",
        url:"/apt/like/change",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data : json,
        success:function(daa){
            var signal=daa.signal;
            if(signal.equal("Delete Like Success")){
                alert("取消成功!");
            }
            else if(signal.equal("Delete Like Fail")){
                alert("取消失败!");
            }
        },
        error:function(){
            alert("出错啦！！");
        }
    })
}
//点击收藏后收藏信息----------------------------------------------------
function formDataCollection(obj){
    var id=obj.id;
    var data={
        "LikeChange":"Add Like",
        "DataTitle":id
    };
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/apt/like/change",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data : json,
        success: function (daa) {
            var signal=daa.signal;
            if(signal.equal("collect success")) {
                alert("收藏成功！") ;
            }
            else if(signal.equal("collected already!!")){
                alert("收藏过，无法重复收藏")
            }
        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//登陆后加载用户信息----------------------------------------------------
$(document).ready(function(){
    var data={
        "demand":"UserInfo"
    };
    var json=JSON.stringify(data);
    $.ajax({
        url:"/apt/user/Information",
        data:json,
        dataType:"json",
        contentType:'application/json; charset=utf-8', // 很重要
        type:"post",
        success:function(daa){
            if(daa.signal=="User Informartion Success"){
                $("#UserName").text(daa.Username);
                $("#Username1").text(daa.Username);
                $("#eamilInfo").text(daa.Useremail);
            }else if(daa.signal=="User Informartion Fail"){
                alert("用户信息加载失败")
            }
        },
        error:function(){
            alert("用户信息后台未发包");
        }
    })
});
//登陆后搜所有信息--------------------------------------------------------
$(document).ready(function(){
    var data={
        "demand":"DisplayAllInfo"
    };
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/apt/homepage/data",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data : json,
        success: function (daa) {
            var signal=daa.signal;
            if(signal=="Output Success"){
                $("tr").remove(".formData");
                var arr = daa.jsonarray;
                console.log(arr);
                for (var i = 0; i < arr.length; i++) {
                    $("#Table").append("<tr id=\"FormData\" class=\"formData\">\n" +
                        "                            <td style=\"width: 20%\">"+arr[i].Title+"</td>\n" +
                        "                            <td style=\"width: 10%\">"+arr[i].Tag+"</td>\n" +
                        "                            <td style=\"width: 5%\">\n" +
                        "                                <a id=\""+arr[i].ID+"\" onclick=\"formDataView(this)\">查看</a>\n" +
                        "                            </td>\n" +
                        "                            <td style=\"width: 5%\">\n" +
                        "                                <a id=\""+arr[i].ID+"\" onclick=\"formDataCollection(this)\">收藏</a>\n" +
                        "                            </td>\n" +
                        "                        </tr>"
                    )
                }
                $("#NowTitle").text(arr[0].Title);
                $("#NowAuthor").text("作者："+arr[0].Author);
                $("#NowPublishTime").text("发布时间："+arr[0].PulishTime);
                $("#NowTag").text("标签："+arr[0].Tag);
                $("#NowUrl").text("网址："+arr[0].Url);
                $("#NowContent").text(arr[0].Content)
            }else if(signal=="Output Fail"){
            }
        },
        error: function (daa) {
            alert("数据信息后台未发包");
        }
    })
});
//按输入信息查询------------------------------------------------------------
$("#Search").click(
    function(){
        var searchInfo=("#SearchInfo").val();
        var data = {
            "demand": searchInfo
        };
        var json = JSON.stringify(data);
        $.ajax({
            url: "/apt/homepage/search_info",
            type: "post",
            contentType: 'application/json; charset=utf-8', // 很重要
            dataType: "json",
            data: json,
            success: function (daa) {
                $("tr").remove(".formData");
                console.log("数据为", daa);
                var arr = daa.array;
                console.log(arr);
                for (var i = 0; i < arr.length; i++) {
                    $("#Table").append("<tr id=\"FormData\" class=\"formData\">\n" +
                        "                            <td style=\"width: 20%\">+arr[i].Title+</td>\n" +
                        "                            <td style=\"width: 10%\">+arr[i].Tag+</td>\n" +
                        "                            <td style=\"width: 5%\">\n" +
                        "                                <a id=\"+arr[i].Title+\" onclick=\"formDataView(this)\">查看</a>\n" +
                        "                            </td>\n" +
                        "                            <td style=\"width: 5%\">\n" +
                        "                                <a id=\"+arr[i].Title+\" onclick=\"formDataCollection(this)\">收藏</a>\n" +
                        "                            </td>\n" +
                        "                        </tr>"
                    )
                }
                $("#NowTitle").text(arr[0].Title);
                $("#NowAuthor").text("作者："+arr[0].Author);
                $("#NowPublishTime").text("发布时间："+arr[0].PulishTime);
                $("#NowTag").text("标签："+arr[0].Tag);
                $("#NowUrl").text("网址："+arr[0].Url);
                $("#NowContent").text(arr[0].Content)
            },
            error: function () {
                alert("未搜索到相关信息");
            }
        })
    }
);
//退出登录-----------------------------------------------------------------------
$("#Logout").click(
    function(){
        var data={
            "logout":"logout"
        };
        var json=JSON.stringify(data);
        $.ajax({
            url:"/apt/user/logout",
            type:"post",
            dataType:"json",
            contentType: 'application/json; charset=utf-8', // 很重要
            data:json,
            success:function(daa){
                var signal=daa.signal;
                if(signal.equal("logout Success")){
                    alert("注销成功");
                    window.location.href='localhost:8888/apt/html/Login.html';
                }
                else if(signal.equal("logout Fail")){
                    alert("注销失败");
                }
            },
            error:function(){
                alert("注销error");
            }
        })
    }
);
