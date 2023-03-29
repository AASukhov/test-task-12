package test_task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import test_task.additional.Status;
import test_task.repository.TaskRepository;

@Service
public class TaskServiceAsync {

    private TaskRepository repository;

    public TaskServiceAsync (TaskRepository repository) {
        this.repository = repository;
    }

    @Async
    public void statusChanger (int id) throws InterruptedException {
        int time = (int) (Math.random() * 300000);
        Thread.sleep(time);
        repository.statusChange(id, String.valueOf(Status.COMPLETE));
    }
}
