package nl.rug.oop.introduction;

import java.util.List;

/**
 * Lecturer class (subclass of person).
 */

public class Lecturer extends Person {

    public Lecturer(String name) {
        super(name);
    }

    /**
     * This is a for loop that takes as parameters a list of students and each of them
     * obtain knowledge.
     * @param attendees is a list of the students that attend the lecture.
     */
    public void lecture(List<Student> attendees) {
        System.out.println("Knowledge has been provided");
        for (Student student: attendees) {
            student.obtainKnowledge();
        }
    }
}
