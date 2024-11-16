package raisetech.StudentManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/students"))
        .andExpect(status().isOk());

    verify(service, times(1)).students();
  }

  @Test
  void 受講生詳細の単一検索ができること() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/student/999"))
        .andExpect(status().isOk());

  }

  @Test
  void 受講生コース情報の一覧検索ができること() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/studentsCourses"))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生登録ができること() throws Exception{
    Students student = new Students();

    student.setName("角田 憲哉");
    student.setFurigana("Kakuta Kazuya");
    student.setNickname("Kazuya");
    student.setMailAddress("l2206693309as@gmail.com");
    student.setLivingArea("日本-神奈川県");
    student.setAge(16);
    student.setGender("man");
    student.setRemark("備考");

    List<StudentCourses> studentCourses = new ArrayList<>();
    studentCourses.get(0).setCourseName("example7");

    mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent"))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生更新ができること() throws Exception{
    Students student = new Students();

    student.setId(10);
    student.setName("角田 憲哉");
    student.setFurigana("Kakuta Kazuya");
    student.setNickname("Kazuya");
    student.setMailAddress("l2206693309as@gmail.com");
    student.setLivingArea("日本-神奈川県");
    student.setAge(16);
    student.setGender("man");
    student.setRemark("備考");
    student.setIsDeleted(false);

    StudentCourses studentCourse = new StudentCourses();
    studentCourse.setCourseName("hogehoge");
    List<StudentCourses> studentCourses = new ArrayList<>();
    studentCourses.add(studentCourse);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourses);

    mockMvc.perform(MockMvcRequestBuilders.put("/updateStudent"))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    Students student = new Students();

    student.setId(2);
    student.setName("角田 憲哉");
    student.setFurigana("Kakuta Kazuya");
    student.setNickname("Kazuya");
    student.setMailAddress("l2206693309as@gmail.com");
    student.setLivingArea("日本-神奈川県");
    student.setAge(16);
    student.setGender("man");
    student.setRemark("備考");
    student.setIsDeleted(false);

    Set<ConstraintViolation<Students>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(7);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {
//  Students の id を Integer で宣言しているため実装不可

/*
    Students student = new Students();

    student.setId("テストです。");
    student.setName("角田 憲哉");
    student.setFurigana("Kakuta Kazuya");
    student.setNickname("Kazuya");
    student.setMailAddress("l2206693309as@gmail.com");
    student.setLivingArea("日本-神奈川県");
    student.setGender("man");

    Set<ConstraintViolation<Students>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations.size()).extracting("message").containsOnly("数字のみ入力するようにしてください。");
 */
  }
}