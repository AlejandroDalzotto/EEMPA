USE varano;

CREATE TABLE
    IF NOT EXISTS rol (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        rol_name VARCHAR(255)
    );

CREATE TABLE
    IF NOT EXISTS user (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        email VARCHAR(255),
        lastName VARCHAR(255),
        name VARCHAR(255),
        password VARCHAR(255),
        username VARCHAR(255)
    );

CREATE TABLE
    IF NOT EXISTS user_rol (
        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        rol_id BIGINT,
        FOREIGN KEY (user_id) REFERENCES user (id),
        FOREIGN KEY (rol_id) REFERENCES rol (id)
    );

CREATE TABLE
    IF NOT EXISTS module (
        id_module BIGINT AUTO_INCREMENT PRIMARY KEY,
        active BOOLEAN,
        name VARCHAR(255)
    );

CREATE TABLE
    IF NOT EXISTS subject (
        id_subject BIGINT AUTO_INCREMENT PRIMARY KEY,
        active BOOLEAN,
        name VARCHAR(255),
        id_module BIGINT,
        FOREIGN KEY (id_module) REFERENCES module (id_module)
    );

CREATE TABLE
    IF NOT EXISTS student (
        id_student BIGINT AUTO_INCREMENT PRIMARY KEY,
        active BOOLEAN,
        address VARCHAR(255),
        age int (11),
        birth DATE,
        birthCert BOOLEAN,
        cellPhone BIGINT,
        course int (11),
        disability BOOLEAN,
        dni int (11),
        health int (11),
        lastName VARCHAR(255),
        legajo BIGINT,
        linePhone int (11),
        mail VARCHAR(255),
        matricula BIGINT,
        sex VARCHAR(5),
        studyCert BOOLEAN
    );

CREATE TABLE
    IF NOT EXISTS student_subject (
        student_subject_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        id_student BIGINT,
        id_subject BIGINT,
        FOREIGN KEY (id_student) REFERENCES student (id_student),
        FOREIGN KEY (id_subject) REFERENCES subject (id_subject)
    );

CREATE TABLE
    IF NOT EXISTS calification (
        id_calification BIGINT AUTO_INCREMENT PRIMARY KEY,
        value int,
        student_subject BIGINT,
        FOREIGN KEY (student_subject) REFERENCES student_subject (student_subject_id)
    );