DELETE
FROM dishs;
-- DELETE FROM menus;
DELETE
FROM restaurants;
DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM users;

ALTER SEQUENCE USERS_SEQ
RESTART WITH 1000;
ALTER SEQUENCE RESTAURANTS_SEQ
RESTART WITH 1000;
ALTER SEQUENCE DISHS_SEQ
RESTART WITH 1000;
ALTER SEQUENCE VOTES_SEQ
RESTART WITH 1000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('User2', 'user2@yandex.ru', 'password2'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1000),
       ('ROLE_USER', 1001),
       ('ROLE_ADMIN', 1002),
       ('ROLE_USER', 1002);

INSERT INTO RESTAURANTS (NAME)
values ('Стрела'),
       ('Заря');

/*INSERT INTO MENUS ("DATE_TIME", "RESTAURANT_ID") VALUES
  ('2018-04-09 09:00:00', 1000),
  ('2018-04-10 09:00:00', 1000),
  ('2018-04-09 09:00:00', 1001),
  ('2018-04-10 09:00:00', 1001);*/

INSERT INTO DISHS (NAME, RESTAURANT_ID, PRICE)
VALUES ('Каша манная', 1000, 80),
       ('Греческий салат', 1001, 150),
       ('Каша гречневая', 1000, 100),
       ('Омары', 1001, 400);

INSERT INTO VOTES (DATE_TIME, USER_ID, RESTAURANT_ID)
VALUES ('2018-04-09 09:00:00', 1000, 1000),
       ('2018-04-09 09:30:00', 1001, 1000),
       ('2018-04-09 10:00:00', 1002, 1001),
       ('2018-04-10 09:00:00', 1000, 1001),
       ('2018-04-10 09:30:00', 1001, 1000),
       ('2018-04-10 10:00:00', 1002, 1001),
       ('2018-04-11 00:01:00', 1000, 1001),
       ('2018-04-11 10:59:00', 1001, 1000),
       ('2018-04-11 11:01:00', 1002, 1001)

