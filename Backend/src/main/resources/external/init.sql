use Icecream;

insert into roles (name) values ("ROLE_ADMIN");
insert into roles (name) values ("ROLE_USER");
insert into roles (name) values ("ROLE_STAFF");

insert into categories (name) values ("Electronics");
insert into categories (name) values ("Mathematics");
insert into categories (name) values ("Computer science");
insert into categories (name) values ("Economics");

insert into users (username, email, password, status) values ("SanDang", "sandang@hotmail.com", "1234", "1");
insert into users (username, email, password, status) values ("JohnDoe", "johndoe@gmail.com", "1234", "1");

insert into user_role (user_id, role_id) values ("1", "1");
insert into user_role (user_id, role_id) values ("2", "1");
insert into user_role (user_id, role_id) values ("2", "2");

insert into user_details (fullname, address, gender, birthday, avatar, user_id) values ("Dang Thai San", "xyz str", "1","2020-01-01", "/my/avatar","1");
insert into user_details (fullname, address, gender, birthday, avatar, user_id) values ("Jane Doe", "xyz str", "0","2020-01-01","/my/avatar2","2");

insert into products (description, image, name, price, status, category_id) values ("Operating system book","/my/image","3 simple pieces of Operating system","3.6","1","1");

insert into feedbacks (content,title,user_id, product_id) values ("this is painfully bad","Bad book!","1","1");
insert into feedbacks (content,title,user_id, product_id) values ("some contents","Good book","2","1");

insert into orders (payment_method,code,status,user_id) values ("cash","E234","1","1"); 

insert into order_details (code,quantity,order_id,product_id) values ("012_Eas34",100,1,1);
