package info.hongik.ee.domain;

import java.util.HashMap;

public class User {
    // 학번
    private String StudentId;
    // 학년
    private String Grade;
    // 평균 학점
    private Long Gpa;
    // 총 수강학점
    private String Acquisition;
    // 분야, 학수번호
    private HashMap<String, Long> SubjectsTaken;

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public Long getGpa() {
        return Gpa;
    }

    public void setGpa(Long gpa) {
        Gpa = gpa;
    }

    public String getAcquisition() {
        return Acquisition;
    }

    public void setAcquisition(String acquisition) {
        Acquisition = acquisition;
    }

    public HashMap<String, Long> getSubjectsTaken() {
        return SubjectsTaken;
    }

    public void setSubjectsTaken(HashMap<String, Long> subjectsTaken) {
        SubjectsTaken = subjectsTaken;
    }

    public User() {

    }

    public User(String studentId, String grade, Long gpa, String acquisition, HashMap<String, Long> subjectsTaken) {
        StudentId = studentId;
        Grade = grade;
        Gpa = gpa;
        Acquisition = acquisition;
        SubjectsTaken = subjectsTaken;
    }
}
