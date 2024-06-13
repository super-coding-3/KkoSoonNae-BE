# 🐶🐱 반려동물의 미용샵 플랫폼서비스 
![IMG_1164](https://github.com/super-coding-3/KkoSoonNae-BE/assets/162071460/0fb32551-01d4-48e8-ad9b-ed48dc8de8dc)
- 배포 URL : [https://www.kkosoonnae.store/](https://www.kkosoonnae.store/)

<br>

## 1. 프로젝트 소개
- 꼬순내는 펫팸족과 전문 반려동물 미용사를 연결하는 플랫폼입니다. 
- 내 반려동물 등록 후 원하는 날짜, 스타일을 예약할수있습니다.
- 검색을 통해 근처 펫미용샵을 알아보고 후기등을 참고 할수있습니다.
- 문의하기, 리뷰관리, 펫정보 등을 수정할수있습니다.

<br>
  
## 2. 프로젝트 아키텍쳐
  ![아키텍쳐112 drawio](https://github.com/super-coding-3/KkoSoonNae-BE/assets/162071460/8594b0d6-08dc-4460-94a7-31109aae1272)

<br>
  
## 3. 주요 기능
+ **사용자 등록 및 로그인:** 반려동물 주인과 미용사가 계정을 생성하고 로그인하여 웹을 접근할 수 있습니다.
+ **프로필 관리:** 사용자가 개인 정보와 반려동물 정보를 업데이트할 수 있습니다.
+ **매장 검색:** 사용자가 매장명, 주소로 매장을 찾을 수 있다.
+ **내위치 매장검색:** 키워드검색이 아닌 내 주변 위치로도 매장을 찾을 수 있다. 
+ **예약 시스템:** 반려동물 주인이 이용 가능한 시간대를 확인하고 미용 예약을 할 수 있습니다.

<br>
  
## 4. ERD 설계
![erd](https://github.com/super-coding-3/KkoSoonNae-BE/assets/105399835/4b233eb1-53f5-4964-984c-7df831b99686)

<br>

## 5. 개발 환경
+ Java 17
+ IDE : Intellij
+ Framework : Spring Boot (3.2.5)
+ ORM : JPA,Query DSL
+ DB : MySQL
+ Security : JWT
+ API 문서화 : Swagger
+ AWS : EC2 , RDS , S3
+ Nginx
+ certbot
+ Front 협업 : [Figma](https://www.figma.com/design/blBt17GB6m2L5OEHCmAMtP/%EA%BC%AC%EC%88%9C%EB%82%B4-UI?node-id=41-357&t=f6FakeOd9r1YTGTv-1)
<br>

### ⚒️ 개발 기술 및 브랜치 전략

![git  다이어그램](https://github.com/super-coding-3/KkoSoonNae-BE/assets/162071460/22d42a48-c6a8-48fc-ba63-f326cdb04da3)

<br>

## 6. 개발 기간 및 작업 관리

### 개발 기간

- 전체 개발 기간 : 2024-05-06 ~ 2024-06-03
- 기획 : 2024-05-07 ~ 2024-05-12
- UI 구현 : 2024-05-13 ~ 2024-05-19
- 기능 구현 : 2024-05-20 ~ 2024-06-03

<br>

### 작업 관리

- GitHub PR과 디스코드를 사용하여 진행 상황을 공유했습니다.
- 주간회의를 진행하며 작업 순서와 방향성에 대한 고민을 나누고 [Notion](https://www.notion.so/511920be91a34f8485c6c91f82d8fd19?pvs=4)에 회의 내용을 기록했습니다.
- WBS 작성하여 프로젝트 범위 명확화하고 진척도 추적을 했습니다.
![image](https://github.com/super-coding-3/KkoSoonNae-BE/assets/105399835/9444b61f-41c1-4c16-a1e8-c8d473fabfd4)

<br>

## 7. 역할 분담

### 🐵 채동현

내 주변 매장 찾아서 정보 보여주기 

<br>

### 🐶 이학준
Spring Boot 초기작업 ,Security 및 Jwt적용, AWS EC2, RDS S3,Swagger 초기 작업,CustomException 제작,마이페이지 프로필수정,프로젝트 배포 ,내가쓴리뷰,예약내역,내포인트,문의하기,관심매장,내꼬순내,회원가입

<br>

 
### 🐭 김재익
메인 강남구 매장리스트,매장전체검색,매장상세조회,매장 펫스타일, 매장관심매장추가,관심매장 삭제,매장 리뷰조회,CustomException

<br>

### 🐺 배기한 
예약페이지 - 예약하기 , 펫정보 가져오기,펫등록하기 ,스타일가져오기, 예약확인 ,스프링 스케쥴러 ,ExceptionHandler ,Logback

<br>

### 🐼 이용이
공지사항 조회, 매장 리뷰작성, 별점추가
  
<br>
  
## 👨‍💻 팀원 구성
<table align="center">
    <tr>
      <th><img src="https://avatars.githubusercontent.com/u/102035495?v=4" width=150></th>
      <th><img src="https://avatars.githubusercontent.com/u/105399835?v=4" width=150></th>
      <th><img src="https://avatars.githubusercontent.com/u/162071460?v=4" width=150></th>
      <th><img src="https://avatars.githubusercontent.com/u/157384713?v=4" width=150></th>
      <th><img src="https://avatars.githubusercontent.com/u/156290150?v=4" width=150></th>
     </tr>
       <tr>
       <td align="center">채동현</td>
       <td align="center">이학준</td>
       <td align="center">김재익</td>
       <td align="center">배기한</td>
       <td align="center">이용이</td>
       </tr>
</table>
  
<br>

## 8. 개선목표
1. 단순 API CRUD의 양보단 질 , 퀄리티 고민
2. Controller에서 Map으로 메시지를 보내주고 있는것을 Response 형식으로 처리
3. Controller Advice 에러처리 Exception Handler로 캐치해서 에러 인 경우 응답 처리
4. 공지사항 캐시 처리 -> 매번 DB를 찌를 필요 가 없기 때문에
5. 코드 리팩토링 -> 한 메소드의 로직이 책 처럼 읽혀 지지 않음 -> 메소드 분리 후 코드 간결화
6. 지도 거리 관련 거리 계산 할 때 DB 풀 스캔 성능 이슈가 발생 할 수 있기에 거리기반 인덱스를 지원하는 DB를 찾거나 다른 방법 찾아보기
7. JPQL로 쓴 쿼리들 QueryDSL로 바꾸기
8. 리뷰 별점 - 매장 정보 내보낼 때 마다 계산을 하고 있다. 별점이 실시간일 이유는 없기에 스케줄러 비정규화 하거나 레디스 , 캐시 사용해보기
