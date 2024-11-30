CREATE TABLE IF NOT EXISTS students
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  furigana VARCHAR(30) NOT NULL,
  nickname VARCHAR(30),
  mail_address VARCHAR(100) NOT NULL,
  living_area VARCHAR(30) NOT NULL,
  age INT,
  gender VARCHAR(10) NOT NULL,
  remark VARCHAR(100),
  is_deleted boolean,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS students_courses
(
  id INT NOT NULL AUTO_INCREMENT,
  s_id INT NOT NULL,
  course_name VARCHAR(50) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  status_id INT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS status_of_students_courses
(
  id INT NOT NULL,
  status VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);