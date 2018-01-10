package project.SignupManager.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
     private static final int EXPIRATION = 60 * 24;

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     private String token;

     @OneToOne(targetEntity = ApplicationUser.class, fetch = FetchType.EAGER)
     @JoinColumn(nullable = false, name = "username")
     private ApplicationUser user;

     private Date expiryDate;

     private Date calculateExpiryDate(int expiryTimeInMinutes) {
          Calendar cal = Calendar.getInstance();
          cal.setTime(new Timestamp(cal.getTime().getTime()));
          cal.add(Calendar.MINUTE, expiryTimeInMinutes);
          return new Date(cal.getTime().getTime());
     }

     public static int getEXPIRATION() {
          return EXPIRATION;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getToken() {
          return token;
     }

     public void setToken(String token) {
          this.token = token;
     }

     public ApplicationUser getUser() {

          return user;
     }

     public void setUser(ApplicationUser user) {
          this.user = user;
     }

     public Date getExpiryDate() {
          return expiryDate;
     }

     public void setExpiryDate() {
          this.expiryDate = calculateExpiryDate(EXPIRATION);
     }

}
