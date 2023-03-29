package test_task.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class TaskResponseDTO {

    private int id;
    private int userfrom;
    private int userto;
    private String text;
    private String status;

    public TaskResponseDTO(int id, int userfrom, int userto, String text, String status) {
        this.id = id;
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
