function thankyou() {
    //alert("Thank you! We will notify you via Email once we launch!");
    var twitter_account = document.getElementById("twitter_account").value;
    window.location = "/app/?twitter_account=" + encodeURIComponent(twitter_account);
}

function subscribe() {
    var phone_or_email = document.getElementById("phone_or_email").value;
    var twitter_account = document.getElementById("twitter_account").value;
    $.post("/action/subscribe", {twitter_account: twitter_account, email_or_phone: phone_or_email});
    window.setTimeout("thankyou()", 1000);

}


