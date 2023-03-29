package test_task.dto;

public class TaskRequestDTO {
    private int to;
    private String text;

    public TaskRequestDTO(int to, String text) {
        this.to = to;
        this.text = text;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
