insert into _administrator (administrator_id, username, password, email, full_name, role) VALUES (1, 'george', '$2a$12$dBiYGVlNel2LIMY5sLysyuUVuHT3RqK/7bdHfgFb6jciVSbJ70FCu', 'george@rocc', 'George Hay','ADMINISTRATOR');
insert into _administrator (administrator_id, username, password, email, full_name, role) VALUES (2, 'antonia', '$2a$12$saXMkgys7Bf0FrMFjJH6L.SE1SF10LVfvKnjue7ihJc/diBmbN9li', 'antonia@imiplacerazvan', 'Antonia Zeibel','ADMINISTRATOR');

insert into _employee (employee_id, username, password, email, full_name, role) VALUES (1, 'razvan', '$2a$12$s0m80hVnnO4oxlf9gP1Mne2sowSiLt/BAi51V1ruec4JJUCNIYsxS', 'razvan@linux', 'Razvan Bolundut','EMPLOYEE');
insert into _employee (employee_id, username, password, email, full_name, role) VALUES (2, 'sara', '$2a$12$//NqF4ftG.MUvlfH.pDFeek4ZYDxFOjhop0vlJR/0bkBhTbJY03zG', 'sara@imiplacegeoorge', 'Sara Voicu','EMPLOYEE');

insert into _client (client_id, username, password, email, full_name, role) VALUES (1, 'buddy', '$2a$12$KysOx6/ksJoTEQ95V2dY0uG6./S/3lJN7WiEfsmLgrOYc1CIVxViG', 'buddy@director', 'Buddy Patrick','CLIENT');
insert into _client (client_id, username, password, email, full_name, role) VALUES (2, 'tu', '$2a$12$7uV1aQn5Wnjn2A0ju4RwieUNzn.ekCTAVw3i169w7tZ4nGrwqKcUW', 'tu@eu', 'Tu Eu','CLIENT');

insert into _car (car_id, brand, model, kilometers, price, availability, administrator_id) VALUES (1, 'BMW', 'X1', 120, 1000, 'unavailable', 1);
insert into _car (car_id, brand, model, kilometers, price, availability, administrator_id) VALUES (2, 'AUDI', 'RSQ8', 100, 2000, 'available', 1);

insert into _rental (rental_id, date, car_id, client_id) VALUES (1, '12 mai', 1, 2);

insert into _contract(contract_id, rental_id, signature) VAlUES(1, 1, 'signed');
