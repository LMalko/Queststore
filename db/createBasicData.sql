
-- create entities of admin, mentor and student
INSERT INTO users (name, surname, login, password, status, group_id, experience)
VALUES ('default', 'student', 'defaultstudent@cc.com', 'student', 'student', 1, 'Adept'),
    ('default', 'mentor', 'defaultmentor@cc.com', 'mentor', 'mentor', 1, null),
    ('default', 'admin', 'defaultadmin@cc.com', 'admin', 'admin', null, null);

--create representational record of CROWDFUND
INSERT INTO crowdfunds (name, total_price, account, founder_id)
VALUES ('Na płyte braci Perdolec', 2000, 20, 1), ('Na kebsa', 1000, 10, 2);

--create representational record of ARTIFACTS
INSERT INTO artifacts (name, price, category)
VALUES ('Yo mama', 2, 'basic'),
('Ma mama', 9999999, 'extra');

--create representational record of QUESTS
INSERT INTO quests (name, reward, category)
VALUES ('Zjeść kebsa', 1000, 'basic'),
('Nie jeść kebsa', 3000, 'extra');

--create representational record of WALLET
INSERT INTO wallet (user_id, current_balance)
VALUES (1, 2500, 3000);

--create representational record of STUDENT_QUESTS
INSERT INTO student_quests (quests_id, student_id, status)
VALUES (1, 1, 'done'),
(2, 1, 'not done');

--create representational record of STUDENT_ARTIFACTS
INSERT INTO student_artifacts (artifact_id, student_id, status)
VALUES (1, 1, 'done'),
(2, 1, 'not done');

--create representational record of GROUPS
INSERT INTO groups (name)
VALUES ('2017.B');

--create representational record of EXPERIENCE_LEVELS
INSERT INTO experience_levels (name, money_required)
VALUES ('Adept', 2000),
('Big Shaq', 4000);
