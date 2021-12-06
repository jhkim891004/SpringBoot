# Local 세팅 가이드
### Local 작업 환경을 만들기 위한 가이드
1. STS 설치
> 개발툴은 자신에게 잘 맞는 툴로 자유롭게 사용 
- [STS 다운로드 링크](https://spring.io/tools)
2. JDK 11 설치
- 기존에 사용하는 11 버전이 있으면 그대로 사용
- 없으면 해당 [링크](https://www.oracle.com/kr/java/technologies/javase/jdk11-archive-downloads.html) 접속하여 설치
  - 하단 [Windows x64 Compressed Archive] 다운로드 하여 STS 전용으로 별도로 구축
3. MySQL Server Community 버전 설치
- [다운로드 링크](https://dev.mysql.com/downloads/installer/)
4. 데이터베이스 생성 및 유저 생성
```mysql
use mysql;
-- 사용자 생성
create user cndfactory@localhost identified by '1234';
-- database 생성
create database spring default character set utf8;
-- 사용자 권한 부여
grant all privileges on spring.* to cndfactory@locahost;
```
5. 테이블 생성
```mysql
/* 게시글 테이블 */
create table tb_board
(
    ID       int auto_increment comment '식별키',
    MENU_ID  int                    not null comment '메뉴 식별키',
    TITLE    varchar(200)           not null comment '제목',
    CONTENT  text                   null comment '내용',
    USE_YN   varchar(1) default 'Y' not null comment '사용여부',
    REG_DATE datetime               not null comment '작성일시',
    REG_ID   varchar(50)            not null comment '작성자ID',
    MOD_DATE datetime               not null comment '수정일시',
    MOD_ID   varchar(50)            not null comment '수정자ID',
    constraint TB_BOARD_ID_uindex unique (ID desc)
) comment '게시판';

create index TB_BOARD_CATEGORY_ID_index
    on tb_board (MENU_ID);

create index TB_BOARD_REG_DATE_index
    on tb_board (REG_DATE desc);

create index TB_BOARD_REG_ID_index
    on tb_board (REG_ID);

alter table tb_board
    add primary key (ID);

/* 메뉴 테이블 */
create table tb_menu
(
    ID        int auto_increment comment '식별키',
    PARENT_ID int                    null comment '상위메뉴 식별키',
    MENU_NAME varchar(100)           null comment '메뉴명',
    SEQ       int                    null comment '메뉴 순서',
    USE_YN    varchar(1) default 'Y' null comment '사용여부',
    REG_DATE  datetime               not null comment '작성일시',
    REG_ID    varchar(50)            not null comment '작성자ID',
    MOD_DATE  datetime               not null comment '수정일시',
    MOD_ID    varchar(50)            not null comment '수정자ID',
    constraint tb_menu_ID_uindex unique (ID)
) comment '메뉴';

create index tb_menu_PARENT_ID_index
    on tb_menu (PARENT_ID);

alter table tb_menu
    add primary key (ID);

/* 댓글 테이블 */
create table tb_reply
(
    ID       int auto_increment comment '식별키',
    BOARD_ID int         not null comment '게시글 식별키',
    CONTENT  text        not null comment '내용',
    REG_DATE datetime    not null comment '작성일시',
    REG_ID   varchar(50) not null comment '작성자ID',
    MOD_DATE datetime    not null comment '수정일시',
    MOD_ID   varchar(50) not null comment '수정자ID',
    constraint TB_REPLY_ID_uindex unique (ID desc)
) comment '댓글';

create index TB_REPLY_BOARD_ID_index
    on tb_reply (BOARD_ID desc);

alter table tb_reply
    add primary key (ID);
```
6. 데이터 생성
```mysql
insert into tb_menu 
(ID, PARENT_ID, MENU_NAME, SEQ, USE_YN, REG_DATE, REG_ID, MOD_DATE, MOD_ID)
values  
(1, null, '메뉴1', 1, 'Y', now(), 'jhkim', now(), 'jhkim'),
(2, null, '메뉴2', 2, 'Y', now(), 'jhkim', now(), 'jhkim'),
(3, null, '메뉴3', 3, 'Y', now(), 'jhkim', now(), 'jhkim'),
(4, 1, '메뉴1-1', 1, 'Y', now(), 'jhkim', now(), 'jhkim'),
(5, 1, '메뉴1-2', 2, 'Y', now(), 'jhkim', now(), 'jhkim'),
(6, 1, '메뉴1-3', 3, 'Y', now(), 'jhkim', now(), 'jhkim'),
(7, 1, '메뉴1-4', 4, 'Y', now(), 'jhkim', now(), 'jhkim'),
(8, 1, '메뉴1-5', 5, 'Y', now(), 'jhkim', now(), 'jhkim'),
(9, 2, '메뉴2-1', 1, 'Y', now(), 'jhkim', now(), 'jhkim'),
(10, 2, '메뉴2-2', 2, 'Y', now(), 'jhkim', now(), 'jhkim'),
(11, 3, '메뉴3-1', 1, 'Y', now(), 'jhkim', now(), 'jhkim'),
(12, 3, '메뉴3-2', 2, 'Y', now(), 'jhkim', now(), 'jhkim'),
(13, 3, '메뉴3-3', 3, 'Y', now(), 'jhkim', now(), 'jhkim'),
(14, 3, '메뉴3-4', 4, 'Y', now(), 'jhkim', now(), 'jhkim');
```