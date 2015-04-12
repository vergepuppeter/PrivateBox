package app.privatebox.com.privatebox.Object;

/**
 * Created by Kaede on 11/04/2015.
 */
public class Student {
    private String name;
    private String studentId;
    private String dob;

    public Student()
    {

    }

    public Student(String name, String studId, String dob)
    {
        this.name = name;
        this.studentId = studId;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}
