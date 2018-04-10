DELETE FROM dishs;
-- DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM users;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1000),
  ('ROLE_ADMIN', 1001),
  ('ROLE_USER', 1002);

INSERT INTO RESTAURANTS (NAME) values
  ('Стрела'),
  ('Заря');

/*INSERT INTO MENUS ("DATE_TIME", "RESTAURANT_ID") VALUES
  ('2018-04-09 09:00:00', 1000),
  ('2018-04-10 09:00:00', 1000),
  ('2018-04-09 09:00:00', 1001),
  ('2018-04-10 09:00:00', 1001);*/

INSERT INTO DISHS (DATE_TIME, NAME, RESTAURANT_ID, PRICE) VALUES
  ('2018-04-09 09:00:00', 'Каша манная', 1000, 80),
  ('2018-04-09 09:00:00', 'Греческий салат', 1001, 150),
  ('2018-04-10 09:00:00', 'Каша гречневая', 1000, 100),
  ('2018-04-10 09:00:00', 'Омары', 1001, 400)

INSERT INTO VOTES (DATE_TIME, USER_ID, RESTAURANT_ID) VALUES
  ('2018-04-09 09:00:00', 1000, 1000),
  ('2018-04-09 09:00:00', 1001, 1000),
  ('2018-04-09 09:00:00', 1002, 1001),
  ('2018-04-10 09:00:00', 1000, 1001),
  ('2018-04-10 09:00:00', 1001, 1000),
  ('2018-04-10 09:00:00', 1002, 1001)

/*INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2015-05-30 10:00:00', 'Завтрак', 500, 100000),
  ('2015-05-30 13:00:00', 'Обед', 1000, 100000),
  ('2015-05-30 20:00:00', 'Ужин', 500, 100000),
  ('2015-05-31 10:00:00', 'Завтрак', 500, 100000),
  ('2015-05-31 13:00:00', 'Обед', 1000, 100000),
  ('2015-05-31 20:00:00', 'Ужин', 510, 100000),
  ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
  ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100001);*/
