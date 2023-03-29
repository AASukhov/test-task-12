package test_task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userfrom")
    private int userfrom;

    @Column(name = "userto")
    private int userto;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private String status;

    public Task() {
    }

    public Task(int id, int userfrom, int userto, String text, String status) {
        this.id = id;
        this.userfrom = userfrom;
        this.userto = userto;
        this.text = text;
        this.status = status;
    }

    public Task(int userfrom, int userto, String text, String status) {
        this.userfrom = userfrom;
        this.userto = userto;
        this.text = text;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(int userfrom) {
        this.userfrom = userfrom;
    }

    public int getUserto() {
        return userto;
    }

    public void setUserto(int userto) {
        this.userto = userto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
