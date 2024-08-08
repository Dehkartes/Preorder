CREATE DATABASE IF NOT EXISTS Preorder;

use Preorder;

create table if not exists WishList
(
    ID          int auto_increment
        primary key,
    `Member.ID` char(16)      null,
    ItemList    varchar(1024) null
);

create table if not exists Payment
(
    ID            int auto_increment
        primary key,
    `WishList.ID` int                                                 null,
    Date          date                                                null,
    Status        enum ('주문완료', '배송중', '배송완료', '주문취소', '반품중', '반품완료') null,
    constraint Order_WishList_ID_fk
        foreign key (`WishList.ID`) references WishList (ID)
);