package qa_1.lab6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 02.06.14.
 */
public class StudentRepository {
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
        }
        return connection;
    }

    public StudentRepository() throws SQLException {
        getInstance();
    }

    public void addStudent(Student student) throws SQLException {
        Connection con = getInstance();
        String insertSQL = "INSERT into Student(first_name,last_name,average_assessment,address,faculty) values (?,?,?,?,?)";
        PreparedStatement pd = con.prepareStatement(insertSQL);
        pd.setString(1, student.getFirstName());
        pd.setString(2, student.getLastName());
        pd.setInt(3, student.getAverageAssessment());
        pd.setString(4, student.getAddress());
        pd.setString(5, student.getFaculty());
        pd.executeUpdate();
        con.close();
    }

    public void removeStudent(Student student) throws SQLException {
        Connection con = getInstance();
        PreparedStatement ps = con.prepareStatement("DELETE from Student where id_student = ?");
        ps.setInt(1, student.getId());
        ps.executeUpdate();
        con.close();
    }

    public Student getStudent(String name, String lastName) throws SQLException {
        Connection con = getInstance();
        PreparedStatement ps = con.prepareStatement("Select * from Student where first_name=? and last_name=?");
        ps.setString(1, name);
        ps.setString(2, lastName);
        ResultSet resultSet = ps.executeQuery();
        Student student = null;
        if (resultSet.next()) {
            student = new Student();
            student.setId(resultSet.getInt(1));
            student.setFirstName(resultSet.getString(2));
            student.setLastName(resultSet.getString(3));
            student.setAverageAssessment(resultSet.getInt(4));
            student.setAddress(resultSet.getString(5));
            student.setFaculty(resultSet.getString(6));
        }
        con.close();
        return student;
    }

    public List<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Connection con = getInstance();
        PreparedStatement ps = con.prepareStatement("Select * from Student");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt(1));
            student.setFirstName(resultSet.getString(2));
            student.setLastName(resultSet.getString(3));
            student.setAverageAssessment(resultSet.getInt(4));
            student.setAddress(resultSet.getString(5));
            student.setFaculty(resultSet.getString(6));
            students.add(student);
        }
        return students;
    }
}
