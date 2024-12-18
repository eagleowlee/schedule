# Schedule 앱 개발
___
## 소개
___
CRUD를 만든 스케쥴입니다.
## 개발 기간
___
2024/12/13 ~ 2024/12/18
## 개발 환경
___
Version : java 17
IDE : IntelliJ
Sping Boot 3.4.0
Spring data JPA
MySQL DRIVER
LOMBOK
## ERD
||Schedule||
|------|---|---|
|id|bigint|pk|
|member_id|bigint|pk|
|username|bigint||
|password|varchar||
|title|varchar||
|contents|varchar||
|create_at|dateitme||
|modified_at|datetime||

||member||
|------|---|---|
|id|bigint|pk|
|username|bigint||
|password|varchar||
|create_at|dateitme||
|modified_at|datetime||

## API 명세서
|기능|Method|URL|request|response상태|
|------|---|---|---|---|
|스케쥴 생성|POST|/schedules|Title, Contents, username|201|
|회원가입|POST|/members/signup|Username, Password, Email|200|
|회원조회|GET|/members/{id}|id|200|
|특정 스케쥴 조회|GET|/schedules|id|200|
|스케쥴 전체 조회|GET|/schedules/{id}||200|
|스케쥴 삭제|DELET|/schedules/{id}|id|200|
