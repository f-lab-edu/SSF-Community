SET foreign_key_checks = 0;
drop table if exists member;
drop table if exists board;
drop table if exists comment;
drop table if exists file;
SET foreign_key_checks = 1;

create table member
(
    id      int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uid     varchar(100) NOT NULL UNIQUE,
    pw      varchar(100) NOT NULL,
    email   varchar(100) NOT NULL,
    phone   varchar(100) NOT NULL,
    grade   varchar(1) NOT NULL,
    address varchar(300) NOT NULL,
    name    varchar(100) NOT NULL,
    role    varchar(1) NOT NULL,
    enabled varchar(1) NOT NULL,
    date    DATETIME DEFAULT (current_time)
) Engine=InnoDB default character set = utf8;
create table board
(
    no       int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title    varchar(200) NOT NULL,
    content  varchar(4000) NOT NULL,
    date    DATETIME DEFAULT (current_time),
    category TINYINT NOT NULL,
    views    int     DEFAULT '0',
    uid       VARCHAR(100),
        FOREIGN KEY (uid) REFERENCES member(uid) ON UPDATE CASCADE ON DELETE CASCADE
)Engine=InnoDB default character set = utf8;

create table comment
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(500) NOT NULL,
    uid VARCHAR(100),
    no int,
    date    DATETIME DEFAULT (current_time),
        FOREIGN KEY (uid) REFERENCES member(uid) ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (no) REFERENCES board(no) ON UPDATE CASCADE ON DELETE CASCADE
)Engine=InnoDB default character set = utf8;


