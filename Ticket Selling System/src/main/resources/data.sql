insert into _band (id, name, genre) VALUES (1, 'rocc neboon', 'SexPulaPistol');
insert into _band (id, name, genre) VALUES (2, 'violent groove metal', 'Crimena');
insert into _band (id, name, genre) VALUES (3, 'death metal', 'Obituray');
insert into _band (id, name, genre) VALUES (4, 'gothic metal', 'Type O Negative');
insert into _band (id, name, genre) VALUES (5, 'indie rock', 'Sorority Noise');

insert into _user(id, password, role, username) VALUES (1, '$2a$12$njmYqBhThXTzgMj/q3Ql8.4oIVwpDYzl0CmM4HgAlE.rIqJKqwrba', 'ADMIN', 'admin');
insert into _user(id, password, role, username) VALUES (2, '$2a$12$MBBs7XZxoirm5w4TQNSF6u354yeVw5BgYInYEBnXFG5LTdMvGNEkS', 'CASHIER', 'antonica');
insert into _user(id, password, role, username) VALUES (3, '$2a$12$00D84NdYccosAah3.d/zyuBCSjAhzxHl/ZCczmvitLEpZcYq7EH.y', 'CASHIER', 'rufi');
insert into _user(id, password, role, username) VALUES (4, '$2a$12$TnK9bIk2GwFVcox8Vvv7yO2rURoJdmQJt2u0Pa6qfmgxyaOsJ/82i', 'CASHIER', 'george');
insert into _user(id, password, role, username) VALUES (5, '$2a$12$TWR/efN0W4N1Lga2Sjtbe.2r4z3/bVyzX2bRVO9PoweHu2gSo/IIO', 'CASHIER', 'noaptebuna');

insert into _concert (id, name, tickets, date) VALUES (1, 'Form Space', 200, '18 aprilie');
insert into _concert (id, name, tickets, date) VALUES (2, 'Ziua Antoniei', 35, '20 aprilie');
insert into _concert (id, name, tickets, date) VALUES (3, 'Ziua Sarei', 0, '14 februarie');
insert into _concert (id, name, tickets, date) VALUES (4, 'Wacken', 35, '1 iulie');
insert into _concert (id, name, tickets, date) VALUES (5, 'Oilor', 35, ' la pastele cailor');

insert into _ticket (id, concert_id, person_number, price) VALUES (1, 1, 1, 200);
insert into _ticket (id, concert_id, person_number, price) VALUES (2, 1, 2, 350);
insert into _ticket (id, concert_id, person_number, price) VALUES (3, 1, 3, 450);
insert into _ticket (id, concert_id, person_number, price) VALUES (4, 2, 1, 500);
insert into _ticket (id, concert_id, person_number, price) VALUES (5, 3, 2, 150);