CREATE DATABASE IF NOT EXISTS mofun
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;
CREATE USER mofun@'%'
  IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON mofun.* TO mofun;


CREATE TABLE mofun.user
(
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_name    VARCHAR(20)  NOT NULL,
  user_pwd     VARCHAR(100) NOT NULL,
  mobile_phone VARCHAR(12)  NOT NULL,
  email        VARCHAR(100),
  create_date  TIMESTAMP,
  status       CHAR(2)
);