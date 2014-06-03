package qa_1.lab6;

import java.sql.SQLException;

/**
 * Created by nikolay on 03.06.14.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        StudentRepository sr = new StudentRepository();
        sr.addStudent(new Student("Vova","Sidorov",90,"Nikolaev","Lingust"));
    }
}
