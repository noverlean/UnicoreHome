package unicore.api.senders;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import unicore.api.utils.FreeMaker;

import java.util.Properties;

import jakarta.mail.Session;

public class MailSender {
    private static final String host="soulgoneofficial@gmail.com";
    private static final String user="soulgoneofficial@gmail.com";
    private static final String password="apfylxvkxtnvejsd";
    
    public static enum NotifyType
    {
        USER_EXIT_FROM_ENVIRONMENT(new EmailNotification("Здравствуйте, пользователь покинул ваше окружение!", FreeMaker.instance.makePage("userNotification", "Здравствуйте!<br>Похоже, что один из пользователей, ранее использующих ваше окружение покинул список пользователей. Вы можете ознакомиться со списком текущих пользователей вашего окружения на странице профиля сайта. <br> Ваш Unicore Home!"))),
        USER_ENTRY_TO_ENVIRONMENT(new EmailNotification("Здравствуйте, пользователь присоединился к вашему окружению!", FreeMaker.instance.makePage("userNotification", "Здравствуйте!<br>Похоже, появился пользователь, желающий просоединиться к вашему окружению! Сейчас на вашу почту поступит письмо с кодом подстверждения, который нужно будет ввести подключаемому пользователю на своем устройстве для обеспечения безопасности. <br> Ваш Unicore Home!")));

        @Getter
        private final EmailNotification userExitFromEnvironment;
        NotifyType(EmailNotification userExitFromEnvironment) {
            this.userExitFromEnvironment = userExitFromEnvironment;
        }
    }

    public static void sendCode(String customerMail, String code){
        sendMessage(customerMail, new EmailNotification("Подтвердите почту для регистрации на Unicore Home!", FreeMaker.instance.makePage("accessEmail", code.toUpperCase())));
    }

    public static void sendNotification(String customerMail, NotifyType emailNotification){
        sendMessage(customerMail, emailNotification.getUserExitFromEnvironment());
    }

    private static void sendMessage(String customerMail, EmailNotification emailNotification) {
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(customerMail));
            message.setSubject(emailNotification.getSubject());
            message.setContent(emailNotification.getContent(), "text/html; charset=UTF-8");

            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

    }

}
