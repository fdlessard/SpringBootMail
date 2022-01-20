package io.fdlessard.codebites.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMailApplication.class, args);
    }


    @RestController
    public class EmailController {

        private static final String NOREPLY_ADDRESS = "fdlessard+tw@gmail.com";

        @Autowired
        private JavaMailSender emailSender;

        @RequestMapping(value = "/sendemail")
        public String sendEmail() {
            sendSimpleMessage("fdlessard@gmail.com", "first email", "hello world");
            return "Email sent successfully";
        }


        public void sendSimpleMessage(String to, String subject, String text) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(NOREPLY_ADDRESS);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);

                emailSender.send(message);
            } catch (MailException exception) {
                exception.printStackTrace();
            }
        }

    }
}
