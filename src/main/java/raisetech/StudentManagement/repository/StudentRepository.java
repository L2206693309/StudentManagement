package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

  @Select("select * from students where is_deleted = 0")
  List<Students> searchUndeletedStudents();

  /**
   * 全権検索します。
   *
   * @return 全権検索した受講生コース情報の一覧
   **/

  @Select("select * from students_courses")
  List<StudentsCourses> searchStudentsCourses();

  @Select("select * from students_courses where s_id=#{sId}")
  List<StudentsCourses> searchStudentsCourse(Integer id);

  /**
   * idの最大値を検索します。
   *
   * @return 受講生テーブルのidの最大値
   */

  @Select("select max(id) from students")
  Integer searchStudentsMaxId();

  @Select("select max(id) from students_courses")
  Integer searchStudentsCoursesMaxId();

  /**
   * 引数の値に基づくレコードを検索します。
   *
   * @return 当該idに基づく受講生テーブルのレコード
   */

  @Select("SELECT * FROM students where id=#{id}")
  Students searchStudent(Integer id);

  /**
   *
   *
   */

  @Insert("insert into students values(#{id}, #{name}, #{furigana}, #{nickname}, #{mailAddress}, #{livingArea}, #{age}, #{gender}, #{remark}, false)")
  void registerStudent(Students students);

  @Insert("insert into students_courses values(#{id}, #{sId}, #{courseName}, #{startDate}, #{endDate})")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("update students set name=#{name}, furigana=#{furigana}, nickname=#{nickname}, mail_address=#{mailAddress}, living_area=#{livingArea}, age=#{age}, gender=#{gender}, remark=#{remark}, is_deleted=#{isDeleted} where id = #{id}")
  void updateStudent(Students students);

  @Update("update students_courses set course_name=#{courseName} where id=#{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
