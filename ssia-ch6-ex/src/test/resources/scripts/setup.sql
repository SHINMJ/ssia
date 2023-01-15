insert into authority(id, name)
values (1, 'admin');

insert into authority(id, name)
values (2, 'manager');

insert into user_table(id, username, password)
values (1, 'test1', '1234');

insert into user_table(id, username, password)
values (2, 'test2', '1234');

insert into user_authority(id, user_id, authority_id)
values (1, 1, 1);

insert into user_authority(id, user_id, authority_id)
values (2, 2, 2);