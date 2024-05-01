package nl.rug.oop.introduction;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *  The main class.
 */
public class Main{
    /**
     * Create a lecturer, a list of students and an assignment.
     * Instruct all the students to create a submission.
     * @param args are the pàrameters of the main function.
     */
    public static void main(String[] args) {
        Lecturer lecturer = new Lecturer("Reza");
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student("Virág",4);
        Student s2 = new Student("Belén",3);
        Student s3 = new Student("George", 2);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        Assignment assignment = new Assignment("Introduction",
                LocalDateTime.of(2024, Month.MAY, 1, 17, 0));
        List<Submission> submissionList = new ArrayList<Submission>();
        for (Student s : students) {
            Submission submission = s.doAssignment(assignment);
            submissionList.add(submission);
        }
        TeachingAssistant TA = new TeachingAssistant("Gabija");
        TA.gradeMultiple(submissionList);
        List<Student> attend = new ArrayList<Student>();
        attend.add(s1);
        attend.add(s3);
        lecturer.lecture(attend);
        TA.gradeMultiple(submissionList);
    }
}