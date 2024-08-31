package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Students;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Students> students() {
    return repository.searchStudents();
  }

  public List<Students> students30() {
    List<Students> students = repository.searchStudents();
    List<Students> returnStudents = new ArrayList<Students>();
    for (Students s : students) {
      if (s.getAge() >= 30 && s.getAge() < 40) {
        returnStudents.add(s);
      }
    }



    return returnStudents;
  }

  public Integer studentsMaxId(){
    return repository.searchStudentsMaxId();
  }

  public List<StudentsCourses> studentsCourses() {
    return repository.searchStudentsCourses();
  }

  public List<StudentsCourses> studentsCoursesJava() {
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses();
    List<StudentsCourses> returnStudentCourses = new ArrayList<>();
    for (StudentsCourses sc : studentsCourses) {
      if (sc.getCourseName().equals("Javaコース")) {
        returnStudentCourses.add(sc);
      }
    }
    return returnStudentCourses;

  }

  @Transactional
  public String newStudent(StudentDetail studentDetail) {
    try{
      repository.registerStudent(studentDetail.getStudent());
      for(StudentsCourses studentsCourses : studentDetail.getStudentsCourses()){
        studentsCourses.setSId(studentDetail.getStudent().getId());
        studentsCourses.setStartDate(LocalDate.now());
        studentsCourses.setEndDate(LocalDate.now().plusYears(1));
        repository.registerStudentsCourses(studentsCourses);
      }
    }
    catch (RuntimeException e){
      e.printStackTrace();
      return "ERROR";
    }
    return "SUCCESS";
  }}
