INSERT INTO memo (id, title, description, done, updated) VALUES (1, 'memo shopping', 'memo1 description', false, '2018-01-04 12:01:00');
INSERT INTO memo (id, title, description, done, updated) VALUES (2, 'memo job', 'memo2 description', false, '2018-01-04 13:02:10');
INSERT INTO memo (id, title, description, done, updated) VALUES (3, 'memo private', 'memo3 description', false, '2018-01-04 14:03:21');
INSERT INTO memo (id, title, description, done, updated) VALUES (4, 'memo job', 'memo4 description', false, '2018-01-04 15:04:32');
INSERT INTO memo (id, title, description, done, updated) VALUES (5, 'memo private', 'memo5 description', false, '2018-01-04 16:05:43');
INSERT INTO memo (id, title, description, done, updated) VALUES (6, 'memo travel', 'memo6 description', false, '2018-01-04 17:06:54');
INSERT INTO memo (id, title, description, done, updated) VALUES (7, 'memo travel', 'memo7 description', false, '2018-01-04 18:07:05');
INSERT INTO memo (id, title, description, done, updated) VALUES (8, 'memo shopping', 'memo8 description', false, '2018-01-04 19:08:16');
INSERT INTO memo (id, title, description, done, updated) VALUES (9, 'memo private', 'memo9 description', false, '2018-01-04 20:09:27');
INSERT INTO memo (id, title, description, done, updated) VALUES (10,'memo hospital', 'memoA description', false, '2018-01-04 21:10:38');

INSERT INTO `user` (id, `name`, password, email, admin_flag) VALUES (1, 'kamimura', '{bcrypt}$2a$10$yiIGwxNPWwJ3CZ0SGAq3i.atLYrQNhzTyep1ALi6dbax1b1R2Y.cG', 'kkamimura@example.com', TRUE);
INSERT INTO `user` (id, `name`, password, email, admin_flag) VALUES (2, 'sakuma', '{bcrypt}$2a$10$9jo/FSVljst5xJjuw9eyoumx2iVCUA.uBkUKeBo748bUIaPjypbte', 'rsakuma@example.com', FALSE);
INSERT INTO `user` (id, `name`, password, email, admin_flag) VALUES (3, 'yukinaga', '{bcrypt}$2a$10$1OXUbgiuuIi3SOO3t.jyZOEY66ELL03dRcGpAKWql8HBXOag4YZ8q', 'tyukinaga@example.com', FALSE);
INSERT INTO `user` (id, `name`, password, email, admin_flag) VALUES (4, 'sawatari', '{pbkdf2}0963ebe5b7508e9f0de55e7480ee7b87c623754ea18a94f25a20cc213a6341695d6ad38d18ff8f25', 'zsawatari@example.com', TRUE);
INSERT INTO `user` (id, `name`, password, email, admin_flag) VALUES (5, 'hiyama', '{pbkdf2}998f1e8af4662f9c7e44bad5af69e916f0ab6cf6af1a1a38b0e667f5f7b9f5bb0d3700e2eacfcf72', 'ehiyama@example.com', FALSE);


