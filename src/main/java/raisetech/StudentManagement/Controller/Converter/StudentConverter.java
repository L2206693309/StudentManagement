package raisetech.StudentManagement.Controller.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.Data.Students;
import raisetech.StudentManagement.Data.StudentsCourses;
import raisetech.StudentManagement.Domain.StudentDetail;

@Component
public class StudentConverter {
  public List<StudentDetail> convertStudentDetails(List<Students> students1,
      List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students1.forEach(s -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(s);
      List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
          .filter(sc -> s.getId().equals(sc.getSId())).collect(Collectors.toList());
      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
