package raisetech.StudentManagement.controller;

import jakarta.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.TestException;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新など行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private String name = "Kazuya Kakuta";
  private int age = 16;
  private Map<String, Integer> StudentMap = new HashMap<>();
  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細一覧検索です。 全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧(全件)
   */

  @GetMapping("/students")
  public List<StudentDetail> students() {
      return service.students();
  }

  @GetMapping("/studentsE")
  public List<StudentDetail> studentsE() throws Exception {
    throw new TestException("TestException発生");
  }

  /**
   * 受講生詳細検索です。 IDに紐づく任意の受講生を取得します。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  @GetMapping("/student/{id}")
  public StudentDetail updateStudent(@PathVariable @Min(1) Integer id) {
    return service.searchStudent(id);
  }


  @GetMapping("/students30")
  public List<Students> students30() {
    return service.students30();
  }

  @GetMapping("/studentsCourses")
  public List<StudentCourses> studentsCourses() {
    return service.studentsCourses();
  }

  @GetMapping("/studentsCoursesJava")
  public List<StudentCourses> studentsCoursesJava() {
    return service.studentsCoursesJava();
  }

  @GetMapping("/StudentMap")
  public String StudentMap() {
    return String.valueOf(StudentMap.get(name));
  }

  @GetMapping("/name")
  public String name() {
    System.out.println(name);
    return this.name;
  }

  /**
   * 受講生詳細の登録を行います。
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */

  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    System.out.println(studentDetail.getStudent().getName());
    System.out.println(studentDetail.getStudent().getFurigana());
    System.out.println(studentDetail.getStudent().getNickname());
    System.out.println(studentDetail.getStudent().getMailAddress());
    System.out.println(studentDetail.getStudent().getLivingArea());
    System.out.println(studentDetail.getStudent().getAge());
    System.out.println(studentDetail.getStudent().getGender());
    System.out.println(studentDetail.getStudent().getRemark());
    studentDetail.getStudent().setId(service.studentsMaxId() + 1);
    StudentDetail responseStudentDetail = service.newStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。 キャンセルフラグの更新もここで行います(論理削除)
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    System.out.println(studentDetail.getStudent().getId());
    System.out.println(studentDetail.getStudent().getName());
    System.out.println(studentDetail.getStudent().getFurigana());
    System.out.println(studentDetail.getStudent().getNickname());
    System.out.println(studentDetail.getStudent().getMailAddress());
    System.out.println(studentDetail.getStudent().getLivingArea());
    System.out.println(studentDetail.getStudent().getAge());
    System.out.println(studentDetail.getStudent().getGender());
    System.out.println(studentDetail.getStudent().getRemark());
    System.out.println(studentDetail.getStudent().getIsDeleted());
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}
