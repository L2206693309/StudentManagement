package raisetech.StudentManagement.repository;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること(){
    List<Students> actual = sut.findAllStudents();
    Assertions.assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の単一検索が行えること(){
    Students actual = sut.searchStudent(1);
    Assertions.assertThat(actual.getName()).isEqualTo("name1");
  }

  @Test
  void 論理削除されていない受講生の検索が行えること(){
    List<Students> actual = sut.searchUndeletedStudents();
    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 受講生コース情報の全件検索が行えること(){
    List<StudentCourses> actual = sut.findAllStudentCourseList();
    Assertions.assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生コース情報の単一検索が行えること(){
    List<StudentCourses> actual = sut.searchStudentCourse(1);
    Assertions.assertThat(actual.size()).isEqualTo(1);
    Assertions.assertThat(actual.getFirst().getCourseName()).isEqualTo("courseName1");
  }

  @Test
  void 受講生テーブルのidの最大値の検索が行えること(){
    Integer actual = sut.searchStudentsMaxId();
    Assertions.assertThat(actual).isEqualTo(5);
  }

  @Test
  void 受講生コース情報テーブルのidの最大値の検索が行えること(){
    Integer actual = sut.searchStudentsCoursesMaxId();
    Assertions.assertThat(actual).isEqualTo(5);
  }

  @Test
  void ステータステーブルの単一検索が行えること(){
    String actual = sut.searchStatusOfStudentsCourses(1);
    Assertions.assertThat(actual).isEqualTo("仮申込");
  }

  @Test
  void 受講生の条件付き検索が行えること(){
    String[] targetStudents = new String[10];
    targetStudents[1] = "name3";

    List<Students> actual = sut.searchTargetStudents(targetStudents);
    Assertions.assertThat(actual.getFirst().getId()).isEqualTo(3);
  }

  @Test
  void 受講生の登録が行えること(){
    Students student = new Students();
    student.setId(6);
    student.setName("testName");
    student.setNickname("testNickname");
    student.setAge(2887);
    student.setGender("testGender");
    student.setFurigana("testFurigana");
    student.setLivingArea("testLivingArea");
    student.setMailAddress("testMailAddress");
    student.setRemark("testRemark");
    student.setIsDeleted(false);

    sut.registerStudent(student);

    List<Students> actual = sut.findAllStudents();

    Assertions.assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生コース情報の登録が行えること(){
    StudentCourses studentCourse = new StudentCourses();
    studentCourse.setCourseName("testCourseName");
    studentCourse.setId(79);
    studentCourse.setStartDate(LocalDate.now());
    studentCourse.setEndDate(LocalDate.now().plusYears(1));
    studentCourse.setSId(99822);
    studentCourse.setStatusId(2);

    sut.registerStudentCourse(studentCourse);

    List<StudentCourses> actual = sut.findAllStudentCourseList();

    Assertions.assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生の更新が行えること(){
    Students student = new Students();
    student.setId(1);
    student.setName("testName");
    student.setNickname("testNickname");
    student.setAge(2887);
    student.setGender("testGender");
    student.setFurigana("testFurigana");
    student.setLivingArea("testLivingArea");
    student.setMailAddress("testMailAddress");
    student.setRemark("testRemark");
    student.setIsDeleted(false);

    sut.updateStudent(student);

    Students actual = sut.searchStudent(1);

    Assertions.assertThat(actual.getName()).isEqualTo("testName");
  }

  @Test
  void 受講生コース情報の更新が行えること(){
    StudentCourses studentCourse = new StudentCourses();
    studentCourse.setCourseName("testCourseName");
    studentCourse.setId(1);
    studentCourse.setStartDate(LocalDate.now());
    studentCourse.setEndDate(LocalDate.now().plusYears(1));
    studentCourse.setSId(99822);
    studentCourse.setStatusId(2);

    sut.updateStudentCourse(studentCourse);

    List<StudentCourses> actual = sut.searchStudentCourse(1);

    Assertions.assertThat(actual.getFirst().getCourseName()).isEqualTo("testCourseName");
  }
}