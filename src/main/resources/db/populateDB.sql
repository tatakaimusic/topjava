DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals(user_id, date_time, description, calories)
VALUES (100000, '2023-04-05 10:00:00.000000+00', 'user meal', 2000),
       (100000, '2023-04-07 15:00:00.000000+00', 'user meal 2', 2000),
       (100000, '2023-04-07 20:00:00.000000+00', 'user meals 2.2', 100),
       (100000, '2023-04-09 20:00:00.000000+00', 'user meal 3', 2000),
       (100001, '2023-04-05 15:00:00.000000+00', 'admin meal', 2000);

