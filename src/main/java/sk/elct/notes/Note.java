package sk.elct.notes;

import java.time.LocalDate;

public class Note {
    private Long id;
    private String title;
    private String text;
    private boolean done;
    private LocalDate deadline;
    private NoteType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", title=" + title + ", text=" + text + ", done=" + done + ", deadline=" + deadline + ", type=" + type + '}';
    }
}
