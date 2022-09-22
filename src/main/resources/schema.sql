drop table if exists member;
drop table if exists board;
drop table if exists comment;
drop table if exists file;

create table member
(
    id      int  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    uid     varchar2(100) NOT NULL UNIQUE,
    pw      varchar2(100) NOT NULL,
    email   varchar2(100) NOT NULL,
    phone   varchar2(100) NOT NULL,
    grade   varchar2(1) NOT NULL,
    address varchar2(300) NOT NULL,
    name    varchar2(100) NOT NULL,
    role    varchar2(1) NOT NULL,
    enabled varchar2(1) NOT NULL,
    date    DATE NOT NULL
);

create table board
(
    id       int     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    no       int     NOT NULL UNIQUE,
    title    varchar2(200) NOT NULL,
    content  varchar2(4000) NOT NULL,
    date     DATE    NOT NULL,
    category TINYINT NOT NULL,
    views    int     NOT NULL,
    uid       VARCHAR2(100),
        FOREIGN KEY (uid) REFERENCES member(uid) ON UPDATE CASCADE ON DELETE CASCADE
);


