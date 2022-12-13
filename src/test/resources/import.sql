INSERT INTO roles (id,name) VALUES (1,'ROLE_USER');
INSERT INTO roles (id,name) VALUES (2,'ROLE_ADMIN');
insert into users (password, username, id) values ('$2a$10$FSZqargaN7eYPBkFnCVoU.EtY0whv8o.6S82AcJpZ0oZp5/ePa.Su', 'admin', 1);
insert into user_roles (user_id, role_id) values (1, 2);
insert into user_roles (user_id, role_id) values (1, 1);
insert into long_url (long_url, shortened_times, visited_time, id) values ('www.google.com', 1, 0, 1);
insert into visit (fk_long_url, fk_user) values (1,1);