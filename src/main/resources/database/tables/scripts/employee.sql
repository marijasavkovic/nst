CREATE TABLE employee (

  id                        BIGINT           NOT NULL    AUTO_INCREMENT,
  name                      VARCHAR(100)     NOT NULL,
  surname                   VARCHAR (100)    NOT NULL,
  personal_identification_number  VARCHAR (13)     NOT NULL  UNIQUE,
  date_of_birth             DATE             NOT NULL,
  date_of_employment        DATE             NOT NULL,
  address                   VARCHAR (100)    NOT NULL,
  title                     VARCHAR (50)     NOT NULL,
  vocation                  VARCHAR (50)     NOT NULL,

  PRIMARY KEY (id)
);