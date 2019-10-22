insert into users values('U001', 'P001', true);
insert into users values('U002', 'P002', false);
insert into users values('U003', 'P003', true);
insert into users values('U004', 'P004', true);
insert into users values('A001', 'P001', true);

insert into authorities values('U001', 'ROLE_USER');
insert into authorities values('U002', 'ROLE_USER');
insert into authorities values('U003', 'ROLE_USER');
insert into authorities values('U003', 'ROLE_ADMIN');
insert into authorities values('U004', 'ROLE_USER');
insert into authorities values('A001', 'ROLE_ADMIN');
