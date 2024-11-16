package raisetech.StudentManagement.controller.converter;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.domain.StudentDetail;


class StudentConverterTest {

  @Mock
  private StudentConverter tester;
  private java.lang.Exception Exception;

  @BeforeEach
  void before() {
    tester = new StudentConverter();
  }

  @Test
  void コンバーターで変換処理ができること() throws Exception {

    List<Students> studentsList = new ArrayList<>();
    List<StudentCourses> studentCoursesList = new ArrayList<>();
    List<StudentDetail> studentDetails = new ArrayList<>();
    if (studentDetails.equals(tester.convertStudentDetails(studentsList, studentCoursesList))){
      
    }else {
      throw Exception ;
    }
    
  }
}