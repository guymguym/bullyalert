package me.bullyalert;

import com.centirion.commons.http.HttpUtils;
import com.centirion.commons.strings.StringUtils;
import java.net.URLEncoder;
import java.util.Date;
import me.bullyalert.dal.dao.DAO;
import me.bullyalert.dal.pojo.Subscriber;

public class App {

    public static void work() {
        try {
            for (Subscriber subscriber : DAO.getInstance().getSubscribers()) {
                try {
                    System.out.println("==============================");
                    System.out.println(subscriber.getTwitterMonitor());


                    String params = "query=" + URLEncoder.encode(subscriber.getTwitterMonitor(), "utf-8");
                    byte[] resultData = HttpUtils.postHttpData("https://bullyalert.herokuapp.com/api/analyze", params);
                    String resultString = new String(resultData);
                    String levelString = StringUtils.extractText(resultString, "\"level\":", ",").trim();
                    float level = Float.parseFloat(levelString);
                    System.out.println("level: [" + level + "]");
                    if (level > 0.66) {
                        App.alert(subscriber, AppConstants.ALERT_TYPE_WARNING);
                    } else if (level > 0.33) {
                        App.alert(subscriber, AppConstants.ALERT_TYPE_ATTENTION);
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                //System.out.println(new String(resultData));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void alert(Subscriber subscriber, int alertType) {
        try {
            // No more than 1 alert in 24 hours
            if (subscriber.getLastAlertSent().before(new Date(System.currentTimeMillis()-24*60*60*1000))) {
                return;
            }
            new AppEmail().sendAlertEmail(subscriber, alertType);
            new AppSMS().sendAlertSMS(subscriber, alertType);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
