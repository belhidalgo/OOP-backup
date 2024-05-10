package nl.rug.oop.introduction;

import java.time.LocalDateTime;

/**
 * The assignment class.
 */
public class Assignment {
    private String name;
    private final LocalDateTime deadline;

    public Assignment(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getName() {
        return name;
    }
}
