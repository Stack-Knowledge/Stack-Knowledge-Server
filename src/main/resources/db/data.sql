insert into user(id, created_at, authority, email, name, profile_image) values('000000', NOW(), 'ROLE_STUDENT', 's22000@gsm.hs.kr', '김주홍', NULL);
insert into student(id, created_at, cumulate_point, current_point, user_id) values('000001', NOW(), 10, 10, '000000');

insert into user(id, created_at, authority, email, name, profile_image) values('000002', NOW(), 'ROLE_STUDENT', 's22001@gsm.hs.kr', '이주홍', NULL);
insert into student(id, created_at, cumulate_point, current_point, user_id) values('000003', NOW(), 15, 15, '000002');

insert into user(id, created_at, authority, email, name, profile_image) values('000004', NOW(), 'ROLE_STUDENT', 's22002@gsm.hs.kr', '최주홍', NULL);
insert into student(id, created_at, cumulate_point, current_point, user_id) values('000005', NOW(), 35, 35, '000004');

insert into user(id, created_at, authority, email, name, profile_image) values('000006', NOW(), 'ROLE_STUDENT', 's22003@gsm.hs.kr', '채주홍', NULL);
insert into student(id, created_at, cumulate_point, current_point, user_id) values('000007', NOW(), 360, 360, '000006');

insert into user(id, created_at, authority, email, name, profile_image) values('000008', NOW(), 'ROLE_STUDENT', 's22004@gsm.hs.kr', '정주홍', NULL);
insert into student(id, created_at, cumulate_point, current_point, user_id) values('000009', NOW(), 9999999, 99, '000008');