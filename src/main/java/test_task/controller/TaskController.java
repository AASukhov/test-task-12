package test_task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test_task.dto.TaskRequestDTO;
import test_task.dto.TaskResponseDTO;
import test_task.entity.Task;
import test_task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService service;

    public TaskController (TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestHeader ("token") String token,
                                             @RequestBody TaskRequestDTO taskRequestDTO) throws InterruptedException {
        return service.createTask(token, taskRequestDTO);
    }

    @GetMapping("/list")
    public List<TaskResponseDTO> getTasks (@RequestHeader ("token") String token) {
        return service.getTasks(token);
    }
}
