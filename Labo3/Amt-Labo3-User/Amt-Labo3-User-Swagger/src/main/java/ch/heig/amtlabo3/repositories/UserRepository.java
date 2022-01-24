package ch.heig.amtlabo3.repositories;

import ch.heig.amtlabo3.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findById(int id);
    List<UserEntity> findByUserNameLike(String pattern);
}