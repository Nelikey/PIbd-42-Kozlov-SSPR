CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE district (
  id BIGINT NOT NULL,
   description VARCHAR(255),
   CONSTRAINT pk_district PRIMARY KEY (id)
);

CREATE TABLE doctor (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_doctor PRIMARY KEY (id)
);

CREATE TABLE patient (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   phone_number VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_patient PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT NOT NULL,
   login VARCHAR(64) NOT NULL,
   password VARCHAR(64) NOT NULL,
   role INT,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE doctor ADD CONSTRAINT FK_DOCTOR_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);

ALTER TABLE patient ADD CONSTRAINT FK_PATIENT_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE district (
  id BIGINT NOT NULL,
   description VARCHAR(255),
   CONSTRAINT pk_district PRIMARY KEY (id)
);

CREATE TABLE doctor (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_doctor PRIMARY KEY (id)
);

CREATE TABLE patient (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   phone_number VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_patient PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT NOT NULL,
   login VARCHAR(64) NOT NULL,
   password VARCHAR(64) NOT NULL,
   role INT,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE doctor ADD CONSTRAINT FK_DOCTOR_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);

ALTER TABLE patient ADD CONSTRAINT FK_PATIENT_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE district (
  id BIGINT NOT NULL,
   description VARCHAR(255),
   CONSTRAINT pk_district PRIMARY KEY (id)
);

CREATE TABLE doctor (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_doctor PRIMARY KEY (id)
);

CREATE TABLE patient (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   phone_number VARCHAR(255),
   district_id BIGINT,
   CONSTRAINT pk_patient PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT NOT NULL,
   login VARCHAR(64) NOT NULL,
   password VARCHAR(64) NOT NULL,
   role INT,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE doctor ADD CONSTRAINT FK_DOCTOR_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);

ALTER TABLE patient ADD CONSTRAINT FK_PATIENT_ON_DISTRICT FOREIGN KEY (district_id) REFERENCES district (id);