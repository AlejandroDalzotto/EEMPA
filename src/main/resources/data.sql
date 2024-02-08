USE varano;

INSERT INTO
    rol
VALUES
    (null, 'ROLE_USER'),
    (null, 'ROLE_ADMIN');

INSERT INTO
    user
VALUES
    (
        1,
        'ale@gmail.com',
        'Dalzotto',
        'Alejandro',
        '$2a$10$PzTNVIVNVqTTDIkpylYg9.a8H3A.9La99eHLk.QZ8dhI/6ny5KaW.',
        'alejandro'
    );

INSERT INTO
    user_rol
VALUES
    (1, 1);

INSERT INTO
    module
VALUES
    (1, 1, "primer modulo"),
    (1, 2, "segundo modulo"),
    (1, 3, "tercer modulo"),
    (1, 4, "cuarto modulo"),
    (1, 5, "quinto modulo"),
    (1, 6, "sexto modulo");

INSERT INTO varano.student (`id_student`, `active`, `address`, `birth`, `birth_cert`, `cell_phone`, `course`, `disability`, `dni`, `health`, `last_name`, `legajo`, `line_phone`, `mail`, `matricula`, `name`, `sex`, `study_cert`) VALUES
(1, true, 'Calle 513', '2001-02-27', true, 1252425124, 1, false, 42512355, true, 'Rodrigez', NULL, 912951054, 'pedro@gmail.com', 2023001, 'Pedro', 'M', true),
(2, true, 'Calle 201', '1999-04-02', true, 1560198910, 1, false, 40198501, true, 'García', 1000, 9012490098, 'maria@gmail.com', 2023110, 'María', 'F', true),
(3, true, 'Calle 994', '2010-10-09', true, 1905909128, 3, false, 44123506, true, 'Gutiérrez', 1001, 185928492, 'julian@hotmail.com', 2023454, 'Julián', 'M', true),
(4, true, 'Junín 415', '2002-12-13', true, 12356717123, 3, false, 30124544, true, 'Fernandez', 1002, NULL, 'pao@hotmail.com', 12223, 'Paola', 'F', true),
(5, true, 'Junín 4005', '2001-12-14', true, 12356717123, 2, false, 30124541, true, 'Fernandez', 1003, NULL, 'martina@hotmail.com', 12223, 'Martina', 'F', true),
(6, true, 'Calle nueva 5671', '1995-07-21', true, 5129950012, 4, false, 37180501, true, 'Guerrero', NULL, 5513566461, 'ultima-prueba@gmail.com', 2023690, 'Pedro', 'M', true),
(7, true, 'Santa Fe 4512', '2002-03-27', true, 516167126, 3, false, 43100510, true, 'Ivarra', 1006, 778163246, 'aleivarra@gmail.com', 2023901, 'Alejandro', 'M', true),
(8, true, 'Belgrano 512', '2002-01-01', true, 660012309, 1, false, 42978109, true, 'Herrera', 1007, 5198375189, 'julieta@google.com', 2024008, 'Julieta', 'F', true),
(9, true, 'Saenz Peña 6413', '2000-11-28', true, 5136617, 4, false, 41005150, true, 'Juarez', 1008, 1237671341, 'ariel@hotmail.com', 2024101, 'Ariel', 'M', true),
(10, true, 'Lisandro de la Torre 100', '1996-09-08', true, 4136136, 2, false, 39012950, true, 'Pineda', 1010, 7147147134, 'ferpineda@hotmail.com', 2024914, 'Fernando', 'M', true),
(11, true, 'Italia 907', '1993-08-12', false, 51236172178, 2, false, 34100489, true, 'Gutierrez', 1011, 123612355, 'dai@google.com', 2024661, 'Daiana', 'F', true),
(12, true, 'Velez Sarfienld 881', '1998-05-12', true, 51261261, 5, false, 38001249, true, 'Gomez', NULL, 347141436, 'jimegomez@hotmail.com', 2022991, 'Jimena', 'F', false),
(13, true, 'Juan B. Justo 2142', '1999-03-09', true, 123612361, 5, false, 40012141, true, 'Montés', 1015, 46243262, 'mariana@google.com', 2021121, 'Mariana', 'F', true),
(14, true, 'San Martín 561', '1994-10-15', true, 163471146, 3, false, 38123501, false, 'Maciel', 1016, 357427, 'javier-maciel@gmail.com', 2021935, 'Javier', 'M', true),
(15, true, 'Calle 601', '2003-04-28', true, 512361, 5, false, 43125601, true, 'Garcia', 1017, 54362561, 'sofia-garcia@hotmail.com', 2022519, 'Sofia', 'F', true),
(16, true, 'Alvear 61', '1997-12-09', true, 641371, 5, false, 39135063, true, 'Fernandez', 1018, 5725723111, 'manu-fer@google.com', 2021166, 'Manuel', 'M', true),
(17, true, 'Belgrano 50', '1999-04-05', true, 45147147, 5, false, 40553101, true, 'Navarro', 1020, 67578212324, 'ana-n@google.com', 2020856, 'Ana', 'F', true),
(18, true, 'Dorrego 6022', '1995-04-08', true, 657878671, 5, false, 32059112, true, 'Castillo', 1022, 1235125777, 'elena@gmail.com', 2020686, 'Elena', 'F', true),
(19, true, 'Calle 771', '2002-02-03', false, 526161, 3, false, 39120415, true, 'Marín', 1023, 515125, 'manuela@google.com', 2023148, 'Manuela', 'F', false),
(20, true, 'Calle 10', '1990-07-20', true, 3515102395, 3, false, 31058195, true, 'Rodenas', 1025, NULL, 'paula@hotmail.com', 2021501, 'Paula', 'F', true),
(21, true, 'Liniers 691', '2000-03-12', true, 61298365, 2, false, 40125691, true, 'Ortega', 1026, NULL, 'tati-ortega@gmail.com', 20231462, 'Tatiana', 'F', true);