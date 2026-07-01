# JustChat 💬

Servlet, JSP, 그리고 Google GenAI SDK(Gemini API)를 연동하여 구축한 AI 채팅 웹 애플리케이션입니다. Docker Multi-stage 빌드를 통해 경량화된 이미지로 패키징되었으며, Render 플랫폼에 자동 배포되도록 구성되어 있습니다.

---

## 🛠 Tech Stack (기술 스택)

<p align="left">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/Apache_Tomcat-10-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black" alt="Tomcat 10" />
  <img src="https://img.shields.io/badge/Jakarta_Servlet-6.0-0073B1?style=for-the-badge&logo=jakartaee&logoColor=white" alt="Servlet 6" />
  <img src="https://img.shields.io/badge/Google_Gemini_API-Lite-8E75C2?style=for-the-badge&logo=googlegemini&logoColor=white" alt="Gemini API" />
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
  <img src="https://img.shields.io/badge/Render-Deployment-46E3B7?style=for-the-badge&logo=render&logoColor=white" alt="Render" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
</p>

---

## 📅 프로젝트 진행 단계별 가이드 문서

상세한 구현 과정과 원리, 면접 예상 질문은 단계별 가이드 문서에서 확인하실 수 있습니다. (GitHub 마크다운 뷰어에 최적화되어 있습니다.)

### [Step 01. Servlet & JSP 기반의 기본 채팅 구현](./01_servlet_jsp_chat.md)
* **주요 내용:** MVC 패턴 구현, `HttpSession` 기반의 대화 내역 유지, 그리고 새로고침으로 인한 중복 요청 방지를 위한 **PRG(Post-Redirect-Get) 패턴** 적용 원리
* **초심자 비유:** 식당의 주방장(Servlet), 서빙 직원(JSP), 중복 방지 번호표(PRG)

### [Step 02. Google GenAI SDK 추가 및 Gemini API 연동](./02_gemini_api_integration.md)
* **주요 내용:** `google-genai` Maven 의존성 설정, **try-with-resources**를 활용한 `Client` 리소스 해제 처리, 환경 변수(`.env`) 기반의 안전한 API Key 관리 방법
* **초심자 비유:** 외부 전문 자문단 전화 상담(Gemini), 자문단 전용 다이얼러(SDK)

### [Step 03. Docker Multi-stage 빌드와 Render 클라우드 배포](./03_docker_render_deployment.md)
* **주요 내용:** Maven 빌드 전용 컨테이너와 Tomcat 런타임용 컨테이너를 분리하여 용량을 최소화하는 **Multi-stage 빌드** 아키텍처, 톰캣 내부 포트(10000) 및 보안 설정 수정, Render 플랫폼 연동 방법
* **초심자 비유:** 재료 손질실과 조리 매장을 분리하여 최종 포장용 팩 크기를 줄이는 밀키트 포장 기법

---

## 🚀 로컬 실행 방법 (Quick Start)

### 1. 전제 조건 (Prerequisites)
* JDK 17 이상 설치
* Maven 3.x 이상 설치
* [Google AI Studio](https://aistudio.google.com/)에서 발급받은 **Gemini API Key**

### 2. 환경 변수 설정
프로젝트 루트 폴더에 `.env` 파일을 만들고 발급받은 API 키를 입력합니다. (단, `.env` 파일은 절대 Git에 커밋하지 마세요.)
```bash
# .env
GEMINI_API_KEY=your_actual_gemini_api_key_here
```

### 3. 애플리케이션 빌드 및 실행
```bash
# 의존성 다운로드 및 빌드
mvn clean package

# WAS(Tomcat) 구동 환경에 따라 로컬 배포
# (예: IntelliJ Tomcat Configurations에서 생성된 WAR 파일을 배포 및 기동)
```

---

## 🐳 Docker를 활용한 로컬 실행 방법

Dockerfile이 준비되어 있으므로, 도커를 이용해 로컬 환경에서도 클라우드 배포와 동일한 컨테이너 환경을 시뮬레이션할 수 있습니다.

```bash
# 1. 도커 이미지 빌드
docker build -t justchat:latest .

# 2. 컨테이너 실행 (환경변수 전달 및 포트 바인딩)
docker run -d -p 10000:10000 --env GEMINI_API_KEY="your_api_key" --name my-justchat justchat:latest

# 3. 접속 테스트
# 웹 브라우저를 열고 http://localhost:10000/chat 에 접속합니다.
```
