package raisetech.StudentManagement.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.Controller.Converter.StudentConverter;
import raisetech.StudentManagement.Data.Students;
import raisetech.StudentManagement.Data.StudentsCourses;
import raisetech.StudentManagement.Domain.StudentDetail;
import raisetech.StudentManagement.Service.StudentService;

@RestController
public class StudentController {

  private String name = "Kazuya Kakuta";
  private int age = 16;
  private Map<String, Integer> StudentMap = new HashMap<>();
  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service,StudentConverter converter) {
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


  @PostMapping("/name")
  public void name(String name) {
    this.name = name;
  }

  @PostMapping("/StudentMap")
  public void StudentMap(String name, int age) {
    this.name = name;
    this.age = age;
    this.StudentMap.put(name, age);


  }
}
