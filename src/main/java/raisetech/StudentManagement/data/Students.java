package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
@Valid

public class Students {

  private Integer id;

  @Max(30)
  @NotNull
  private String name;

  @Max(30)
  @NotNull
  private String furigana;

  @Max(30)
  private String nickname;

  @Max(100)
  @NotNull
  private String mailAddress;

  @Max(30)
  @NotNull
  private String livingArea;

  @NotNull
  private Integer age;

  @Max(10)
  @NotNull
  private String gender;

  @Max(100)
  private String remark;

  private Boolean isDeleted;

}
