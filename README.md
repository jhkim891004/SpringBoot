# Spring Boot Sampling
Spring MVC 패턴 구조 파악 및 학습 용도
#### 사용한 라이브러리
> 추후 필요한 라이브러리 추가 예정
+ Web Container
  + Spring boot 2.5
+ WAS(Web Application Server)
  + Embedded Tomcat
+ Build
  + Gradle 7
+ Database
  + DBMS: MySqL
  + DB Access: MyBatis
+ Validation
  + Spring Validation
#### 디렉토리 구조(실제 실행 파일)
<pre>
└ domain
    ├ api
    |   ├ controller
    |   ├ service
    |   └ mapper
    ├ model
    |   ├ vo
    |   └ dto
    ├ config
    └ common
</pre>
+ api: 서비스 로직에 필요한 객체 생성
  + controller: 클라이언트의 요청을 받고 클라이언트에게 응답을 주는 계층
    + 요청/응답은 json 객체를 이용하여 교환
    + 응답은 항상 response 객체를 이용하여 통일
      + 성공 -> `SuccessResponse.java`
      + 실패 -> `ErrorResponse.java`
        + 보통 요청이 실패(Exception) 했을 시 Handler을 통한 통일된 객체 응답
    + 요청/응답에 Map 사용금지
      + Map을 사용하면 다른 개발자가 해당 Map이 어떤 데이터를 받고 어떤 데이터를 강제하는 지 알 수 없음
      + 잘못된 키값으로 인한 에러 검출이 힘듬. -> 객체 이용 시 컴파일 오류
  + service: controller와 DB간의 중간 계층(실제 서비스 로직을 담당)
    + 최대한 간결하게 달성하려는 목적만 명시적으로 표현
    + 데이터의 가공은 dto에서 해결
  + mapper: 컨테이너와 DB간 DAO(Data Access Object) 역할 담당
+ model: 서비스 로직에 종속되지 않는 객체 생성
  + dto: 클라이언트 <-> controller 간의 데이터 요청/응답 객체
    + 데이터의 가공 및 vo 객체에 데이터 바인딩 역할 담당
    + 무분별한 setter 사용 금지
    + 데이터 세팅이 필요할 때는 목적에 맞는 메소드명을 사용하여 set
    + naming prefix/postfix rule
      + 요청 시 req, 응답 시 res 를 prefix
      + `ex) reqMenuSearchDTO.java`
      + 검색 시 search, 저장 시 save, 수정 시 modify, 삭제 시 remove 를 postfix
      + `ex) reqMenuSaveDTO.java`
  + vo: 테이블과 매칭되는 객체
    + 해당 객체는 읽기전용으로 데이터를 바인딩하는 용도로 사용
    + getter를 사용한 데이터 조회만 가능, setter 금지
    + DAO 역할을 하는 Mapper
+ config: 라이브러리의 설정에 필요한 객체
+ common: 두 레이아웃 이외의 스프링 컨테이너에 필요한 객체
#### 디렉토리 구조(resources 파일)
<pre>
├ resources
|   └ mybatis-mapper    
├ resources-local
└ resources-stg
</pre>
+ resources: 배포 환경과 상관없이 공통으로 적용되는 resources
  + mybatis-mapper: 각 Mapper 인터페이스와 매핑되는 실제 실행될 쿼리를 작성한 xml 파일
+ resources-local: 로컬 환경을 위한 JDBC 연결 정보 및 로그 설정 파일
+ resources-stg: 스테이징 환경을 위한 JDBC 연결 정보 및 로그 설정 파일