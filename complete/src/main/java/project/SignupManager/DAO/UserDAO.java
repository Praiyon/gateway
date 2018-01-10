package project.SignupManager.DAO;



import project.SignupManager.Entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<ApplicationUser, Long> {
     ApplicationUser findByUsername(String username);
}