package me.bullyalert.test;

import me.bullyalert.AppConstants;
import me.bullyalert.AppEmail;
import me.bullyalert.AppSMS;
import me.bullyalert.dal.dao.DAO;
import me.bullyalert.dal.pojo.Subscriber;

public class TestEmail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int alertType = AppConstants.ALERT_TYPE_WARNING;
        /*
         Subscriber subscriber = DAO.getInstance().getSubscribersByEmail("yogev.triki@centirion.com").get(0);
         new AppEmail().sendSubscriptionWelcomeEmail(subscriber.getEmail());
         new AppEmail().sendAlertEmail(subscriber, alertType);
         */
        Subscriber subscriber = DAO.getInstance().getSubscribersByPhone("+972522952825").get(0);
        new AppSMS().sendAlertSMS(subscriber, alertType);
    }
}
