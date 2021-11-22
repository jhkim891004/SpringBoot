## SpringBoot
- spring boot 기본 CRUD 샘플링
### Local 세팅을 위한 MySQL Server 설치
1. MySQL Server Community 버전 설치
- [다운로드링크](https://dev.mysql.com/downloads/installer/)
2. 데이터베이스 생성 및 유저 생성
```mysql
use mysql;
-- 사용자 생성
create user cndfactory@localhost identified by '1234';
-- database 생성
create database spring default character set utf8;
-- 사용자 권한 부여
grant all privileges on spring.* to cndfactory@locahost;
```
#### 초기 DDL
```mysql

```
#### 초기 DML
```mysql

```