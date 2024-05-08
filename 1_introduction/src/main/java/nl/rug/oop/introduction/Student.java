package nl.rug.oop.introduction;

/**
 * Student class (subclass of person).
 */

public class Student extends Person {
    private int knowledgeLevel;

    /**
     * Create a new student.
     * @param name is the name of the student.
     * @param knowledgeLevel is the knowledge level of the student. If it is greater than 6 (max), it is set to 6.
     */
    public Student(String name, int knowledgeLevel) {
        super(name);
        if (knowledgeLevel > 6) {
            knowledgeLevel = 6;
        }
        this.knowledgeLevel = knowledgeLevel;
    }

    public void obtainKnowledge() {
        knowledgeLevel = Math.min(knowledgeLevel + 1, 6);
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public Submission doAssignment(Assignment assignment) {
        Submission submission = new Submission(this, assignment);
        return submission;
    }
}

