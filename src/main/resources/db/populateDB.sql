DELETE
FROM vote;
DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM dish;
DELETE
FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurant (name, phone, address, email)
VALUES ('Sholly', '+7(8512)51-60-57', 'ул. Урицкого, д.3', 'upravl_sholi@am-house.ru'),
       ('Izba', '+7(8512)51-81-91', 'ул. Красная Набережная, д.8', 'izba2012@inbox.ru'),
       ('BeerHouse', '+7(8512)54-72-72', 'ул. Савушкина, д.38', 'pivdom@am-house.ru');

INSERT INTO dish (restaurant_id, day, name, price)
VALUES ('100003', '2023-03-09', 'Стейк', 800),
       ('100003', '2023-03-09', 'Винегрет', 150),
       ('100003', '2023-03-09', 'Компот', 50),
       ('100004', '2023-03-09', 'Борщ', 400),
       ('100004', '2023-03-09', 'Плов', 500),
       ('100004', '2023-03-09', 'Оливье', 150),
       ('100005', '2023-03-09', 'Грибной суп', 400),
       ('100005', '2023-03-09', 'Удон', 600),
       ('100005', '2023-03-09', 'Греческий', 200),
       ('100005', '2023-03-10', 'Чай', 50),
       ('100004', '2023-03-10', 'Борщ', 400),
       ('100004', '2023-03-10', 'Плов', 500),
       ('100004', '2023-03-10', 'Оливье', 150),
       ('100004', '2023-03-10', 'Чай', 50);

INSERT INTO vote (user_id, restaurant_id, day, time)
VALUES ('100000', '100003', '2023-03-09', '09:00:00'),
       ('100001', '100004', '2023-03-09', '10:00:00'),
       ('100002', '100005', '2023-03-09', '10:00:00'),
       ('100000', '100004', '2023-03-10', '11:00:00'),
       ('100001', '100004', '2023-03-10', '11:15:00'),
       ('100002', '100004', '2023-03-10', '11:00:00');
