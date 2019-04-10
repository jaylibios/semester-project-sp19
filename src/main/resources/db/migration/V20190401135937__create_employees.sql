create table EMPLOYEE
(
  ID SERIAL NOT NULL
    primary key,
  LAST_NAME VARCHAR(24) not null check (LAST_NAME <> ''),
  FIRST_NAME VARCHAR(24) not null check FIRST_NAME <> '',
  PHONE VARCHAR(12) not null check (PHONE <> ''),
  EMAIL VARCHAR(24) not null unique check (EMAIL <> '')
);