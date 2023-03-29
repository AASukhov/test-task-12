package test_task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import test_task.additional.Status;
import test_task.entity.History;
import test_task.repository.HistoryRepository;
import test_task.repository.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskServiceAsync {

    private TaskRepository taskRepository;
    private HistoryRepository historyRepository;

    public TaskServiceAsync(TaskRepository taskRepository, HistoryRepository historyRepository) {
        this.taskRepository = taskRepository;
        this.historyRepository = historyRepository;
    }

    @Async
    public void statusChanger (int id) throws InterruptedException {
        int time = (int) (Math.random() * 300000);
        Thread.sleep(time);
        History history = historyRepository.findByTaskId(id);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formater.format(new Date());
        History history_new = new History(id,history.getUserto(),String.valueOf(Status.COMPLETE),date);
        taskRepository.statusChange(id, String.valueOf(Status.COMPLETE));
        historyRepository.save(history_new);
    }
}
