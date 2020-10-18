package ir.mahmood.sahame.repository;

import ir.mahmood.sahame.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndCodeAndCodeSentAtAfter(String username, String code, Date date);
}
