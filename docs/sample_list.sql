select * from category;
select * from item;
select * from album;

insert into category 
values(null, "컴퓨터");

insert into category 
values(null, "발라드");

insert into category 
values(null, "액션");

insert into category 
values(null, "멜로");

insert into item
values("책",null,false,'자바의 정석', 1);

insert into item
values("책",null,false,'자바 프로그래밍', 1);

insert into item
values("음반",null,false,'아이유', 2);

insert into item
values("음반",null,false,'거북이', 2);

insert into item
values("dvd",null,false,'연평대전', 3);

insert into item
values("dvd",null,false,'멜로영화몰라', 4);

insert into album
values("아이유", 3);

insert into album
values("거북이", 4);

insert into book
values("최성진", 1);

insert into book
values("몰라용", 2);

insert into dvd
values(80, 5);

insert into dvd
values(92, 6);