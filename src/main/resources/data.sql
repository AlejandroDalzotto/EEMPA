
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-04-2024 a las 00:27:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `varano`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `academic_records`
--

CREATE TABLE `academic_records` (
  `id_academic_record` bigint(20) NOT NULL,
  `average` double NOT NULL,
  `referred_comment` varchar(255) DEFAULT NULL,
  `state` enum('GRADUATED','REGULAR','DROPPED_OUT','REPEATER') DEFAULT NULL,
  `study_year` smallint(6) NOT NULL,
  `unique_record_code` varchar(255) NOT NULL,
  `id_course` bigint(20) NOT NULL,
  `id_student` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `academic_records`
--

INSERT INTO `academic_records` (`id_academic_record`, `average`, `referred_comment`, `state`, `study_year`, `unique_record_code`, `id_course`, `id_student`) VALUES
(1, 8, 'comentario de prueba', 'GRADUATED', 2024, '5722b566-18fe-4dbd-a91d-15af56e4e89c', 2, 1),
(2, 0, NULL, 'REGULAR', 2024, 'b291bb8f-393e-4ed5-9e56-3c66e111121c', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `califications`
--

CREATE TABLE `califications` (
  `id_calification` bigint(20) NOT NULL,
  `value` float NOT NULL,
  `id_student` bigint(20) NOT NULL,
  `id_subject` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `califications`
--

INSERT INTO `califications` (`id_calification`, `value`, `id_student`, `id_subject`) VALUES
(1, 8, 1, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `courses`
--

CREATE TABLE `courses` (
  `id_course` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `courses`
--

INSERT INTO `courses` (`id_course`, `active`, `name`) VALUES
(1, b'1', 'Primer Año'),
(2, b'1', 'Segundo Año'),
(3, b'1', 'Tercer Año'),
(4, b'1', 'Cuarto Año'),
(5, b'1', 'Quinto Año');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exams`
--

CREATE TABLE `exams` (
  `id_exam` bigint(20) NOT NULL,
  `exam_date` date NOT NULL,
  `exam_key` varchar(40) NOT NULL,
  `id_module` bigint(20) NOT NULL,
  `id_subject` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `exams`
--

INSERT INTO `exams` (`id_exam`, `exam_date`, `exam_key`, `id_module`, `id_subject`) VALUES
(1, '2024-04-10', 'Primer examen lengua 2024 1er año', 1, 3),
(2, '2024-05-19', 'Examen Lengua 3', 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exam_records`
--

CREATE TABLE `exam_records` (
  `id_exam_record` bigint(20) NOT NULL,
  `attended` bit(1) NOT NULL,
  `grade` float NOT NULL,
  `state` enum('REGULAR','APPROVED','FAILED') DEFAULT NULL,
  `id_exam` bigint(20) NOT NULL,
  `id_student` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `exam_records`
--

INSERT INTO `exam_records` (`id_exam_record`, `attended`, `grade`, `state`, `id_exam`, `id_student`) VALUES
(3, b'1', 10, 'APPROVED', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modules`
--

CREATE TABLE `modules` (
  `id_module` bigint(20) NOT NULL,
  `name` varchar(25) NOT NULL,
  `id_course` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modules`
--

INSERT INTO `modules` (`id_module`, `name`, `id_course`) VALUES
(1, 'Primer módulo (1er Año)', 1),
(2, 'Segundo módulo (1er Año)', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL,
  `rol_name` enum('ROLE_USER','ROLE_ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `rol_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE `students` (
  `id_student` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(55) NOT NULL,
  `birth` date NOT NULL,
  `birth_cert` bit(1) NOT NULL,
  `cell_phone` bigint(20) DEFAULT NULL,
  `disability` bit(1) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `health` bit(1) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `legajo` bigint(20) DEFAULT NULL,
  `line_phone` bigint(20) DEFAULT NULL,
  `mail` varchar(30) NOT NULL,
  `matricula` bigint(20) NOT NULL,
  `name` varchar(25) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `study_cert` bit(1) NOT NULL,
  `id_course` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `students`
--

INSERT INTO `students` (`id_student`, `active`, `address`, `birth`, `birth_cert`, `cell_phone`, `disability`, `dni`, `health`, `last_name`, `legajo`, `line_phone`, `mail`, `matricula`, `name`, `sex`, `study_cert`, `id_course`) VALUES
(1, b'1', 'Junín 2445', '2003-04-28', b'1', 3462386237, b'0', 44809742, b'0', 'Abaca', NULL, NULL, 'andrea.abaca@gmail.com', 2003515, 'Jocelyn Andrea', 'F', b'1', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student_subjects`
--

CREATE TABLE `student_subjects` (
  `student_subject_id` bigint(20) NOT NULL,
  `id_student` bigint(20) DEFAULT NULL,
  `id_subject` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `student_subjects`
--

INSERT INTO `student_subjects` (`student_subject_id`, `id_student`, `id_subject`) VALUES
(7, 1, 5),
(8, 1, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subjects`
--

CREATE TABLE `subjects` (
  `id_subject` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `name` varchar(25) NOT NULL,
  `id_course` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `subjects`
--

INSERT INTO `subjects` (`id_subject`, `active`, `name`, `id_course`) VALUES
(2, b'1', 'Matemática I', 1),
(3, b'1', 'Lengua I', 1),
(4, b'1', 'Lengua II', 2),
(5, b'1', 'Lengua III', 3),
(6, b'1', 'Lengua IV', 4),
(7, b'1', 'Lengua V', 5),
(8, b'1', 'Matemática II', 2),
(9, b'1', 'Matemática III', 3),
(10, b'1', 'Matemática IV', 4),
(11, b'1', 'Matemática V', 5),
(12, b'1', 'Inglés I', 1),
(13, b'1', 'Cs. Sociales I', 1),
(14, b'1', 'Contabilidad I', 1),
(15, b'1', 'Cs. Naturales I', 1),
(16, b'1', 'Inglés II', 2),
(17, b'1', 'Cs. Sociales II', 2),
(18, b'1', 'Contabilidad II', 2),
(19, b'1', 'Cs. Naturales II', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `email`, `lastname`, `name`, `password`, `username`) VALUES
(1, 'ale@gmail.com', 'Dalzotto', 'Alejandro', '$2a$10$n9x5ScwcdyojFaxFxJxh8.M1QwSkXT/Zxv9cuyC35BidpTrcwIq5e', 'alejandro'),
(2, 'waldo123@google.com', 'Cuevas', 'Waldo', '$2a$10$3o.mmioRlWamhmAswoinDu6m4lPZQeoBE48wSlqyM4/ULYCOvDVXG', 'waldo'),
(3, 'test1@gmail.com', 'user', 'test', '$2a$10$1xBsuxcwM71Rvo1zfRy9neZTOar2/VUFmJEcxPfgOO7JFQ50UdPGG', 'test1'),
(4, 'test2@gmail.com', 'user 2', 'test', '$2a$10$HeFzo20KXff0pFu/WFYTZ.NiAOfF7vlLDRPzVcbwhxLZU8lyTKTyW', 'test2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_rol`
--

CREATE TABLE `user_rol` (
  `user_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_rol`
--

INSERT INTO `user_rol` (`user_id`, `rol_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `academic_records`
--
ALTER TABLE `academic_records`
  ADD PRIMARY KEY (`id_academic_record`),
  ADD UNIQUE KEY `UK_3bk4xtu2cbdib8coljlyq9l5p` (`unique_record_code`),
  ADD KEY `FKhl9kwbcxt05nlf0vqcgj1ron2` (`id_course`),
  ADD KEY `FK8otk29ojp0hxom6qlpjua8hq5` (`id_student`);

--
-- Indices de la tabla `califications`
--
ALTER TABLE `califications`
  ADD PRIMARY KEY (`id_calification`),
  ADD KEY `FK50n2nc6qryjxdnt7mteorhwij` (`id_student`),
  ADD KEY `FKgbt0es130bmed8rgqt3rx8wv8` (`id_subject`);

--
-- Indices de la tabla `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id_course`),
  ADD UNIQUE KEY `UK_5o6x4fpafbywj4v2g0owhh11r` (`name`);

--
-- Indices de la tabla `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`id_exam`),
  ADD UNIQUE KEY `UK_p32i2ir1qcown48x4k9peerpd` (`exam_key`),
  ADD KEY `FK9byutl3rij3shdnw6k3kc39g5` (`id_module`),
  ADD KEY `FKb5vhsbjgp1944tr8oeuutty38` (`id_subject`);

--
-- Indices de la tabla `exam_records`
--
ALTER TABLE `exam_records`
  ADD PRIMARY KEY (`id_exam_record`),
  ADD KEY `FKjlx21499jqlwn9k6bky8yy0su` (`id_exam`),
  ADD KEY `FKo49yerxoasul24fb4a9uo69hq` (`id_student`);

--
-- Indices de la tabla `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`id_module`),
  ADD UNIQUE KEY `UK_n5ng23nwasusfsyqk68hx5a05` (`name`),
  ADD KEY `FKoc4d40e5prr69wpw1pdnfjf9` (`id_course`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id_student`),
  ADD UNIQUE KEY `UK_cmyptcbd3gj911vn3hhrq7yh` (`dni`),
  ADD UNIQUE KEY `UK_2cuhd9866qpay0p36d7xxxo12` (`mail`),
  ADD KEY `FKcdfao93lii73776juvyh63k73` (`id_course`);

--
-- Indices de la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  ADD PRIMARY KEY (`student_subject_id`),
  ADD KEY `FKnst3s7nrw06gkjvgqooghdh8o` (`id_student`),
  ADD KEY `FKh2dd37vwfuguverd3jm12vqiy` (`id_subject`);

--
-- Indices de la tabla `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id_subject`),
  ADD KEY `FKoi6icgcu31usp57q450oxtc2d` (`id_course`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user_rol`
--
ALTER TABLE `user_rol`
  ADD PRIMARY KEY (`user_id`,`rol_id`),
  ADD KEY `FKpfraq7jod5w5xd3sxm3m6y1o` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `academic_records`
--
ALTER TABLE `academic_records`
  MODIFY `id_academic_record` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `califications`
--
ALTER TABLE `califications`
  MODIFY `id_calification` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `courses`
--
ALTER TABLE `courses`
  MODIFY `id_course` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `exams`
--
ALTER TABLE `exams`
  MODIFY `id_exam` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `exam_records`
--
ALTER TABLE `exam_records`
  MODIFY `id_exam_record` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `modules`
--
ALTER TABLE `modules`
  MODIFY `id_module` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `students`
--
ALTER TABLE `students`
  MODIFY `id_student` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  MODIFY `student_subject_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id_subject` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `academic_records`
--
ALTER TABLE `academic_records`
  ADD CONSTRAINT `FK8otk29ojp0hxom6qlpjua8hq5` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`),
  ADD CONSTRAINT `FKhl9kwbcxt05nlf0vqcgj1ron2` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id_course`);

--
-- Filtros para la tabla `califications`
--
ALTER TABLE `califications`
  ADD CONSTRAINT `FK50n2nc6qryjxdnt7mteorhwij` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`),
  ADD CONSTRAINT `FKgbt0es130bmed8rgqt3rx8wv8` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id_subject`);

--
-- Filtros para la tabla `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `FK9byutl3rij3shdnw6k3kc39g5` FOREIGN KEY (`id_module`) REFERENCES `modules` (`id_module`),
  ADD CONSTRAINT `FKb5vhsbjgp1944tr8oeuutty38` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id_subject`);

--
-- Filtros para la tabla `exam_records`
--
ALTER TABLE `exam_records`
  ADD CONSTRAINT `FKjlx21499jqlwn9k6bky8yy0su` FOREIGN KEY (`id_exam`) REFERENCES `exams` (`id_exam`),
  ADD CONSTRAINT `FKo49yerxoasul24fb4a9uo69hq` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`);

--
-- Filtros para la tabla `modules`
--
ALTER TABLE `modules`
  ADD CONSTRAINT `FKoc4d40e5prr69wpw1pdnfjf9` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id_course`);

--
-- Filtros para la tabla `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `FKcdfao93lii73776juvyh63k73` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id_course`);

--
-- Filtros para la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  ADD CONSTRAINT `FKh2dd37vwfuguverd3jm12vqiy` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id_subject`),
  ADD CONSTRAINT `FKnst3s7nrw06gkjvgqooghdh8o` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`);

--
-- Filtros para la tabla `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `FKoi6icgcu31usp57q450oxtc2d` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id_course`);

--
-- Filtros para la tabla `user_rol`
--
ALTER TABLE `user_rol`
  ADD CONSTRAINT `FK6c4lt3635uhrcn2lxcf5edl92` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKpfraq7jod5w5xd3sxm3m6y1o` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
