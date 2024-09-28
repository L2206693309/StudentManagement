package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentsCourses;


/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return 受講生一覧(全件)
   */

  @Select("SELECT * FROM students")
  List<Students> searchStudents();

  /**
   * 部分検索します。
   *
   * @return is_deletedが0のものを除外した検索した受講生情報の一覧
   */

  @Select("select * from students where is_deleted = 0")
  List<Students> searchUndeletedStudents();

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return 受講生のコース情報(全件)
   **/

  @Select("select * from students_courses")
  List<StudentsCourses> searchStudentsCourses();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param id 受講生ID
   * @return 受講生IDに紐づく受講コース情報
   */

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
   * 受講生の検索を行います。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  @Select("SELECT * FROM students where id=#{id}")
  Students searchStudent(Integer id);

  @Insert("insert into students values(#{id}, #{name}, #{furigana}, #{nickname}, #{mailAddress}, #{livingArea}, #{age}, #{gender}, #{remark}, false)")
  void registerStudent(Students students);

  @Insert("insert into students_courses values(#{id}, #{sId}, #{courseName}, #{startDate}, #{endDate})")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("update students set name=#{name}, furigana=#{furigana}, nickname=#{nickname}, mail_address=#{mailAddress}, living_area=#{livingArea}, age=#{age}, gender=#{gender}, remark=#{remark}, is_deleted=#{isDeleted} where id = #{id}")
  void updateStudent(Students students);

  @Update("update students_courses set course_name=#{courseName} where id=#{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
