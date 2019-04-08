create table EMPLOYEE
(
  ID SERIAL NOT NULL
    primary key,
  LAST_NAME VARCHAR(24) not null,
  FIRST_NAME VARCHAR(24) not null,
  PHONE VARCHAR(12) not null,
  EMAIL VARCHAR(24) not null unique
);