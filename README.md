# 무료 포인트 처리 API

## 개발 환경
- Java 21
- Spring Boot 3.x
- H2 Database (내장형)

## 빌드 & 실행 
방법
1. Java 21 환경에서 소스 checkout
2. 아래 명령어로 빌드 및 실행
    ```
    ./gradlew build
    ./gradlew bootRun
    ```
3. 실행 후 `http://localhost:8080/h2-console`에서 H2 DB 데이터 확인 가능
    

## API 주요 기능

- **적립**
    - 1회 적립 가능 포인트: 1포인트 이상 ~ 10만포인트
    - 1회 최대 적립 한도 : 기본 100만
    - 개인별 무료포인트 최대 한도 설정 가능
    - 포인트는 최소 1일~최대 5년 미만의 만료일 지정 (현재 기본 365일)
    - 포인트 관련 정책들은 POINT_POLICY, POINT_POLICY_PERSONAL 테이블에서 추가/변경이 가능합니다.
- **적립 취소**
    - 해당 적립 건의 금액만큼만 취소 가능
    - 이미 일부 사용된 적립 건은 취소 불가
- **사용**
    - 주문시에만 포인트 사용 가능
    - (이 기능은 주문 발생 후 주문API에서 주문번호를 가지고 point 사용 API 호출을 한다고 가정하여 만들었습니다.)
    - 관리자가 지급한 포인트 우선 사용 + 만료일 짧은 순서대로 자동 사용됩니다.
- **사용 취소**
    - 전체/일부 금액의 사용 취소 가능
    - 사용취소 시 이미 만료된 포인트라면 그 금액만큼 신규 적립 처리
    - (이 때 신규 적립되는 포인트는 1회 최대 적립 가능 포인트의 영향을 받지 않습니다.)

## ERD
![ERD 다이어그램](./src/main/resources/erd.png)

## API 테스트 방법 (IntelliJ REST Client)
1. 아래 링크를 누르시면 http파일로 이동합니다.
2. IntelliJ에서 해당 파일을 열면 각 요청마다 `Run HTTP request` 버튼이 나타납니다.
3. API 서버(Spring Boot)를 먼저 실행하세요.
4. 원하는 요청의 버튼을 클릭하면 즉시 서버와 연동된 결과를 확인할 수 있습니다.
5. 테스트 응답 결과와 status code는 하단에 표시됩니다.
- [1_적립_테스트.http](src%2Ftest%2Fhttp%2F1_%EC%A0%81%EB%A6%BD_%ED%85%8C%EC%8A%A4%ED%8A%B8.http)
- [2_적립취소_테스트.http](src%2Ftest%2Fhttp%2F2_%EC%A0%81%EB%A6%BD%EC%B7%A8%EC%86%8C_%ED%85%8C%EC%8A%A4%ED%8A%B8.http)
- [3_사용_테스트.http](src%2Ftest%2Fhttp%2F3_%EC%82%AC%EC%9A%A9_%ED%85%8C%EC%8A%A4%ED%8A%B8.http)
- [4_사용취소_테스트.http](src%2Ftest%2Fhttp%2F4_%EC%82%AC%EC%9A%A9%EC%B7%A8%EC%86%8C_%ED%85%8C%EC%8A%A4%ED%8A%B8.http)