use Icecream;

insert into `roles`(`code`) values (`ROLE_ADMIN`);
insert into `roles`(`code`) values (`ROLE_USER`);
insert into `roles`(`code`) values (`ROLE_STAFF`);

insert into `categories`(`name`) values (`Electronics`);
insert into `categories`(`name`) values (`Mathematics`);
insert into `categories`(`name`) values (`Computer science`);
insert into `categories`(`name`) values (`Economics`);

insert into `users`(`username`, `email`, `password`, `status`) values (`SanDang`, `sandang@hotmail.com`, `1234`, `1`);
insert into `users`(`username`, `email`, `password`, `status`) values (`JohnDoe`, `johndoe@gmail.com`, `1234`, `1`);

insert into `user_role`(`username`, `code`) values (`SanDang`, `ROLE_ADMIN`);
insert into `user_role`(`username`, `code`) values (`JohnDoe`, `ROLE_USER`);