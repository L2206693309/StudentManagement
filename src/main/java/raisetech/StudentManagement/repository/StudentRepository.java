package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentsCourses;


/**
 * 受講生情報を扱うリポジトリ。
 * <p>
 * 全権検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全権検索します。
   *
   * @return 全権検索した受講生情報の一覧
   */

  @Select("SELECT * FROM students")
  List<Students> searchStudents();

  @Select("select * from students_courses")
  List<StudentsCourses> searchStudentsCourses();

  @Select("select max(id) from students")
  Integer searchStudentsMaxId();

  @Insert("insert into students values(#{id}, #{name}, #{furigana}, #{nickname}, #{mailAddress}, #{livingArea}, #{age}, #{gender}, #{remark}, false)")
  void registerStudent(Students students);

  @Insert("insert into students_courses values((select max(id)+1 from students_courses), #{sId}, #{courseName}, #{startDate}, #{endDate})")
  void registerStudentsCourses(StudentsCourses studentsCourses);
}
