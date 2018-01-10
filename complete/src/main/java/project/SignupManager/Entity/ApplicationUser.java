package project.SignupManager.Entity;

import javax.persistence.*;

@Entity
public class ApplicationUser {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String username;
     private String password;
     private String email;

     @Column(name = "enabled")
     private boolean enabled;

     public long getId() {
          return id;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public void setId(long id) {
          this.id = id;
     }

     public boolean isEnabled() {
          return enabled;
     }

     public void setEnabled(boolean enabled) {
          this.enabled = enabled;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }
}