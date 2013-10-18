<%@page import="com.centirion.commons.strings.StringUtils"%>
<%
    if (StringUtils.isNullOrEmpty(request.getParameter("twitter_account"))) {
        response.sendRedirect("/");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Protect your child from cyber bullying | BullyAlert.me</title>

        <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

        <link href="reset.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, width=device-width, height=device-height, maximum-scale=1.0, user-scalable=no"/>

        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
        <link rel="apple-touch-icon" sizes="57x57" href="images/app_icon_57x57.png">
        <link rel="apple-touch-icon" sizes="72x72" href="images/app_icon_72x72.png"/>
        <link rel="apple-touch-icon" sizes="114x114" href="images/app_icon_114x114.png"/>
        <link rel="apple-touch-icon" sizes="144x144" href="images/app_icon_144x144.png"/>
        <link rel="apple-touch-startup-image" href="images/splash_320x460.png"/>
        <link rel="apple-touch-startup-image" href="images/splash_640x920.png" sizes="640x920">
        <link rel="apple-touch-startup-image" href="images/splash_640x1096.png" sizes="640x1096">
        <link rel="SHORTCUT ICON" href="images/app_icon_114x114.png">
        <link rel="image_src" href="images/app_icon_114x114.png"/>

        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="http://d3js.org/d3.v2.min.js"></script>
        <script type="text/javascript" src="gauge.js"></script>
        <script type="text/javascript" src="app.js"></script>
    </head>
    <body onload="startApp();">
        <input type="hidden" id="twitter_account" value="<%=request.getParameter("twitter_account") != null ? request.getParameter("twitter_account") : ""%>">
        <!--
        <div style="width: 350px; margin-left: auto; margin-right: auto;">
            <span class="field_name">Your child's twitter user name:</span><br>
            <input id="twitter_username" type="text" class="text_field" value="<%=request.getParameter("twitter_account") != null ? request.getParameter("twitter_account") : ""%>"><input type="button" class="submit_button" value="Monitor">
        </div>
        -->
        <div style="text-align: center;">
            <img src="/images/logo.png" style="margin: 10px;">
        </div>
        <div id="screenLoading" style="text-align: center;">
            <img src="images/loading.gif" style="margin-top: 20px;">
        </div>
        <div id="screenData" style="display: none; text-align: center;">

            <div class="section_div">
                <span class="section_title">Cyber Bullying Analysis for:</span><br>
                <br>
                <h1 id="twitter_account_h1" style="margin-top: 5px;"></h1>
            </div>

            <div class="section_div" style="margin-bottom: -130px;">
                <span class="section_title">Threat level</span><br>
                <br>
                <div id="power-gauge"></div>
            </div>

            <div class="section_div" id="section_tweets" style="display: none;">
                <span class="section_title">Bullying Tweets</span><br>
                <br>
                <div id="tweet_master" style="display: none; margin-top: 15px;">
                    <img src="images/tweet.png" class="tweet_icon">
                    <span class="tweet_span"></span>
                </div>
            </div>
            <div class="section_div" id="section_bullies" style="display: none;">
                <span class="section_title">Top Bullies</span><br>
                <br>
                <div id="bully_master">

                </div>
            </div>
        </div>
        <!--
        <script>
            function refresh() {
                var twitter_username = document.getElementById("twitter_username");
                console.log(twitter_username);
                setTimeout("refresh()", 1000);
            }
            refresh();
        </script>
        -->
    </body>
</html>
