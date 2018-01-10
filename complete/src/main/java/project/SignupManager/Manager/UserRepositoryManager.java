package project.SignupManager.Manager;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import project.EmailManager;
import project.SignupManager.DAO.UserDAO;
import project.SignupManager.DAO.VerificationDAO;
import project.SignupManager.Entity.ApplicationUser;
import project.SignupManager.Entity.VerificationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class UserRepositoryManager {
     private UserDAO userDAO;
     private BCryptPasswordEncoder bCryptPasswordEncoder;
     private VerificationDAO verificationDAO;
     private JavaMailSender javaMailSender;
     final String TOKEN = UUID.randomUUID().toString();


     public UserRepositoryManager(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder,
                                  VerificationDAO verificationDAO,
                                  JavaMailSender javaMailSender){
          this.userDAO = userDAO;
          this.bCryptPasswordEncoder = bCryptPasswordEncoder;
          this.verificationDAO = verificationDAO;
          this.javaMailSender = javaMailSender;
     }

     public void setToken(ApplicationUser user){
          VerificationToken verificationToken = new VerificationToken();

          user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
          user.setEnabled(false);
          userDAO.save(user);
          verificationToken.setUser(user);
          verificationToken.setToken(TOKEN);
          verificationToken.setExpiryDate();
          verificationDAO.save(verificationToken);

     }

     public void sendMail(ApplicationUser user){
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setTo(user.getEmail());
          simpleMailMessage.setSubject("Registration Confirmation");
          simpleMailMessage.setText("http://localhost:8000/sign-up/token?token=" + TOKEN);
          javaMailSender.send(simpleMailMessage);
     }
}
