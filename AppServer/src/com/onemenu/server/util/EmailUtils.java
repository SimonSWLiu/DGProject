package com.onemenu.server.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;

import com.onemenu.server.model.CustomerFeedback;
import com.onemenu.server.model.OrderForm;


public class EmailUtils {

    private static Logger LOGGER = Logger.getLogger(EmailUtils.class);

    // Gmail
    private static String onemenuMailHostName = "smtp.gmail.com";
    private static String onemenuMailAddress = "onemenu@onemenu.mobi";
    private static String onemenuPassword = "onemenu31415926";

    public static Boolean sendOrderFormToRestaurant(OrderForm orderForm) {

        String toMail = orderForm.getmRestaurant().getmEmail();

        if (TextUtils.isEmpty(toMail)) {
            return false;
        }

        Properties mailProps = new Properties();
        mailProps.setProperty("mail.smtp.host", onemenuMailHostName);
        mailProps.setProperty("mail.smtp.starttls.enable", "true");
        mailProps.setProperty("mail.smtp.auth", "true");
        mailProps.setProperty("mail.smtp.quitwait", "false");
        mailProps.setProperty("mail.smtp.ssl.trust", "*");

        Authenticator authenticator = new EmailAuthenticator(onemenuMailAddress, onemenuPassword);

        javax.mail.Session mailSession =
                javax.mail.Session.getDefaultInstance(mailProps, authenticator);
        // mailSession.setDebug(true);

        try {

            Address fromAddress = new InternetAddress(onemenuMailAddress);
            Address toAddress = new InternetAddress(toMail);
            Address bccAddress = new InternetAddress(onemenuMailAddress);

            String content;
            try {
                content = FaxUtils.getOrderFormHtml(orderForm);
            } catch (IOException e) {
                LOGGER.error("", e);
                return false;
            } catch (URISyntaxException e) {
                LOGGER.error("", e);
                return false;
            }

            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(fromAddress);
            msg.setSubject("[OneMenu Order] " + orderForm.getmRestaurant().getmName());
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            msg.setRecipient(RecipientType.TO, toAddress);
            msg.setRecipient(RecipientType.BCC, bccAddress);

            /*
             * Transport transport = session.getTransport("smtp"); transport.connect("smtp.163.com",
             * userName, password); transport.sendMessage(msg,msg.getAllRecipients());
             * transport.close();
             */

            Transport.send(msg);
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Send order form email to " + toMail);

        } catch (MessagingException e) {
            LOGGER.error("", e);
        }

        return true;

    }

    public static Boolean sendFeedbackEmail(CustomerFeedback customerFeedback) {

        Properties mailProps = new Properties();
        mailProps.setProperty("mail.smtp.host", onemenuMailHostName);
        mailProps.setProperty("mail.smtp.starttls.enable", "true");
        mailProps.setProperty("mail.smtp.auth", "true");
        mailProps.setProperty("mail.smtp.quitwait", "false");
        mailProps.setProperty("mail.smtp.ssl.trust", "*");

        Authenticator authenticator = new EmailAuthenticator(onemenuMailAddress, onemenuPassword);

        javax.mail.Session mailSession =
                javax.mail.Session.getDefaultInstance(mailProps, authenticator);

        try {

            Address fromAddress = new InternetAddress(onemenuMailAddress);
            Address toAddress = new InternetAddress(onemenuMailAddress);
            // modify by file 本处不用使用StringBuffer，Stringbuffer
            // 是线程安全的，性能不如StringBuilder（所有本身是线程安全的代码都不应该使用StringBuffer）
            // StringBuffer contentBuffer = new StringBuffer();
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append("<p>" + customerFeedback.getmCustomerEmail() + "</p>")
                    .append("<p>" + customerFeedback.getmCustomerName() + ":</p>")
                    .append("<p>" + customerFeedback.getmContent() + "</p>");
            String content = contentBuilder.toString();

            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(fromAddress);
            msg.setSubject("[OneMenu Feedback] ");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            msg.setRecipient(RecipientType.TO, toAddress);

            /*
             * Transport transport = session.getTransport("smtp"); transport.connect("smtp.163.com",
             * userName, password); transport.sendMessage(msg,msg.getAllRecipients());
             * transport.close();
             */

            Transport.send(msg);
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Send feedback email.");

        } catch (MessagingException e) {
            LOGGER.error("", e);
        }

        return true;
    }
}
