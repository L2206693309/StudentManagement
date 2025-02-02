package raisetech.StudentManagement.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.TestException;
import raisetech.StudentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @Mock
  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    // 事前準備
    List<Students> studentList = new ArrayList<>();
    StudentDetail studentDetail = new StudentDetail();
    List<StudentCourses> studentCourseList = new ArrayList<>();
    studentDetail.setStudentCourseList(studentCourseList);
    when(repository.findAllStudents()).thenReturn(studentList);
    when(repository.findAllStudentCourseList()).thenReturn(studentCourseList);

    // 実行
    List<StudentDetail> actual = sut.students();

    // 検証
    verify(repository, times(1)).findAllStudents();
    verify(repository, times(1)).findAllStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生の単一検索_リポジトリの処理が適切に呼び出せていること() {
    Students student = new Students();
    student.setId(1);
    when(repository.searchStudent(student.getId())).thenReturn(student);

    StudentDetail actual = sut.searchStudent(1);

    verify(repository, times(1)).searchStudent(student.getId());
  }

  @Test
  void 受講生の条件付き一覧検索_リポジトリの処理が適切に呼び出せていること(){
    Students targetStudent = new Students();
    targetStudent.setName("name3");
    
    Students returnStudent = new Students();
    returnStudent.setId(3);
    returnStudent.setName("name3");
    returnStudent.setFurigana("furigana3");
    returnStudent.setNickname("nickname3");
    returnStudent.setMailAddress("mailAddress3");
    returnStudent.setGender("gender3");
    returnStudent.setLivingArea("livingArea3");
    returnStudent.setRemark("remark3");
    returnStudent.setIsDeleted(false);
    
    List<Students> returnStudents = new ArrayList<>();
    returnStudents.add(returnStudent);
    String[] SQLGrammar = new String[10];
    SQLGrammar[1] = "name3";

    when(repository.searchTargetStudents(SQLGrammar)).thenReturn(returnStudents);

    List<StudentDetail> actual = sut.searchStudents(targetStudent);

    verify(repository, times(1)).searchTargetStudents(SQLGrammar);
  }

  @Test
  void 受講生登録_リポジトリに適切に情報を渡せていること() {
    Students student = new Students();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);

    StudentDetail actual = sut.newStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
  }

  @Test
  void 受講生更新_リポジトリに適切に情報を渡せていること() {
    Students student = new Students();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);

    String actual = sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(studentDetail.getStudent());
  }

  @Test
  void 受講コース情報_開始日時と終了日時が適切に設定されていること() throws Exception {
    Students student = new Students();
    Integer studentId = 1;
    student.setId(studentId);
    StudentCourses studentCourse = new StudentCourses();
    LocalDate now = LocalDate.now();
    Integer day = now.getDayOfMonth();
    Integer testYear = now.getYear() + 1;

    sut.initStudentsCourse(studentCourse, student);

    if (day.equals(studentCourse.getStartDate().getDayOfMonth())) {
      if ((testYear).equals(studentCourse.getEndDate().getYear())) {
        System.out.println("SUCCESS");
      } else {
        throw new TestException();
      }
    } else {
      throw new TestException();
    }
  }
}