select * from category;
select * from item;
select * from album;


-- 사용자 등록

insert into user
values(null, "admin@naver.com", "man", now(), "관리자", "admin");

insert into user
values(null, "test1@naver.com", "man", now(), "테스트1", "test");

insert into user
values(null, "test2@naver.com", "man", now(), "테스트2", "test");

insert into user
values(null, "test3@naver.com", "man", now(), "테스트3", "test");

insert into user
values(null, "test4@naver.com", "man", now(), "테스트4", "test");



-- 카테고리 삽입

insert into category 
values(null, "컴퓨터");

insert into category 
values(null, "댄스");

insert into category 
values(null, "액션");

insert into category 
values(null, "멜로");

insert into category 
values(null, "개그");


-- 아이템 삽입

insert into item
values("책",null,false,'자바의 정석', 1);

insert into item
values("책",null,false,'자바 프로그래밍', 1);

insert into item
values("책",null,false,'Spring 3.0', 1);

insert into item
values("책",null,false,'윤성우의 자료구조', 1);

insert into item
values("음반",null,false,'트와이스', 2);

insert into item
values("음반",null,false,'거북이', 2);

insert into item
values("dvd",null,false,'연평대전', 3);

insert into item
values("dvd",null,false,'멜로영화몰라', 4);

insert into item
values("dvd",null,false,'연금술사', 3);

insert into item
values("dvd",null,false,'염력', 4);

insert into item
values("dvd",null,false,'덤앤더머', 5);


-- 부가정보 삽입

insert into album
values("아이유", 5);

insert into album
values("거북이", 6);

insert into book
values("최성진", 1);

insert into book
values("몰라용", 2);

insert into book
values("최성진", 3);

insert into book
values("몰라용", 4);

insert into dvd
values(80, 7);

insert into dvd
values(92, 8);

insert into dvd
values(100, 9);

insert into dvd
values(92, 10);

insert into dvd
values(100, 11);