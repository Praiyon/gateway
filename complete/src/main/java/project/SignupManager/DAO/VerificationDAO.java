package project.SignupManager.DAO;


import project.SignupManager.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationDAO extends JpaRepository<VerificationToken, Long> {
     VerificationToken findByToken(String token);

}
