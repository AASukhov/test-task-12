package test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test_task.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(int id);
}
