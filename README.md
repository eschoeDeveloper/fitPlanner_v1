# 💪 FitPlanner

**개인 운동 관리 앱**  
운동 일정과 주변 헬스장을 효과적으로 관리할 수 있도록 돕는 포트폴리오용 피트니스 앱입니다.

![fitplanner-banner](./FitPlanner/src/main/resources/static/assets/img/heathGym.24e27cd1.jpg) <!-- (원하시면 여기에 대표 이미지 추가) -->

---

## 📌 주요 기능

- 🗓️ **운동 일정 관리**: 나만의 운동 계획을 등록하고 관리
- 📍 **주변 헬스장 추천**: 위치 기반 근처 헬스장 탐색
- 📊 추후 확장 가능: AI 기반 피트니스 분석 기능 등으로 확장 가능

---

## ⚙️ 기술 스택

| 영역       | 기술 스택                             |
|------------|----------------------------------------|
| **Backend** | Spring Boot, JPA, SQLite              |
| **Frontend** | Vue 3                                 |
| **DevOps**  | Docker, CI/CD 예정                    |

---

## 🚀 실행 방법

### 📦 백엔드

```bash
# 프로젝트 빌드 및 실행
./gradlew build
java -jar build/libs/fitplanner.jar
