package test_task.service;

import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test_task.dto.HistoryDTO;
import test_task.dto.TaskRequestDTO;
import test_task.additional.Status;
import test_task.dto.TaskResponseDTO;
import test_task.entity.History;
import test_task.entity.Task;
import test_task.entity.User;
import test_task.exceptions.TaskException;
import test_task.exceptions.UserNotFoundException;
import test_task.repository.HistoryRepository;
import test_task.repository.TaskRepository;
import test_task.repository.UserRepository;
import test_task.security.AuthTokenGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private HistoryRepository historyRepository;
    private AuthTokenGenerator generator;
    private TaskServiceAsync serviceAsync;

    public TaskService (UserRepository userRepository,
                        TaskRepository taskRepository,
                        HistoryRepository historyRepository,
                        AuthTokenGenerator generator,
                        TaskServiceAsync serviceAsync) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.historyRepository = historyRepository;
        this.generator = generator;
        this.serviceAsync = serviceAsync;
    }

    public ResponseEntity<String> createTask (String token, TaskRequestDTO taskRequestDTO) throws InterruptedException {
        User user = getUser(token);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        int userTo = taskRequestDTO.getTo();
        String text = taskRequestDTO.getText();

        if (userRepository.existsById(userTo)) {
            int userId = user.getId();
            String status = String.valueOf(Status.RENDERING);
            Task task = new Task(userId,userTo,text,status);
            if (!taskRepository.existsByUserfromAndUsertoAndText(userId,userTo,text)) {
                taskRepository.save(task);
                Task res = taskRepository.findByUserfromAndUsertoAndText(user.getId(),userTo,text);
                int task_id = res.getId();
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = formater.format(new Date());
                History history = new History(task_id,userTo,status,date);
                historyRepository.save(history);
                serviceAsync.statusChanger(task_id);
            } else {
                throw new TaskException("Such task exists");
            }
            return new ResponseEntity<>("New task is created", HttpStatus.OK);
        } else throw new UserNotFoundException("User, for whom is the task is intended, doesn't exist");
    }

    public List<TaskResponseDTO> getTasks (String token) {
        User user = getUser(token);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        int id = user.getId();
        List<Task> list = taskRepository.findAllByUserto(id);
        List<TaskResponseDTO> result = new ArrayList<>();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                TaskResponseDTO response = new TaskResponseDTO(task.getId(),
                        task.getUserfrom(),
                        task.getUserto(),
                        task.getText(),
                        task.getStatus());
                result.add(response);
            }
        }
        return result;
    }

    public List<HistoryDTO> getHistory (String token) {
        User user = getUser(token);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        int id = user.getId();
        List<HistoryDTO> result = new ArrayList<>();
        List<History> list = historyRepository.findAllByUserto(id);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                History history = list.get(i);
                HistoryDTO dto = new HistoryDTO(history.getId(),
                        history.getTaskId(),
                        history.getUserto(),
                        history.getStatus(),
                        history.getDate());
                result.add(dto);
            }
        }
        return result;
    }

    private User getUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        final String username = generator.getNameFromToken(token);
        if (userRepository.existsByUsername(username)) {
            return userRepository.findUserByUsername(username);
        } else throw new UsernameNotFoundException("User not found");
    }
}
