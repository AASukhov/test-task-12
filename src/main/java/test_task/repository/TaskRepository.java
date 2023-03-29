package test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test_task.entity.Task;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByUserto(int userto);
    Task findByUserfromAndUsertoAndText (int userfrom, int userto, String text);
    boolean existsByUserfromAndUsertoAndText (int userfrom, int userto, String text);

    @Modifying
    @Query("update Task t set t.status = :status  where t.id = :id")
    void statusChange (@Param("id") int id, @Param("status") String status);
}
