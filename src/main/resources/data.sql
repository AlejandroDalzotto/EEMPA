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

INSERT INTO
    varano.student (
        active,
        age,
        birth,
        birth_cert,
        course,
        disability,
        dni,
        health,
        study_cert,
        cell_phone,
        id_student,
        legajo,
        line_phone,
        matricula,
        address,
        last_name,
        mail,
        name,
        sex
    )
VALUES
    (
        1,
        21,
        '2002-07-16',
        1,
        1,
        0,
        43958587,
        1,
        1,
        3462698422,
        1,
        4214,
        NULL,
        8387,
        'Garbarino 64',
        'Coronel',
        'coronelmartinernesto@gmail.com',
        'Martín',
        'M'
    ),
    (
        1,
        21,
        '2002-01-27',
        1,
        1,
        0,
        43858587,
        1,
        1,
        3462367806,
        2,
        4214,
        NULL,
        8387,
        '26 de Abril 883',
        'García',
        'garciaayelentamara@gmail.com',
        'Tamara',
        'F'
    );