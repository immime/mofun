-- user
CREATE USER root WITH SUPERUSER
  PASSWORD 'root'
  CREATEDB
  CREATEROLE
  LOGIN;
-- schema
-- schema

ALTER DATABASE plan SET SEARCH_PATH TO public;
-- CREATE SCHEMA mofun;
-- GRANT ALL ON SCHEMA mofun TO root;

-- database
CREATE DATABASE plan WITH ENCODING = 'UTF8' OWNER = "root";
-- tables
CREATE TABLE public."user"
(
  id           SERIAL PRIMARY KEY,
  user_name    VARCHAR(20)  NOT NULL,
  user_pwd     VARCHAR(100) NOT NULL,
  mobile_phone VARCHAR(12)  NOT NULL,
  email        VARCHAR(100),
  create_date  TIMESTAMP,
  status       VARCHAR(2)
);

-- schema
GRANT ALL ON SCHEMA public TO root;
SET SEARCH_PATH TO public;
ALTER DATABASE plan SET SEARCH_PATH TO public;