## 1. 자신이 개발한 앱에 대한 설명

### 프로젝트 명 : 일정관리 및 위시리스트 애플리케이션
**개요** : 본 애플리케이션은 사용자의 일정을 효율적으로 관리하고 구매 목표를 설정 및 관리할 수 있는 위시리스트 기능을 제공하는 서비스입니다. 일정 알림 기능은 실시간 WebSocket을 활용하여 구현하였으며 사용자의 입금 계획 및 목표 달성을 돕는 위시리스트 관리 기능이 포함되어 있습니다.

### 기능 상세
**[일정 관리]**
- 일정 등록: 사용자는 일정 유형에 따라 맞춤형 데이터를 입력하여 일정을 생성할 수 있습니다.
  - 단일 종일 일정
  - 단일 시간 지정 일정
  - 연속일 종일 일정
  - 연속일 시간 지정 일정
- 일정 수정: 등록된 일정을 수정할 수 있습니다.
- 일정 조회: 월별, 일별, 단일 일정 상세 조회가 가능합니다.
- 일정 삭제: 등록된 일정을 삭제할 수 있습니다.
- 일정 알림 기능:
  - 종일 일정: 해당 일정의 날짜 자정에 알림 전송
  - 시간 지정 일정: 해당 일정의 지정 시간에 알림 전송
  - 실시간 WebSocket을 활용하여 클라이언트에 알림 전송

**[위시리스트 관리]**
- 위시리스트 등록: 구매 목표 금액, 시작일자, 입금 빈도, 입금일 등을 입력하면 자동으로 목표 마감일이 설정됩니다.
- 위시리스트 입금 관리: 설정한 입금일 혹은 요일에 맞춰 입금 완료 시 남은 금액이 차감됩니다.
- 위시리스트 수정: 등록된 위시리스트 정보를 수정할 수 있습니다.
- 위시리스트 조회: 전체 목록 및 단일 위시리스트 상세 조회가 가능합니다.
- 위시리스트 삭제: 등록된 위시리스트를 삭제할 수 있습니다.

## 2. 소스 빌드 및 실행 방법 메뉴얼
**1. Gradle 로딩 및 프로젝트 빌드**
- build.gradle에서 Gradle을 로딩합니다.
- ./gradlew clean 명령어 실행
- ./gradlew build 명령어 실행

**2. 데이터베이스 설정**
- application.yml 파일에서 환경에 맞는 DB 접속 정보를 설정합니다.
  - hostname, username, password 수정
- MySQL 실행 후 데이터베이스 생성
  - create database calenradrdb;
  - use calendardb;
- 첨부된 SQL 파일 실행(일정관리_가데이터.sql)

**3. 애플리케이션 실행**
- 실행 후 WebSocket 연결 테스트
  - ws://localhost:8080/ws?deviceToken={deviceToken}
  - deviceToken은 일정(schedule) 데이터에 포함되어 있으며, 가데이터의 경우 deviceToken은 'abc123'입니다.

**데이터베이스 ERD**
![Image](https://github.com/user-attachments/assets/7cadea18-e266-4d0b-a27a-a217b3448b0f)

## 3. 주력으로 사용한 라이브러리에 대한 설명 및 사용 이유
**[QueryDSL]**
- 사용 이유: 일정 조회 시 Schedule과 ScheduleDetail 테이블의 조인이 빈번하게 발생하여, 가독성과 유지보수성을 고려하여 QueryDSL을 도입했습니다.
- 문제 상황: 현재 시간과 일치하는 스케줄의 startTime 조회 시 나노초 단위까지 일치하지 않으면 조회되지 않는 문제가 발생
- 해결 방법: 시간 비교 타입을 DateTime으로 명시하여 문제 해결

**[Scheduler]** 
- 사용 이유: 일정 알림 기능 구현을 위해 1분마다 실행되는 스케줄러를 활용하여 현재 시간과 일치하는 일정 탐색 및 알림 전송
- 실행 방식: QueryDSL을 이용해 현재 시간과 일치하는 일정을 조회한 후 해당 일정의 deviceToken을 사용해 WebSocket을 통해 클라이언트에 알림 전송
- Quartz 미사용 이유: 프로젝트 규모와 목적상 고성능 스케줄링 라이브러리가 필요하지 않아 상대적으로 간단한 Scheduler를 선택함

**[WebSocket]**
- 사용 이유: 일정 알림의 실시간 전송을 위해 WebSocket을 채택
- FCM 미사용 이유: FCM은 테스트 환경 구축 및 활용이 복잡하여 개발 및 테스트 단계에서는 테스트가 간단한 WebSocket 방식을 선택함 실제 배포 시에는 FCM 활용 가능성 고려

## 4. Api 명세
**메일 내 첨부된 파일(권성지_일정관리_api 명세.html)**

## 5. 테스트케이스
**[테스트케이스 Link](https://docs.google.com/spreadsheets/d/1f7tDXXzBYK3tO5lGjpkfW9lyALR461s2HhZWScGy688/edit?usp=sharing)**

