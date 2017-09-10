
//登陆后加载用户信息
 $(document).ready(function(){
        displayUserInfo()
    });
//登陆后搜所有信息
$(document).ready(function(){
    displayInfo()
});

$("#Search").click(
    function(){
        var searchInfo = $("#SearchInfo").val();
        searchFunction(searchInfo);
    }
);

$("#Logout").click(
    function(){
        logOutFunction();
    }
);

function collection(){
    searchCollection()
}

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

//点击查看后显示信息
function formDataView(obj){
    var data=obj.ID;
    viewInfo(data);
}
//点击收藏后收藏信息
function formDataCollection(obj){
    var data=obj.ID;
    clickCollection(data);
}

//--------------------------------取消收藏---------------------------
function collectionCancel(data){
    var json=JSON.stringify({"demand":data.Title})
    $.ajax({
        type:"post",
        url:"",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data : json,
        success:function(daa){
            var signal=daa.signal;
            if(signal.equal("cancel success")){
                alert("取消成功!");
            }
            else {
                alert("取消错误!");
            }
        },
        error:function(){
            alert("出错啦！！");
        }
    })
}
//--------------------------------查看信息-----------------------------
function viewInfo(searchInfo){
    var data={"demand":searchInfo};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/homepage/search_info",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data : json,
        success: function (daa) {
            $("tr").remove(".formData");
            console.log("数据为",daa);
            var arr = daa.array;
            console.log(arr);
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
//--------------------------------点击收藏--------------------------------
function clickCollection(){
    var data={"demand":"collection"};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/homepage/search_info",
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
//--------------------------------收藏信息查询--------------------------
function searchCollection(){
    var data={"demand":"searchCollection"};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "",
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
//<--------------------------------登录显示--------------------------
function displayInfo(){
    var data={"demand":"DisplayAllInfo"};
    var json=JSON.stringify(data);
    $.ajax({
        type: "post",
        url: "/apt/homepage/data",
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
                    "                                <a id=\"+arr[i].Title+\" onclick=\"formDataCollection(this)\">收藏</a>\n" +
                    "                            </td>\n" +
                    "                        </tr>"
                )
            }
            $("#NowTitle").text(arr[0].TiTle)
            $("#NowAuthor").text("作者："+arr[0].Author)
            $("#NowPublishTime").text("发布时间："+arr[0].PulishTime)
            $("#NowTag").text("标签："+arr[0].Tag)
            $("#NowUrl").text("网址："+arr[0].Url)
            $("#NowContent").text(arr[0].Content)
        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//---------------------------------登录获取用户信息----------------------------------
function displayUserInfo(){
    var data={
        "demand":"UserInfo"
    };
    var json=JSON.stringify(data);
    $.ajax({
        url:"",
        data:json,
        dataType:"json",
        contentType:'application/json; charset=utf-8', // 很重要
        type:"post",
        success:function(daa){
            $(".dropdown-toggle").text(UserName);
            $(".username").text(UserName);
            $("#eamilInfo").text(Email);
        },
        error:function(){
            console.log("error!!!!!!")
        }
    })
}
//---------------------------------分类点击显示--------------------------------------
function searchFunction(searchInfo) {
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

        },
        error: function () {
            alert("未搜索到相关信息");
        }
    })
}
//--------------------------------退出登录-----------------------------------
function logOutFunction(){
    var data={
        "demand":"logout"
    };
    var json=JSON.stringify(data);
    $.ajax({
        url:"",
        type:"post",
        dataType:"json",
        contentType: 'application/json; charset=utf-8', // 很重要
        data:json,
        success:function(daa){
            var signal=daa.signal;
            if(signal.equal("logout success")){
                alert("注销成功");
            }
        }
    })
}