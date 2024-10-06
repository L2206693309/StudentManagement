package raisetech.StudentManagement.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import raisetech.StudentManagement.exception.TestException;

@ControllerAdvice
@Component
public class ExceptionHandlerForTestException {

  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

}
