package raisetech.StudentManagement.Domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.Data.Students;
import raisetech.StudentManagement.Data.StudentsCourses;
import raisetech.StudentManagement.Repository.StudentRepository;

@Getter
@Setter

public class StudentDetail {

  private Students student;
  private List<StudentsCourses> studentsCourses;
}
