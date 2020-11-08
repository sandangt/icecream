use Icecream;

insert into roles (name) values ("ROLE_ADMIN");
insert into roles (name) values ("ROLE_STAFF");
insert into roles (name) values ("ROLE_USER");

insert into categories (name) values ("Electronics");
insert into categories (name) values ("Mathematics");
insert into categories (name) values ("Computer science");
insert into categories (name) values ("Economics");

insert into users (username, email, password, status) values ("SanDang", "sandang@hotmail.com", "1234", "1");
insert into users (username, email, password, status) values ("JohnDoe", "johndoe@gmail.com", "1234", "1");
insert into users (username, email, password, status) values ("Socrates", "socrates@yahoo.com", "1234", "1");
insert into users (username, email, password, status) values ("DavidBowie", "Bowie@gmail.com", "1234", "1");

insert into user_role (user_id, role_id) values ("1", "1");
insert into user_role (user_id, role_id) values ("1", "2");
insert into user_role (user_id, role_id) values ("2", "1");
insert into user_role (user_id, role_id) values ("3", "3");
insert into user_role (user_id, role_id) values ("4", "2");

insert into user_details (id, firstname, lastname, address, gender, birthday, avatar, user_id) values ("1", "Dang", "San", "xyz str", "MALE","2020-01-01", "/images/user1.jpg","1");
insert into user_details (id, firstname, lastname, address, gender, birthday, avatar, user_id) values ("2", "Jane", "Doe", "xyz str", "FEMALE","2020-01-01","/images/user1.jpg","2");

insert into products (description, image, name, price, status, category_id) values ("Operating system book","/my/image","3 simple pieces of Operating system","3.6","1","1");

insert into feedbacks (content,title,user_id, product_id) values ("this is painfully bad","Bad book!","1","1");
insert into feedbacks (content,title,user_id, product_id) values ("some contents","Good book","2","1");

insert into orders (payment_method,status,user_id) values ("cash","1","1"); 

insert into order_details (quantity,order_id,product_id) values (100,1,1);
