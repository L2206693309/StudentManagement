package raisetech.StudentManagement.Data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {
  private Integer id;
  private Integer sId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;
}
