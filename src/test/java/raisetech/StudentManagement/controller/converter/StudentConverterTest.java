package raisetech.StudentManagement.controller.converter;

import static org.mockito.Mockito.verify;

import java.lang.Exception;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.domain.StudentDetail;


class StudentConverterTest {

  @Mock
  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void コンバーターで変換処理ができること() throws Exception {
    Exception e = new Exception();

    List<Students> studentsList = new ArrayList<>();

    Students student = new Students();
    student.setId(99822);
    student.setName("testName");
    student.setNickname("testNickname");
    student.setAge(2887);
    student.setGender("testGender");
    student.setFurigana("testFurigana");
    student.setLivingArea("testLivingArea");
    student.setMailAddress("testMailAddress");
    student.setRemark("testRemark");
    student.setIsDeleted(false);
    studentsList.add(student);

    List<StudentCourses> studentCoursesList = new ArrayList<>();

    StudentCourses studentCourse = new StudentCourses();
    studentCourse.setCourseName("testCourseName");
    studentCourse.setId(79);
    studentCourse.setStartDate(LocalDate.now());
    studentCourse.setEndDate(LocalDate.now().plusYears(1));
    studentCourse.setSId(99822);

    List<StudentDetail> studentDetails = new ArrayList<>();

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentCourseList(studentCoursesList);
    studentDetail.setStudent(student);

    List<StudentDetail> actual = sut.convertStudentDetails(studentsList, studentCoursesList);

    Assertions.assertThat(actual.get(0).getStudent()).isEqualTo(student);
    Assertions.assertThat(actual.get(0).getStudentCourseList()).isEmpty();

  }
}