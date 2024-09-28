package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新を行います。
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
   * 受講生の一覧検索です。
   * 全権検索を行うので、条件指定は行いません。
   *
   * @return 受講生一覧
   */
  public List<StudentDetail> students() {
    List<Students> studentList = repository.searchUndeletedStudents();
    List<StudentsCourses> studentsCourseList = repository.searchStudentsCourses();
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

  public Students studentData(Integer id){
    return repository.searchStudent(id);
  }

  /**
   * 受講生検索です。
   * IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します
   * 。
   * @param id 受講生ID
   * @return 受講生
   */
  public StudentDetail searchStudent(Integer id){
    Students students = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourse(students.getId());
    return new StudentDetail(students, studentsCourses);
  }

  public List<StudentsCourses> studentsCourses() {
    return repository.searchStudentsCourses();
  }

  public List<StudentsCourses> studentsCoursesJava() {
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses();
    List<StudentsCourses> returnStudentCourses = new ArrayList<>();
    for (StudentsCourses sc : studentsCourses) {
      if (sc.getCourseName().equals("Javaコース")) {
        returnStudentCourses.add(sc);
      }
    }
    return returnStudentCourses;

  }

  @Transactional
  public StudentDetail newStudent(StudentDetail studentDetail) {
    try {
      repository.registerStudent(studentDetail.getStudent());
      for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
        studentsCourses.setSId(studentDetail.getStudent().getId());
        studentsCourses.setStartDate(LocalDate.now());
        studentsCourses.setEndDate(LocalDate.now().plusYears(1));
        studentsCourses.setId(repository.searchStudentsCoursesMaxId()+1);
        System.out.println(studentsCourses.getId());
        System.out.println(studentsCourses.getStartDate());
        System.out.println(studentsCourses.getEndDate());
        repository.registerStudentsCourses(studentsCourses);
      }
    } catch (RuntimeException e) {
      e.printStackTrace();
      return studentDetail;
    }
    return studentDetail;
  }@Transactional
  public String updateStudent(StudentDetail studentDetail) {
    try {
      repository.updateStudent(studentDetail.getStudent());
      for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
        studentsCourses.setSId(studentDetail.getStudent().getId());
        repository.updateStudentsCourses(studentsCourses);
      }
    } catch (RuntimeException e) {
      e.printStackTrace();
      return "ERROR";
    }
    return "SUCCESS";
  }


}
