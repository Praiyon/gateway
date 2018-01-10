package project.SignupManager.Rest;

import project.SignupManager.DAO.UserDAO;
import project.SignupManager.DAO.VerificationDAO;
import project.SignupManager.Entity.ApplicationUser;
import project.SignupManager.Entity.VerificationToken;
import project.SignupManager.Manager.UserRepositoryManager;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Calendar;

@RestController
@RequestMapping("/sign-up")
public class UserController {

     private UserRepositoryManager userRepositoryManager;
     private VerificationDAO verificationDAO;
     private UserDAO userDAO;

     public UserController(UserRepositoryManager userRepositoryManager,
                           VerificationDAO verificationDAO,
                           UserDAO userDAO) {
          this.userRepositoryManager = userRepositoryManager;
          this.verificationDAO = verificationDAO;
          this.userDAO = userDAO;
     }

     @PostMapping("/user")
     public ApplicationUser signUp(@RequestBody ApplicationUser user) throws Exception {
          if(userDAO.findByUsername(user.getUsername()) != null){
               throw new ValidationException("Username: " +
                       user.getUsername() + " already exists");
          }
          userRepositoryManager.setToken(user);
          userRepositoryManager.sendMail(user);
          return user;
     }
     @GetMapping(value = "/token")
     public VerificationToken confirmRegistration
             (@RequestParam("token") String token) throws Exception {

          VerificationToken verificationToken = verificationDAO.findByToken(token);
          if (verificationToken == null) {
               throw new ValidationException("Invalid token");
          }

          ApplicationUser user = verificationToken.getUser();
          Calendar cal = Calendar.getInstance();
          if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
               verificationDAO.delete(verificationToken);
               throw new ValidationException("Your token has timed out");

          }
          user.setEnabled(true);
          verificationDAO.delete(verificationToken);
          userDAO.save(user);
          return verificationToken;
     }

}