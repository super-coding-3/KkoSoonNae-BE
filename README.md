# :dog:KkoSoonNae-BE:dog:
![IMG_1164](https://github.com/super-coding-3/KkoSoonNae-BE/assets/162071460/0fb32551-01d4-48e8-ad9b-ed48dc8de8dc)
## 프로젝트 소개
KkoSoonNae 꼬순내는 반려동물이 계속해서 늘어나고 있는 요즘 사람이 아닌 반려동물에 대한 니즈를 충족하기 위해 기획했다.
사람도 시간이 지나면 자라는 머리카락을 위해 미용실을 이용하듯 반려동물 미용실도 쉽고 간편하게 원하는 니즈를 충족시켜주기 위한 웹 사이트 이다.
## 프로젝트 아키텍쳐
## 프로젝트 구조
### 주요 기능
+ **사용자 등록 및 로그인:** 반려동물 주인과 미용사가 계정을 생성하고 로그인하여 웹을 접근할 수 있습니다.
+ **프로필 관리:** 사용자가 개인 정보와 반려동물 정보를 업데이트할 수 있습니다.
+ **매장 검색:** 사용자가 매장명, 주소로 매장을 찾을 수 있다.
+ **내위치 매장검색:** 키워드검색이 아닌 내 주변 위치로도 매장을 찾을 수 있다. 
+ **예약 시스템:** 반려동물 주인이 이용 가능한 시간대를 확인하고 미용 예약을 할 수 있습니다.
### ERD 설계
![ERD](https://github.com/super-coding-3/KkoSoonNae-BE/assets/162071460/e79427f7-91c2-47e4-bd04-a141c4356ea6)
### ⚙️ 개발 환경
+ Java 17
+ IDE : Intellij
+ Framework : Spring Boot (3.X)
+ ORM : JPA,Query DSL
+ DB : MySQL
+ Security : JWT
+ API 문서화 : Swagger
+ AWS : EC2 , RDS , S3# KkoSoonNae-BE
### 개발기간 및 작업관리
+ 24.05.06~24.06.03
### 개발 기술 및 브랜치 전략
## 역할 분담(기여도)
+ **채동현:** 내 주변 매장 찾아서 정보 보여주기 
+ **이학준:** Spring Boot 초기작업 ,Security 및 Jwt적용, AWS EC2, RDS S3,Swagger 초기 작업,CustomException 제작,마이페이지 프로필수정,프로젝트 배포 ,
             내가쓴리뷰,예약내역,내포인트,문의하기,관심매장,내꼬순내,회원가입
 
+ **김재익:** 메인 강남구 매장리스트,매장전체검색,매장상세조회,매장 펫스타일, 매장관심매장추가,매장관심매장 삭제,매장 리뷰조회, CustomException
+ **배기한:** 예약페이지 - 예약하기 , 펫정보 가져오기,펫등록하기 ,스타일가져오기, 예약확인 ,스프링 스케쥴러 ,ExceptionHandler ,Logback
+ **이용이:** 공지사항 조회, 매장 리뷰작성, 별점추가
## 팀원 구성
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/Chaedonghyun"><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀장 : 채동현</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/leehagjoon"><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 이학준</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/kimjaeik95"><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 김재익</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/keehan95"><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 배기한</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/skfkfh"><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 이용이</b></sub></a><br /></td>
     <tr/>
      <td align="center"><a href="https://github.com/ruby-yujin"><img src="" width="100px;" alt=""/><br /><sub><b>FE 팀장 : 박유진</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/HyoKyoungLee"><img src="" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 이효경</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/ines012"><img src="" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 박우혁</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

## 개선목표
개선 목표
처음 프로젝트를 기획 할때는 스스로와 팀원들의 실력을 고려하여 필수라고 생각되는 기능을 구현하는데에 초점을 맞췄었다.
생각보다 빠르게 필수 기능들을 구현하게 되면서 개선을 위한 시간을 얻었고 먼저는 코드의 간소화를 시도했다.
필수 기능을 구현하는데만 초점을 맞추다보니 나중에가서 코드의 가독성이 많이 떨어지고 서버를 구동할때에도 생각보다 많은 시간이 걸려 코드의 간소화가 필수적이라고 생각했다.
## 트러블 슈팅
