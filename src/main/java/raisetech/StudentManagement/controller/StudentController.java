package raisetech.StudentManagement.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private String name = "Kazuya Kakuta";
  private int age = 16;
  private Map<String, Integer> StudentMap = new HashMap<>();
  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/students")
  public List<StudentDetail> students() {
    List<Students> students1 = service.students();
    List<StudentsCourses> studentsCourses = service.studentsCourses();
    return converter.convertStudentDetails(students1, studentsCourses);
  }

  @GetMapping("/students30")
  public List<Students> students30() {
    return service.students30();
  }

  @GetMapping("/studentsCourses")
  public List<StudentsCourses> studentsCourses() {
    return service.studentsCourses();
  }

  @GetMapping("/studentsCoursesJava")
  public List<StudentsCourses> studentsCoursesJava() {
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

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(Arrays.asList(new StudentsCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }





  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody StudentDetail studentDetail) {
    System.out.println(studentDetail.getStudent().getName());
    System.out.println(studentDetail.getStudent().getFurigana());
    System.out.println(studentDetail.getStudent().getNickname());
    System.out.println(studentDetail.getStudent().getMailAddress());
    System.out.println(studentDetail.getStudent().getLivingArea());
    System.out.println(studentDetail.getStudent().getAge());
    System.out.println(studentDetail.getStudent().getGender());
    System.out.println(studentDetail.getStudent().getRemark());
    studentDetail.getStudent().setId(service.studentsMaxId() + 1);
    service.newStudent(studentDetail);
    return ResponseEntity.ok("登録処理が成功しました。");
  }

  @PostMapping("/updateStudent")
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
