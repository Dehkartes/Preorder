CREATE DATABASE IF NOT EXISTS Preorder;

use Preorder;
create table if not exists Member
(
    ID       char(16)             not null
    primary key,
    Email    varchar(128)         null,
    Password varchar(128)         null,
    Verified tinyint(1) default 0 null,
    Name     varchar(128)         null,
    Phone    char(11)             null,
    Address  varchar(128)         null,
    role     varchar(16)          null
    );

create table if not exists Verification
(
    id       varchar(16) not null
    primary key,
    authCode varchar(64) null,
    constraint Verification_User_ID_fk
    foreign key (id) references Member (ID)
    );