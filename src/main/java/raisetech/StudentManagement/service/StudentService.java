package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。 受講生の検索や登録・更新を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。 全権検索を行うので、条件指定は行いません。
   *
   * @return 受講生一覧
   */
  public List<StudentDetail> students() {
    List<Students> studentList = repository.searchUndeletedStudents();
    List<StudentCourses> studentsCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentsCourseList);
  }

  public List<Students> students30() {
    List<Students> students = repository.searchStudents();
    List<Students> returnStudents = new ArrayList<Students>();
    for (Students s : students) {
      if (s.getAge() >= 30 && s.getAge() < 40) {
        returnStudents.add(s);
      }
    }

    return returnStudents;
  }

  public Integer studentsMaxId() {
    return repository.searchStudentsMaxId();
  }

  public Integer studentCoursesMaxId() {
    return repository.searchStudentsCoursesMaxId();
  }

  public Students studentData(Integer id) {
    return repository.searchStudent(id);
  }

  /**
   * 受講生詳細検索です。 IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します 。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  public StudentDetail searchStudent(Integer id) {
    Students students = repository.searchStudent(id);
    List<StudentCourses> studentCours = repository.searchStudentCourse(students.getId());
    return new StudentDetail(students, studentCours);
  }

  public List<StudentCourses> studentsCourses() {
    return repository.searchStudentCourseList();
  }

  public List<StudentCourses> studentsCoursesJava() {
    List<StudentCourses> studentCours = repository.searchStudentCourseList();
    List<StudentCourses> returnStudentCourses = new ArrayList<>();
    for (StudentCourses sc : studentCours) {
      if (sc.getCourseName().equals("Javaコース")) {
        returnStudentCourses.add(sc);
      }
    }
    return returnStudentCourses;

  }

  /**
   * 受講生詳細の登録を行います。 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail 受講生詳細
   * @return 登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail newStudent(StudentDetail studentDetail) {
    try {
      Students student = studentDetail.getStudent();
      repository.registerStudent(student);
      for (StudentCourses studentsCourses : studentDetail.getStudentCourseList()) {
        initStudentsCourse(studentsCourses, student);
        studentsCourses.setId(repository.searchStudentsCoursesMaxId() + 1);
        System.out.println(studentsCourses.getId());
        System.out.println(studentsCourses.getStartDate());
        System.out.println(studentsCourses.getEndDate());
        repository.registerStudentCourse(studentsCourses);
      }
    } catch (RuntimeException e) {
      e.printStackTrace();
      return studentDetail;
    }
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourses 受講生コース情報
   * @param student        受講生
   */
  private void initStudentsCourse(StudentCourses studentCourses, Students student) {
    LocalDate now = LocalDate.now();
    studentCourses.setSId(student.getId());
    studentCourses.setStartDate(now);
    studentCourses.setEndDate(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。 受講生と受講コース情報をそれぞれ更新します。
   *
   * @param studentDetail 受講生詳細
   */
  @Transactional
  public String updateStudent(StudentDetail studentDetail) {
    try {
      repository.updateStudent(studentDetail.getStudent());
      studentDetail.getStudentCourseList().forEach(studentCourses -> {
        studentCourses.setSId(studentDetail.getStudent().getId());
        repository.updateStudentCourse(studentCourses);
      });
    } catch (RuntimeException e) {
      e.printStackTrace();
      return "ERROR";
    }
    return "SUCCESS";
  }


}
