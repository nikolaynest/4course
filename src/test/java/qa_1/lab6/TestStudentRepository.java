package qa_1.lab6;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by nikolay on 03.06.14.
 */
public class TestStudentRepository {



//    @Mock
//    private StudentRepository repositoryMock;
//    private Student student;
//    private ArrayList<Student> students;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//        repositoryMock = Mockito.mock(StudentRepository.class);
//        student = new Student("Vasya","Pupkin",89, "Nikolaev", "Computer Engineering");
//        students = new ArrayList<>();
//        students.add(student);
//        students.add(new Student("Fedya","Ivanov",90,"Kiev","Lenguist"));
//    }
//
//    @Test
//    public void testAddStudentBehavior() throws SQLException {
//        repositoryMock.addStudent(student);
//        verify(repositoryMock).addStudent(student);
//    }
//
//    @Test
//    public void testAddStudentNumberOfInteractions() throws SQLException {
//        Student s2 = new Student("name","lastname",33,"city","faculty");
//        repositoryMock.addStudent(s2);
//        repositoryMock.addStudent(student);
//        verify(repositoryMock, Mockito.times(1)).addStudent(s2);
//    }
//
//    @Test
//    public void testGetStudentByFirstnameAndLastname() throws SQLException {
//        Mockito.when(repositoryMock.getStudent("Vasya","Pupkin")).thenReturn(student);
//        Student result = repositoryMock.getStudent("Vasya","Pupkin");
//        Mockito.verify(repositoryMock).getStudent("Vasya","Pupkin");
//        assertEquals(student.getId(), result.getId());
//    }
//
//    @Test
//    public void testGetStudent() throws SQLException {
//        Student s2 = new Student("name","lastname",33,"city","faculty");
//        Mockito.when(repositoryMock.getStudent("name","lastname")).thenReturn(s2);
//        assertEquals(repositoryMock.getStudent("name","lastname"),s2);
//    }
//    @Test
//    public void testRemoveStudent() throws SQLException {
////        Mockito.when(repositoryMock.removeStudent(student))
////        doAnswer(new Answer() {
////            @Override
////            public Object answer(InvocationOnMock invocation) throws Throwable {
////                return null;
////            }
////        }).when(repositoryMock.removeStudent(student));
//    }
//    @Test
//    public void testRemoveStudent2(){
//
//    }
//    @Test
//    public void testgetAllStudents() throws SQLException {
//        when(repositoryMock.getAllStudents()).thenReturn(students);
//        List<Student> list = repositoryMock.getAllStudents();
//        assertEquals("Vasya",list.get(0).getFirstName());
//    }
//    @Test
//    public void testgetAllStudentsSize() throws SQLException {
//        when(repositoryMock.getAllStudents()).thenReturn(students);
//        List<Student> list = repositoryMock.getAllStudents();
//        assertEquals(2,list.size());
//    }

}
