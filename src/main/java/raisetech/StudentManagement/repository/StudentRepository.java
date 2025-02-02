package raisetech.StudentManagement.repository;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.data.Students;


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

  List<Students> findAllStudents();

  /**
   * 部分検索します。
   *
   * @return is_deletedが0のものを除外した検索した受講生情報の一覧
   */

  List<Students> searchUndeletedStudents();

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return 受講生のコース情報(全件)
   **/

  List<StudentCourses> findAllStudentCourseList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param id 受講生ID
   * @return 受講生IDに紐づく受講コース情報
   */

  List<StudentCourses> searchStudentCourse(Integer id);

  List<StudentCourses> searchStudentCourses(ArrayList<Integer> sId);

  /**
   * idの最大値を検索します。
   *
   * @return 受講生テーブルのidの最大値
   */

  Integer searchStudentsMaxId();

  Integer searchStudentsCoursesMaxId();

  /**
   * 受講生の検索を行います。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  Students searchStudent(Integer id);

  /**
   * 受講状況のステータスの取得をします。
   *
   * @param id 対象となる受講生コース情報のstatusId
   */
  String searchStatusOfStudentsCourses(Integer id);

  /**
   * 引数に指定された値を満たすレコードを取得します。
   *
   * @param SQLGrammar 検索条件
   * @return 検索条件を満たす受講生
   */
  List<Students> searchTargetStudents(String[] SQLGrammar);

  /**
   * 受講生を新規登録します。 IDに関しては自動採番を行う。
   *
   * @param students 受講生
   */
  void registerStudent(Students students);

  /**
   * 受講生コース情報を新規登録します。 IDに関しては自動採番を行う。
   *
   * @param studentCourse 受講生コース情報
   */
  void registerStudentCourse(StudentCourses studentCourse);

  /**
   * 受講生を更新します。
   *
   * @param students 受講生
   */
  void updateStudent(Students students);

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentCourse 受講生コース情報
   */
  void updateStudentCourse(StudentCourses studentCourse);
}
