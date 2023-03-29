package test_task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "histories")
public class History {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "task_id")
    private int taskId;
    @Column(name = "userto")
    private int userto;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private String date;

    public History(int id,int taskId, int userto, String status, String date) {
        this.id = id;
        this.taskId = taskId;
        this.userto = userto;
        this.status = status;
        this.date = date;
    }

    public History() {
    }

    public History(int taskId, int userto, String status, String date) {
        this.taskId = taskId;
        this.userto = userto;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
