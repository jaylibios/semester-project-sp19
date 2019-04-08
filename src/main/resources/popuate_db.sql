create table EMPLOYEE
(
  ID SERIAL NOT NULL
    primary key,
  LAST_NAME VARCHAR(24) not null,
  FIRST_NAME VARCHAR(24) not null,
  PHONE VARCHAR(12) not null,
  EMAIL VARCHAR(24) not null unique
);

create table CUSTOMER
(
  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  STREET_ADDRESS_LN1 varchar(24) not null check (STREET_ADDRESS_LN1 <> ''),
  STREET_ADDRESS_LN2 varchar(24),
  CITY varchar(24) not null check (CITY <> ''),
  STATE varchar(2) not null check (STATE <> ''),
  ZIP_CODE varchar(5) not null check (ZIP_CODE <> ''),
  PHONE varchar(12) not null check (PHONE <> '')
);

create table JOB (
  ID serial not null primary key,
  JOB_NAME varchar(24) not null check (JOB_NAME <> ''),
  DESCRIPTION varchar(255) not null check (DESCRIPTION <> ''),
  JOB_STAGE_ID int not null,
  CUSTOMER_ID int not null,
  foreign key (JOB_STAGE_ID) references JOB_STAGE(ID),
  foreign key (CUSTOMER_ID) references CUSTOMER(ID)
);

create table JOB_STAGE (
  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  ORDINAL int not null
);