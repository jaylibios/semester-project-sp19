create table EMPLOYEE (

  ID SERIAL NOT NULL
    primary key,
  LAST_NAME VARCHAR(24) not null,
  FIRST_NAME VARCHAR(24) not null,
  PHONE VARCHAR(12) not null,
  EMAIL VARCHAR(24) not null unique

);

create table JOB_STAGE (

  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  ORDINAL int not null

);

create table CUSTOMER (

  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  STREET_ADDRESS varchar(24) not null check (STREET_ADDRESS <> ''),
  PHONE varchar(12) not null check (PHONE <> '')

);

create table PRODUCT (

  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  DESCRIPTION varchar(255) not null check (DESCRIPTION <> '')

);

create table JOB (

  ID serial not null primary key,
  JOB_NAME varchar(24) not null check (JOB_NAME <> ''),
  DESCRIPTION varchar(255) not null check (DESCRIPTION <> ''),
  PRODUCT_ID int not null,
  JOB_STAGE_ID int not null,
  CUSTOMER_ID int not null,
  foreign key (PRODUCT_ID) references PRODUCT(ID),
  foreign key (JOB_STAGE_ID) references JOB_STAGE(ID),
  foreign key (CUSTOMER_ID) references CUSTOMER(ID)

);

insert into JOB_STAGE(name,ORDINAL) values('Pre-Production',1);
insert into JOB_STAGE(name,ORDINAL) values('Production',2);
insert into JOB_STAGE(name,ORDINAL) values('Close-Out',3);

create table COMPONENT (

  ID serial not null primary key,
  NAME varchar(24) not null check (NAME <> ''),
  DESCRIPTION varchar(255) not null check (DESCRIPTION <> ''),
  WHOLESALE_PRICE decimal not null

);

create table PRODUCT_COMPONENT (

  ID serial not null
    constraint PRODUCT_COMPONENTS_PK
    primary key,
  QUANTITY int not null,
  COMPONENT_ID integer
    constraint PRODUCT_COMPONENTS_COMPONENT_ID
    references COMPONENT,
  PRODUCT_ID integer
    constraint PRODUCT_COMPONENTS_PRODUCT_ID
    references PRODUCT

);