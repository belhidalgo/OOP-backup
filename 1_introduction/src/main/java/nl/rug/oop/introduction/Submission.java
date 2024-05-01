package nl.rug.oop.introduction;

import java.time.LocalDateTime;

/**
 * The submission class.
 */
public class Submission {
    private int quality;
    private LocalDateTime time;
    private Student student;
    private Assignment assignment;

    /**
     * To create a new submission.
     * @param student is the student who does the submission.
     * @param assignment is the submitted assignment.
     */
    public Submission(Student student, Assignment assignment){
        this.quality = student.getKnowledgeLevel();
        this.student = student;
        this.assignment = assignment;
        this.time = LocalDateTime.now();
    }

    public int getQuality() {
        return quality;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Student getStudent() {
        return student;
    }
}
