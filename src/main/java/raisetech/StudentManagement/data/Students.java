package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Students {

  private Integer id;
  private String name;
  private String furigana;
  private String nickname;
  private String mailAddress;
  private String livingArea;
  private Integer age;
  private String gender;
  private String remark;
  private Boolean idDeleted;
}
