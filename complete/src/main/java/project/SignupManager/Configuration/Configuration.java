package project.SignupManager.Configuration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import project.SignupManager.DAO.UserDAO;
import project.SignupManager.DAO.VerificationDAO;
import project.SignupManager.Manager.UserRepositoryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@org.springframework.context.annotation.Configuration

public class Configuration {
     @Bean
     public JavaMailSenderImpl javaMailSender(){
          return new JavaMailSenderImpl();
     }
     @Bean
     public BCryptPasswordEncoder bCryptPasswordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     public UserRepositoryManager userRepositoryManager(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder,
                                                        VerificationDAO verificationDAO, JavaMailSenderImpl javaMailSender){
          return new UserRepositoryManager(userDAO,bCryptPasswordEncoder, verificationDAO, javaMailSender);
     }





}
