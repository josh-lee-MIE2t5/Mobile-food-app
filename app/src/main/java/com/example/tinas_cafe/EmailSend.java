package com.example.tinas_cafe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import android.os.NetworkOnMainThreadException;

public class EmailSend extends AsyncTask <Void, Void, Void>{

    private Context context;
    private String to, msg;
    public EmailSend(Context context, String to,  String msg){
        this.context = context;
        this.to = to;
        this.msg = msg;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tinascafebusiness@gmail.com", "rhhtrckdxyukjwek");
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tinascafebusiness@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Order from Tina's cafe confirmed!!!");
            message.setText(msg);
            Transport.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        catch (NetworkOnMainThreadException ex){
            throw new RuntimeException(ex);
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return null;
    }
}