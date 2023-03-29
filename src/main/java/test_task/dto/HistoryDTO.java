package test_task.dto;

import javax.persistence.Column;

public class HistoryDTO {

    private int id;
    private int taskId;
    private int userto;
    private String status;
    private String date;

    public HistoryDTO(int id, int taskId, int userto, String status, String date) {
        this.id = id;
        this.taskId = taskId;
        this.userto = userto;
        this.status = status;
        this.date = date;
    }

    public HistoryDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserto() {
        return userto;
    }

    public void setUserto(int userto) {
        this.userto = userto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
