package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  @Autowired
  private StudentRepository repository;

  private String name = "Kazuya Kakuta";
  private int age = 16;
  private static Map<String, Integer> StudentMap = new HashMap<>();

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
    StudentMap.put("Kazuya", 10);
  }

  @GetMapping("/student")
  public String student(@RequestParam String name) {
    Student student = repository.searchByName(name);
    return student.getName() + " " + student.getAge() + "歳";
  }

  @GetMapping("/registerStudent")
  public String registerStudent() {
    Student student = repository.searchByName("TarouTanaka");
    return student.getName() + " " + student.getAge() + "歳";
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

  @PostMapping("/student")
  public void student(String name, int age) {
    repository.registerStudent(name, age);
  }

  @PostMapping("/registerStudent")
  public void registerStudent(String name, int age) {
    repository.registerStudent(name, age);
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
  @PatchMapping("/student")
  public void student (int age , String name){
    repository.updateStudent(name,age);
  }

  @DeleteMapping("/student")
  public void deleteStudent(String name){
    repository.deleteStudent(name);
  }
}
