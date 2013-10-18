package me.bullyalert;

import com.centirion.commons.email.EmailContent;
import com.centirion.commons.email.EmailSender;
import com.centirion.commons.encryption.Encryption;
import com.centirion.commons.strings.StringUtils;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import me.bullyalert.AppConstants;
import me.bullyalert.dal.pojo.Subscriber;

public class AppEmail {

    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
    private static final String SMTP_AUTH_USER = "centirion";
    private static final String SMTP_AUTH_PWD = "JNcxFdiqVmapB4";
    private static final int SMTP_HOST_PORT = EmailSender.SMTP_HOST_PORT_TLS;
    private static final String FROM = "BullyAlert.me <noreply@bullyalert.me>";
    private static final String BCC = "";
    private EmailSender defaultEmailSender = new EmailSender(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD, true);

    public AppEmail() {
    }

    public boolean sendAlertEmail(Subscriber subscriber, int alertType) {
        boolean sent = false;
        try {
            StringBuilder body = new StringBuilder();
            body.append(getHeader(subscriber));
            String title = "";

            if (alertType == AppConstants.ALERT_TYPE_ATTENTION) {
                title = "BullyAlert.me - Attention";
                body.append("We would like to bring to your attention a potential cyber bullying against your child.");
            } else {
                title = "BullyAlert.me - Warning!";
                body.append("This is a warning regarding a potential cyber bullying against your child!");
            }
            body.append("<br>");
            body.append("<br>");
            String url = "http://www.bullyalert.me/app/?twitter_account=" + URLEncoder.encode(subscriber.getTwitterMonitor(), "utf-8");
            body.append("<a href=\"" + url + "\">Click for more details</a>");
            body.append("<br>");
            body.append(getFooter(subscriber.getEmail()));
            EmailContent emailContent = new EmailContent(body.toString(), EmailContent.CONTENT_TYPE_HTML);
            EmailSender emailSender = defaultEmailSender;
            String from = FROM;
            sent = emailSender.sendEmail(
                    title,
                    emailContent,
                    from,
                    StringUtils.isNullOrEmpty(subscriber.getEmail()) ? null : new ArrayList<String>(Arrays.asList(subscriber.getEmail())),
                    null,
                    StringUtils.isNullOrEmpty(BCC) ? null : new ArrayList<String>(Arrays.asList(BCC)));
        } catch (Throwable t) {
            t.printStackTrace();
            sent = false;
        }
        return sent;
    }

    public void sendSubscriptionVerificationEmail(String email) {
        try {
            StringBuilder body = new StringBuilder();

            body.append(getHeader());
            String verifyUrl = "http://www.landingpageinstant.com/verify?id=" + URLEncoder.encode(Encryption.encrypt(email, AppConstants.SECRET), "utf-8") + "&email=true";
            body.append("Please verify your email address by clicking on this link:<br>");
            body.append("<a href=\"" + verifyUrl + "\">" + verifyUrl + "</a><br>");
            body.append(getFooter(email));

            EmailContent emailContent = new EmailContent(body.toString(), EmailContent.CONTENT_TYPE_HTML);

            boolean sent = defaultEmailSender.sendEmail("Email address verification",
                    emailContent,
                    FROM,
                    new ArrayList<String>(Arrays.asList(email)),
                    null,
                    new ArrayList<String>(Arrays.asList(BCC)));

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void sendSubscriptionWelcomeEmail(String email) {
        try {
            StringBuilder body = new StringBuilder();

            body.append(getHeader());
            //String verifyUrl = "http://www.bullyalert.me/verify?id=" + URLEncoder.encode(Encryption.encrypt(email, AppConstants.SECRET), "utf-8") + "&email=true";
            body.append("Thank you for using BullyAlert.me!<br>");
            body.append(getFooter(email));

            EmailContent emailContent = new EmailContent(body.toString(), EmailContent.CONTENT_TYPE_HTML);

            boolean sent = defaultEmailSender.sendEmail("Email address verification",
                    emailContent,
                    FROM,
                    new ArrayList<String>(Arrays.asList(email)),
                    null,
                    new ArrayList<String>(Arrays.asList(BCC)));

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void sendGeneralEmail(String to, String title, String body) {
        try {

            EmailContent emailContent = new EmailContent(body, EmailContent.CONTENT_TYPE_HTML);

            boolean sent = defaultEmailSender.sendEmail(title,
                    emailContent,
                    FROM,
                    new ArrayList<String>(Arrays.asList(to)),
                    null,
                    new ArrayList<String>(Arrays.asList(BCC)));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private String getHeader() {
        return getHeader(null);
    }

    private String getHeader(Subscriber subscriber) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<img src=\"http://www.bullyalert.me/images/logo.png\"><br>");
            sb.append("<br>");
            sb.append("Dear Parent,,<br>");
            sb.append("<br>");
            return sb.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private String getFooter(String email) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<br>");

            sb.append("Sincerely,<br>");
            sb.append("BullyAlert.me<br>");

            sb.append("<br>");
            sb.append("<div style=\"color: #808080; font-size: small;\">");

            sb.append("This message was sent by BullyAlert.me<br>");
            sb.append("Suite 32811, 471 Mundet Place, Hillside, NJ 07205, USA<br>");
            sb.append("Tel: +1-855-200-6624 (Mon-Thu 8am-2pm EST)<br>");
            sb.append("<a href=\"http://www.bullyalert.me\">http://www.bullyalert.me</a><br>");

            sb.append("<br><br>");
            if (email != null) {
                String enc_email = Encryption.encrypt(email, AppConstants.SECRET);
                String unsubscribeUrl = "http://www.bullyalert.me/action/unsubscribe?id=" + URLEncoder.encode(enc_email, "utf-8") + "&email=true";
                sb.append("<a href=\"http://www.bullyalert.me/privacy_policy\">Privacy Policy</a> | <a href=\"" + unsubscribeUrl + "\">Unsubscribe</a>.<br>");
            }
            sb.append("</div>");
            return sb.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
