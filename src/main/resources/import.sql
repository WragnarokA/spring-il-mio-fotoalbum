INSERT INTO photos ( description, title, url, visibile) VALUES('Beautiful', 'Machu Picchu', 'https://tinyurl.com/ys2b9rzh', 1);
INSERT INTO photos ( description, title, url, visibile) VALUES('Marvelous', 'Lines to Nasca', 'https://tinyurl.com/33wxuy72', 1);
INSERT INTO photos ( description, title, url, visibile) VALUES('Beautiful', 'Lake Titicaca', 'https://tinyurl.com/4yxm5w4u', 1);
INSERT INTO photos ( description, title, url, visibile) VALUES('Beautiful', 'Amazons of Perù', 'https://tinyurl.com/mr29w8bv', 1);
INSERT INTO photos ( description, title, url, visibile) VALUES('Beautiful', '7 color mountain', 'https://tinyurl.com/562mb9yc', 1);

INSERT INTO categories (name) VALUES('Landscapes');
INSERT INTO categories (name) VALUES('Portraits');
INSERT INTO categories (name) VALUES('Architecture');
INSERT INTO categories (name) VALUES('Still life');
INSERT INTO categories (name) VALUES('Events');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, password, registered_ad) VALUES('will@gmail.com', 'Will', 'Smith', '{noop}will', '2023-12-15');
INSERT INTO users (email, first_name, last_name, password, registered_ad) VALUES('maria@gmail.com', 'Maria', 'Dibi', '{noop}maria', '2023-12-25');

INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES ('1','1')
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES ('1','2')
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES ('2','2')



