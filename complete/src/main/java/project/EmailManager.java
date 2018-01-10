package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import project.SignupManager.Entity.ApplicationUser;

public class EmailManager {

     private JavaMailSender mailSender;

     private ApplicationUser applicationUser;

     private String token;



     public EmailManager(ApplicationUser applicationUser, String token){
          this.applicationUser = applicationUser;
          this.token = token;

     }

     public void sendMessage(){
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setTo(applicationUser.getEmail());
          simpleMailMessage.setSubject("Registration Confirmation");
          simpleMailMessage.setText("http://localhost:8000/sign-up/token?token=" + token);
          mailSender.send(simpleMailMessage);
     }
}
