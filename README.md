# HUSTLE - Backend
모든 대학생들, 모여 같이 운동하자! HUSTLE


![스크린샷 2023-11-25 오전 11 33 46](https://github.com/HUSTLE-UMC/HUSTLE_server/assets/16275188/42d59fbc-65a0-47a6-86da-bff2771671d1)

## Environments
* SDK : OpenJDK 11
* Build Tool : Gradle
* Tech tags : `OAuth2`, `JWT`, `JPA`, `QueryDSL`, `Swagger`

## How to run?

허슬 서버 프로젝트의 설정 파일은 Jasypt 라이브러리로 암호화하고 있습니다.
실행 시 공유받은 암호화 키를 설정해야 합니다.

```bash
# for database setting up
docker-compose up -d
```

프로젝트 코드 실행 시, 테스트 코드 실행 시 아래와 같이 VM options 으로 IDE 의 실행 환경에 추가해야 합니다.

```bash
-Djasypt.encryptor.password='ENCRYPT_KEY' -Dspring.active.profiles=default
```

## How to build?
```bash
# for develop
./gradlew build -Djasypt.encryptor.password='ENCRYPT_KEY'

# for production
./gradlew build -Djasypt.encryptor.password='ENCRYPT_KEY' -Dspring.active.profiles=prod
```

## DB ERD Diagram

![Hustle_20230822_165651](https://github.com/HUSTLE-UMC/HUSTLE_server/assets/16275188/ae14eef8-da5c-4edb-8a0b-0e32e5ec4b46)

## System Architecture

![image](https://github.com/HUSTLE-UMC/HUSTLE_server/assets/16275188/aaa148bf-6925-4f4a-b1d3-f7ce2719c475)

## Back Links

- [HUSTLE - Web Front Repo](https://github.com/HUSTLE-UMC/HUSTLE_web)

## Contributors

- [다람쥐](https://github.com/kor-Chipmunk)
- [규](https://github.com/min9yu98)
- [테오](https://github.com/realisshomyang)
- [코비](https://github.com/choiyoubin)
