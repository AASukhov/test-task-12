package test_task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import test_task.additional.Status;
import test_task.entity.Task;
import test_task.entity.User;
import test_task.repository.TaskRepository;
import test_task.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder encoder;

    public Runner(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String username_1 = "Alex";
        String password_1 = encoder.encode("0000");
        User user_1 = new User(username_1,password_1);
        userRepository.save(user_1);

        String username_2 = "Sergey";
        String password_2 = encoder.encode("1111");
        User user_2 = new User(username_2,password_2);
        userRepository.save(user_2);

        List<Task> list = new ArrayList<>();
        Task task_1 = new Task(1,1,2,"Task_1",String.valueOf(Status.COMPLETE));
        Task task_2 = new Task(2,1,2,"Task_2",String.valueOf(Status.COMPLETE));
        Task task_3 = new Task(3,2,1,"Task_3",String.valueOf(Status.COMPLETE));
        list.add(task_1); list.add(task_2); list.add(task_3);
        taskRepository.saveAll(list);
    }
}
