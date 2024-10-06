package raisetech.StudentManagement.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class TestException extends Exception{

  public TestException(Throwable cause) {
    super(cause);
  }

  public TestException(String message, Throwable cause) {
    super(message, cause);
  }

  public TestException(String message) {
    super(message);
  }

  public TestException() {
    super();
  }

  @Override
  public void printStackTrace(PrintWriter s) {
    super.printStackTrace(s);
  }

  @Override
  public void printStackTrace() {
    super.printStackTrace();
  }

  @Override
  public void printStackTrace(PrintStream s) {
    super.printStackTrace(s);
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }
}
