package nl.rug.oop.introduction;

import java.util.Random;
import java.util.List;

/**
 * The teaching assistant class (subclass of Person).
 */
public class TeachingAssistant extends Person {

    public TeachingAssistant(String name) {
        super(name);
    }

    /**
     * print the grade, assignment name, student name and the teaching assistant's name
     * every time a submission is graded.
     * @param submission is the graded submission.
     * @return returns the grade.
     */
    public int grade(Submission submission) {
        int grade = 0;
        if (submission.getTime().isBefore(submission.getAssignment().getDeadline())) {
            Random random = new Random();
            // Random number between submission quality and 10 (quality is at most 6)
            grade = random.nextInt(5) + submission.getQuality();
        }
        System.out.println("grade: "+grade+", assignment: "+submission.getAssignment().getName()
                +", student: "+ submission.getStudent().getName()+", teaching assistant: "+this.getName());
        return grade;
    }

    /**
     * Grade multiple submissions.
     * @param submissionList is a list of the assignments to be graded.
     */
    public void gradeMultiple(List<Submission> submissionList) {
        int grade;
        for (Submission sub : submissionList) {
            grade = grade(sub);
        }
    }
}
