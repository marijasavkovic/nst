CREATE TABLE user (
  id                 BIGINT           NOT NULL    AUTO_INCREMENT,
  username           VARCHAR(250)     NOT NULL    UNIQUE,
  password           VARCHAR (250)    NOT NULL,
  role               VARCHAR (50)     NOT NULL,
  email              VARCHAR (100)    NOT NULL,
  employee_id        BIGINT           NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user FOREIGN KEY (employee_id) REFERENCES employee(id)
);
