# Pet Sitter Platform Backend

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Java](https://img.shields.io/badge/Java-17-orange)

반려동물 돌봄 서비스를 위한 백엔드 API 서버

## 🌟 주요 기능

- **세션 기반 인증 시스템**
- 펫시터 프로필 관리
- 예약 관리 시스템
- 사용자 권한 관리 (일반/관리자)
- 시스템 코드 관리

## 🛠 기술 스택

- **Core**: Java 17, Spring Boot 3.1.5
- **Security**: Spring Security 6, BCrypt
- **Database**: PostgreSQL, Hibernate
- **Session**: HTTP Session 기반 인증
- **API Docs**: Swagger UI

## 📂 프로젝트 구조

📦main
┣ 📂java
┃ ┗ 📂com
┃ ┃ ┗ 📂example
┃ ┃ ┃ ┣ 📂bookings
┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┗ 📜BookingController.java
┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┗ 📜Booking.java
┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┣ 📜BookingRequest.java
┃ ┃ ┃ ┃ ┃ ┗ 📜BookingResponse.java
┃ ┃ ┃ ┃ ┣ 📂exception
┃ ┃ ┃ ┃ ┃ ┣ 📜ConflictException.java
┃ ┃ ┃ ┃ ┃ ┗ 📜ResourceNotFoundException.java
┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┗ 📜BookingRepository.java
┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┗ 📜BookingService.java
┃ ┃ ┃ ┣ 📂codes
┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetailController.java
┃ ┃ ┃ ┃ ┃ ┗ 📜CodeGroupController.java
┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetail.java
┃ ┃ ┃ ┃ ┃ ┗ 📜CodeGroup.java
┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetailRequest.java
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeGroupRequest.java
┃ ┃ ┃ ┃ ┃ ┗ 📜CodeGroupResponse.java
┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetailRepository.java
┃ ┃ ┃ ┃ ┃ ┗ 📜CodeGroupRepository.java
┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┣ 📜CodeDetailService.java
┃ ┃ ┃ ┃ ┃ ┗ 📜CodeGroupService.java
┃ ┃ ┃ ┣ 📂global
┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
┃ ┃ ┃ ┣ 📂petsitters
┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┗ 📜PetsitterController.java
┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┗ 📜Petsitter.java
┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┣ 📜PetsitterRequest.java
┃ ┃ ┃ ┃ ┃ ┗ 📜PetsitterResponse.java
┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┗ 📜PetsitterRepository.java
┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┗ 📜PetsitterService.java
┃ ┃ ┃ ┣ 📂users
┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┣ 📜UserLoginRequest.java
┃ ┃ ┃ ┃ ┃ ┣ 📜UserRegisterRequest.java
┃ ┃ ┃ ┃ ┃ ┣ 📜UserResponse.java
┃ ┃ ┃ ┃ ┃ ┗ 📜UserUpdateRequest.java
┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
┃ ┃ ┃ ┗ 📜UserApplication.java
┗ 📂resources
┃ ┣ 📂static
┃ ┣ 📂templates
┃ ┗ 📜application.yml

## 📡 주요 API 요약

### 코드 관리 시스템

| Method | Endpoint                   | 설명                |
| ------ | -------------------------- | ------------------- |
| POST   | /api/code-groups           | 코드 그룹 생성      |
| GET    | /api/code-groups           | 전체 코드 그룹 조회 |
| PUT    | /api/code-groups/{groupId} | 코드 그룹 수정      |
| DELETE | /api/code-groups/{groupId} | 코드 그룹 삭제      |

| Method | Endpoint                          | 설명                  |
| ------ | --------------------------------- | --------------------- |
| POST   | /api/code-details                 | 코드 상세 항목 생성   |
| GET    | /api/code-details/group/{groupId} | 그룹별 코드 상세 조회 |
| PUT    | /api/code-details/{codeId}        | 코드 상세 수정        |
| DELETE | /api/code-details/{codeId}        | 코드 상세 삭제        |

### 사용자 관리

| Method | Endpoint            | 설명                      |
| ------ | ------------------- | ------------------------- |
| POST   | /api/users/register | 회원가입                  |
| POST   | /api/users/login    | 로그인 (세션 생성)        |
| GET    | /api/users/{userId} | 사용자 상세 정보 조회     |
| PATCH  | /api/users/{userId} | 사용자 정보 수정          |
| DELETE | /api/users/{userId} | 회원 탈퇴                 |
| GET    | /api/users          | 전체 사용자 조회 (관리자) |

### 펫시터 관리

| Method | Endpoint                      | 설명                  |
| ------ | ----------------------------- | --------------------- |
| POST   | /api/petsitters               | 펫시터 등록           |
| GET    | /api/petsitters               | 전체 펫시터 목록 조회 |
| GET    | /api/petsitters/{petsitterId} | 펫시터 상세 조회      |
| PATCH  | /api/petsitters/{petsitterId} | 펫시터 정보 수정      |
| DELETE | /api/petsitters/{petsitterId} | 펫시터 삭제           |

### 예약 관리

| Method | Endpoint                         | 설명                    |
| ------ | -------------------------------- | ----------------------- |
| POST   | /api/bookings                    | 새 예약 생성            |
| GET    | /api/bookings/user               | 사용자별 예약 목록 조회 |
| PATCH  | /api/bookings/{bookingId}/status | 예약 상태 변경          |
| DELETE | /api/bookings/{bookingId}        | 예약 취소               |

## 🔐 인증 필요 경로

- `/api/bookings/**` : 모든 예약 관련 엔드포인트
- `/user/getUserById/**` : 사용자 정보 조회
- `/user/updateUser/**` : 사용자 정보 수정
- `/user/deleteUser/**` : 회원 탈퇴
