package raisetech.StudentManagement.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.StudentManagement.data.Students;
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
  void 受講生詳細の単一検索ができること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/student/999"))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生コース情報の一覧検索ができること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/studentsCourses"))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生詳細の一覧検索ができること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/searchStudents?id=2&name=角田&furigana=Kakuta&nickname=Kazuya&mailAddress=l2206693309&livingArea=日本&age=17&gender=man&remark=備考&isDeleted=0")).andExpect(status().isOk());
  }

  @Test
  void 受講生登録が正常に実行できること() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.post("/registerStudent").contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                          "student" : {
                            "name" : "testName",
                            "furigana" : "testFurigana",
                            "nickname" : "testNickname",
                            "mailAddress" : "testMailAddress",
                            "age" : 99822,
                            "gender" : "testGender",
                            "livingArea" : "testLivingArea",
                            "remark" : "testRemark"
                          },
                          "studentCourseList" : [
                            {
                              "courseName" : "testCourseName"
                            }
                          ]
                        }
                        """
                ))
        .andExpect(status().isOk());
  }

  @Test
  void 受講生更新が正常に実行できること() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.put("/updateStudent").contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "student" : {
                                "id" : 99822,
                                "name" : "testName",
                                "furigana" : "testFurigana",
                                "nickname" : "testNickname",
                                "mailAddress" : "testMailAddress",
                                "livingArea" : "testLivingArea",
                                "age" : 2887,
                                "gender" : "testGender",
                                "remark" : "testRemark",
                                "isDeleted" : 0
                            },
                            "studentCourseList" : [
                        
                            ]
                        }
                        """
                ))
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

    Assertions.assertThat(violations.size()).isEqualTo(7);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.put("/updateStudent").contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "student" : {
                                "id" : "hogehoge",
                                "name" : "testName",
                                "furigana" : "testFurigana",
                                "nickname" : "testNickname",
                                "mailAddress" : "testMailAddress",
                                "livingArea" : "testLivingArea",
                                "age" : 2887,
                                "gender" : "testGender",
                                "remark" : "testRemark",
                                "isDeleted" : 0
                            },
                            "studentCourseList" : [
                        
                            ]
                        }
                        """
                ))
        .andExpect(status().isBadRequest());
  }
}