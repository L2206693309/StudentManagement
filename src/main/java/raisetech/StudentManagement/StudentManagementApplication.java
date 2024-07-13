package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  private String name = "Kazuya Kakuta";
  private String age = "16";
  private static Map<String, String> StudentMap = new HashMap<>();

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
    StudentMap.put("Kazuya", "10");
  }

  @GetMapping("/studentInfo")
  public String studentInfo() {
    return name + " " + age + "歳";
  }

  @GetMapping("/StudentMap")
  public String StudentMap() {
    return StudentMap.get(name);
  }

  @GetMapping("/name")
  public String name() {
    System.out.println(name);
    return this.name;
  }

  @PostMapping("/studentInfo")
  public void studentInfo(String name, String age) {
    this.name = name;
    this.age = age;
  }

  @PostMapping("/name")
  public void name(String name) {
    this.name = name;
  }

  @PostMapping("/StudentMap")
  public void StudentMap(String name, String age) {
    this.name = name;
    this.age = age;
    this.StudentMap.put(name, age);
  }
}
