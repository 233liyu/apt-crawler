


function message(context, message) {
    toastr.options.timeOut = 3000;
    toastr.options.closeButton = true;
    $context = context;
    $message = message;
    $position = 'top-center';

    if($context == '') {
        $context = 'info';
    }

    if($position == '') {
        $positionClass = 'toast-left-top';
    } else {
        $positionClass = 'toast-' + $position;
    }
    toastr.remove();
    toastr[$context]($message, '' , { positionClass: $positionClass });
}



function getUserInfo() {
// ajax to get user info
    $.ajax({
        url : '/apt/user/info',
        type: "POST",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        success : function (data) {
            console.log(data);
            if(data.state){
                // log in success
                //{"user_name":"liyu","email":"sdf@ds.com"}
                var user = data.element;
                $("#user_name").html(user.user_name);
                sessionStorage.setItem('user_info',user);
                console.log('log in success');
            } else {
                // error
                if(data.errMsg == 'not logged'){
                    message('error','您还未登录！');
                    location.replace('/apt/page-login.html')
                } else {
                    message('error','服务器内部错误');
                }
            }
        },
        error : function () {
            console.log('error network');
            message('error','通信错误！')
        }
    })
}