package raisetech.StudentManagement.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentCourses;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

  private Students student;
  private List<StudentCourses> studentCourseList;
}
