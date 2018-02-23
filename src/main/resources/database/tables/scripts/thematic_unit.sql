CREATE TABLE thematic_unit (
  id                        BIGINT           NOT NULL    AUTO_INCREMENT,
  name                      VARCHAR(250)     NOT NULL,
  serial_number             VARCHAR(250)     NOT NULL,
  description               VARCHAR(500),
  tematic_unit_parent_id    BIGINT,
  course_id                 BIGINT           NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_thematic_unit_parent FOREIGN KEY (tematic_unit_parent_id) REFERENCES thematic_unit(id),
  CONSTRAINT fk_course_tu FOREIGN KEY (course_id) REFERENCES course(id)
);