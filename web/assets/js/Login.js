function user_action(ac) {
    var name = $("#signin-name").val();
    var pawd = $("#signin-password").val();
    var email = $("#signin-email").val();

    // console.log($("#signin-name"));

    console.log(name);
    console.log(pawd);

    var json = {
        action : ac,
        user_name : name,
        pass_word : pawd,
        email : email
    };

    $.ajax({
        url : '/apt/user/action',
        type: "POST",
        contentType: 'application/json; charset=utf-8', // 很重要
        dataType: "json",
        data: JSON.stringify(json),
        success : function (data) {
            console.log(data);
            if(data.state){
                // log in success
                console.log('log in success');
                // jump to main page
                location.replace('/apt');
            } else {

            }
        }
    })
}

function sign_up_switch(acc) {
    switch (acc){
        case 'login':
            $("#log_in_").hide();
            $("#email_box").hide();

            $("#sign_up_").show();
            $("#log_in_button").show();
            $("#sign_up_button").hide();

            break;
        case 'sign_up':
            $("#log_in_").show();
            $("#email_box").show();

            $("#sign_up_").hide();
            $("#log_in_button").hide();
            $("#sign_up_button").show();


            break;
    }
}