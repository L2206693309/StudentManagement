package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
@Valid

public class StudentCourses {

  private Integer id;

  private Integer sId;

  @Max(50)
  @NotNull
  private String courseName;

  private LocalDate startDate;

  private LocalDate endDate;

  private Integer statusId;

  private String status;
}
