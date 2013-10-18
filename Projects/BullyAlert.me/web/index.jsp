<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Protect your child from cyber bullying | BullyAlert.me</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700' rel='stylesheet' type='text/css'>
        <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

        <link href="reset.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>

        <style type="text/css">
            html {
                background:url('images/background.jpg') no-repeat center center fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
        </style>

        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="app.js"></script>

    </head>
    <body>
        <!--
        <div id="landing-icons">		
            <i class="icon-google-plus"></i>&nbsp;&nbsp;<i class="icon-twitter"></i>&nbsp;&nbsp;<i class="icon-tumblr"></i>&nbsp;&nbsp;<i class="icon-facebook"></i>
        </div>	
        -->
        <div style="width: 700px; height: 500px; margin-top: 170px; margin-left: auto; margin-right: auto;">
            <h1>Protect your child from cyber bullying</h1>
            <div class="h2_div">
                <h2>
                    "Half of teens have been the victims of cyber bullying.
                    Well over half of young people do not tell their parents when cyber bullying occurs."
                </h2> 
            </div>
            <!--
            <h3>            
                BullyAlert.me helps you protect your child from cyber bullying
            </h3> 
            -->
            <div class="signup_div">
                <h3>Check if your child is a subject of cyber bulling:</h3>
                <table>
                    <tr>
                        <td>
                            <span class="field_name">Your child's twitter user name:</span><br>
                            <span class="field_number">1</span>
                            <input name="twitter_account" id='twitter_account' type="text" class="text_field" style="width: 180px;">
                        </td>
                        <td style="width: 25px;"></td>
                        <td>
                            <span class="field_name">Your phone number or email (for alerts):</span><br>
                            <span class="field_number">2</span>
                            <input name="phone_or_email" id='phone_or_email' type="text" class="text_field" style="width: 250px;">
                        </td>
                        <td style="width: 25px;"></td>
                        <td style='vertical-align: bottom;'>
                            <input type="button" value="Check" class='submit_button' onclick='subscribe();'>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
