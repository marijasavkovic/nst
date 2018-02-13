CREATE TABLE lecturer (
  id                 BIGINT           NOT NULL    AUTO_INCREMENT,
  course_id           BIGINT           NOT NULL,
  teaching_type_id     BIGINT           NOT NULL,
  employee_id         BIGINT           NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course(id),
  CONSTRAINT fk_teaching_type FOREIGN KEY (teaching_type_id) REFERENCES teaching_type(id),
  CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
