var gauge_value = 0;

function startApp() {
    //prepareGauge();
    refresh();
}

function refresh() {
    $("#screenData").hide();
    $("#screenLoading").show();
    var twitter_account = document.getElementById("twitter_account").value;
    $.post("https://bullyalert.herokuapp.com/api/analyze", {query: '@' + twitter_account}, function(data) {
        console.log(data);
        $("#twitter_account_h1").html("@" + twitter_account);
        gauge_value = data.level;

        if (gauge_value > 0) {

            // Tweets
            console.log(data.messages);
            for (i = 0; i < data.messages.length; i++) {
                $("#tweet_master").clone().appendTo("#section_tweets").show().find(".tweet_span").html(data.messages[i].text);
            }
            $("#section_tweets").show();

            // Bullies
            //$("#section_bullies").show();
        }
        prepareGauge();

        $("#screenLoading").hide();
        $("#screenData").fadeIn(1000);
    });
}

function prepareGauge() {
    var powerGauge = gauge('#power-gauge', {
        size: 300,
        clipWidth: 300,
        clipHeight: 300,
        ringWidth: 60,
        maxValue: 10,
        transitionMs: 4000,
    });
    powerGauge.render();

    function updateReadings() {
        // just pump in random data here...
        //console.log("updateReadings(): " + gauge_value);
        //console.log("updateReadings(): " + Math.min(10, 10 * gauge_value + Math.random() * 1));
        powerGauge.update(Math.min(10, 10 * gauge_value + Math.random() * 1));
    }

    // every few seconds update reading values
    updateReadings();
    setInterval(function() {
        updateReadings();
    }, 3000);
}




