package qa_1.lab6;

/**
 * Created by nikolay on 02.06.14.
 */
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int averageAssessment;
    private String address;
    private String faculty;

    public Student(String firstName, String lastName, int averageAssessment, String address, String faculty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageAssessment = averageAssessment;
        this.address = address;
        this.faculty = faculty;
    }

    public Student() {

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public int getAverageAssessment() {
        return averageAssessment;
    }

    public String getAddress() {
        return address;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAverageAssessment(int averageAssessment) {
        this.averageAssessment = averageAssessment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
