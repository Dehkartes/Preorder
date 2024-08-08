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

create table if not exists Product
(
    ID     int(16) auto_increment
    primary key,
    Stock  int           null,
    Price  int           null,
    Detail varchar(1024) null,
    Hide   tinyint(1)    null
    );