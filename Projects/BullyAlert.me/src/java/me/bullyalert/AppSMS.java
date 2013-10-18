package me.bullyalert;

import com.centirion.commons.sms.clickatell.ClickatellSMS;
import java.net.URLEncoder;
import me.bullyalert.dal.pojo.Subscriber;

public class AppSMS {

    public void sendAlertSMS(Subscriber subscriber, int alertType) {
        try {
            ClickatellSMS smsSender = new ClickatellSMS("centirion", "ACNSVeh3", "3366574");
            //InforuSMS smsSender = new InforuSMS("centirion", "tumXK4px");
            StringBuilder text = new StringBuilder();

            if (alertType == AppConstants.ALERT_TYPE_ATTENTION) {
                text.append("BullyAlert.me - Attention:\n");
                text.append("We have detected a potential cyber bullying against your child. Click here for more details: ");
            } else if (alertType == AppConstants.ALERT_TYPE_WARNING) {
                text.append("BullyAlert.me - Warning:\n");
                text.append("We have detected a potential cyber bullying against your child. Click here for more details: ");
            }
            text.append("http://www.bullyalert.me/app/?twitter_account=" + URLEncoder.encode(subscriber.getTwitterMonitor(), "utf-8"));

            smsSender.sendSMS(subscriber.getPhone(), "972522952825", text.toString()); // Clickatell (need 972)
            //smsSender.sendSMS(to.getNumber(), "0522952825", text.toString()); // Inforu (no 972)
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public void sendSubscriptionWelcomeSMS(Subscriber subscriber) {
        try {
            ClickatellSMS smsSender = new ClickatellSMS("centirion", "ACNSVeh3", "3366574");
            //InforuSMS smsSender = new InforuSMS("centirion", "tumXK4px");
            StringBuilder text = new StringBuilder();

                text.append("BullyAlert.me - Attention:\n");
                text.append("Thank you for using our service. Click here for your mobile dashboard: ");
            text.append("http://www.bullyalert.me/app/?twitter_account=" + URLEncoder.encode(subscriber.getTwitterMonitor(), "utf-8"));

            smsSender.sendSMS(subscriber.getPhone(), "972522952825", text.toString()); // Clickatell (need 972)
            //smsSender.sendSMS(to.getNumber(), "0522952825", text.toString()); // Inforu (no 972)
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
