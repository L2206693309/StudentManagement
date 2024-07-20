package raisetech.StudentManagement;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM student WHERE name = #{name}")
  Student searchByName(String name);

  @Insert("insert into student values(#{name},#{age})")
  void registerStudent(String name,int age);

  @Update("update student set age = #{age} where name = #{name}")
  void updateStudent(String name,int age);

  @Delete("delete from studet where name = #{name}")
  void deleteStudent(String name);
}
