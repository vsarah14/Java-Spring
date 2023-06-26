insert into _teacher (teacher_id, username, password, email, full_name, role) VALUES (1, 'mircea', '$2a$12$0/gRABHyFvzKHwi9P0.M.uLlhXyDMbXaWdhbSFspRW5QcDS.Uc.zC', 'mircea@ok', 'Mircea', 'TEACHER');
insert into _teacher (teacher_id, username, password, email, full_name, role) VALUES (2, 'richard', '$2a$12$BErQ8/eePk9WhUjd.HGXgOTpejdgsnxrXu/NsCNbXNuiyjhLMH4Ma', 'richi@', 'Richard ', 'TEACHER');

insert into _student (student_id, username, password, email, full_name, group_nr, hobby, role) VALUES (1, 'sara', '$2a$12$uXWD4mzo4VGDr8konnvDIuxAb2/V2lDWoiYXFEbkHq4NupZ0MTSVm', 'sara@imiplacegeorge', 'Sara Voicu',1, 'tennis', 'STUDENT');
insert into _student (student_id, username, password, email, full_name, group_nr, hobby, role) VALUES (2, 'antonia', '$2a$12$VzW8HB0vuTXlJ72l7YAyJOzwpUG.a/7tlPdWmdSCCcmfIbum/JR9m', 'antonia@imiplacerazvan', 'Antonia Zeibel',2, 'hiking', 'STUDENT');

insert into _submission (id, student_name, assignment_name, grade) VALUES (1, 'Zeibel Antonia', 'Polynomial Calculator', 10);
insert into _submission (id, student_name, assignment_name, grade) VALUES (2, 'Voicu Sara', 'SD - Assignment 2', 10);

insert into _laboratory (laboratory_id, number, date, title, description, teacher_id) VALUES (1, 1, '14 mai', 'Computer Networks', 'description1', 1);
insert into _laboratory (laboratory_id, number, date, title, description, teacher_id) VALUES (2, 3, '16 mai', 'Design Patterns', 'description2', 2);

insert into _assignment (assignment_id, name, deadline, description, laboratory_id) VALUES (1, 'Polynomial Calculator', '20 aprilie', 'description1',1);
insert into _assignment (assignment_id, name, deadline, description, laboratory_id) VALUES (2, 'SD - Assignment 2', '21 aprilie', 'description2',2);

insert into _attendance (id, student_id) VALUES (1, 2);
insert into _attendance (id, student_id) VALUES (2, 1);
