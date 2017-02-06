-- database
SELECT *
FROM pg_database;
-- sequence
SELECT user_id_seq.* FROM user_id_seq;
SELECT nextval('user_id_seq');
SELECT setval('user_id_seq', 1, FALSE);
-- scheam
SHOW search_path;
-- date
SELECT now();

--pagination
SELECT * FROM "user" LIMIT 1 OFFSET 0;