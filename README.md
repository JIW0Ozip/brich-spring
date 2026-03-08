#  Brich 프로젝트 소개 
**Brich**는 광고주와 블로거를 연결하는 캠페인 매칭 플랫폼이자, 데이터 기반 블로거 성장 지원 서비스입니다.

블로거에게 크롤링한 데이터를 정제하여 산출한 독자적인 블로그 지수와 황금 키워드를 제공함으로써  
블로거가 데이터 기반으로 전략을 수립하고 성장할 수 있도록 적극 지원합니다.  
광고주는 캠페인(광고)를 등록하여 캠페인 홍보와 캠페인에 참여하고 싶은 블로거들을 편하게 관리할 수 있는 기능을 이용 가능하고  
Brich를 통해 성장하는 블로거들은 자신에게 맞는 캠페인을 지원하여  
광고주와 블로거를 연결시켜 서로 이득을 볼 수 있는 서비스 구조입니다.  

### 주요 특징
- 📢 **캠페인 매칭**: 광고주와 블로거를 효율적으로 연결
- 📊 **독자적 블로그 지수**: 크롤링 데이터 정제를 통한 자체 블로그 분석 지표 제공
- 🔑 **황금 키워드 제공**: 데이터 기반 최적 키워드 추천
- 🎯 **맞춤형 캠페인 추천**: 블로거 특성에 맞는 캠페인 매칭
- 💬 **실시간 1:1 채팅**: 블로거와 광고주 간 원활한 소통 지원

---

## 🔗 링크

🎥 **[시연 영상 보기](https://www.youtube.com/watch?v=qYUTPqjN7i0&t=848s)**

🎨 **[Figma 디자인 보기](https://www.figma.com/design/N2J1QrbHshX2X0FL1Vnnoo/Brich?node-id=0-1&p=f&t=ASVuOO4T5JVYLbNz-0)**

---

## 목차
- [기술 스택](#기술-스택)
- [주요 기능](#주요-기능)
- [프로젝트 아키텍처](#프로젝트-아키텍처)
- [ERD](#erd)
- [팀원](#팀원)

---

## 기술 스택
[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1766576814322?alt=media&token=7b7fc3ae-33d8-4e60-b089-aeb856880d2a)](https://github.com/msdio/stackticon)

---

## 주요 기능

<details>
<summary><b>👤 회원 기능</b></summary>

- 로그인 / 소셜 로그인
- 회원가입 / 아이디 찾기 / 비밀번호 재설정
- 블로거 구독 결제
  - 프리미엄 기능(황금 키워드, 블로그 지수 등)을 위한 구독 결제
- 블로거 지역 및 카테고리 재설정

</details>

<details>
<summary><b>📊 대시보드</b></summary>

- 예시 A

</details>

<details>
<summary><b>📢 캠페인</b></summary>

**공통**
- 캠페인 리스트 조회 및 필터 검색
  - 카테고리, 조건, 마감일 등 다양한 필터 제공
  - 인기순, 최신순, 마감임박순 정렬
- 캠페인 상세 페이지 열람
  - 캠페인 개요, 미션 조건, 보상, 유의사항 등 확인 가능
    
**블로거**
- 캠페인 지원 / 취소
  - 캠페인 조건 확인 후 지원 가능
  - 지원 상태별 관리 및 취소 기능
- 채택 여부 및 후기 등록
  - 채택된 캠페인 후기 작성 및 제출
  - 후기 승인 여부 및 반려 사유 확인
- 나의 캠페인 관리
  - 진행 중 / 완료 등 상태별 캠페인 리스트 관리

**광고주**
- 작성한 캠페인 리스트 확인
  - 캠페인 상태별 필터 제공
  - 캠페인 제목 검색 기능 제공
- 캠페인 신청 / 수정 / 임시저장
  - 캠페인 정보 작성
  - 파일 첨부 -> 캠페인 리스트, MY 캠페인, 상세보기에서 이미지 제공
- 캠페인 신청 블로거 목록
  - 신청 블로거의 블로그 데이터 확인
  - 블로거 패널티 횟수 확인
  - 각 데이터 별 정렬 기능 제공
  - 블로거 별 대시보드에 있는 블로그 지수, 일일 방문자 수 확인
  - 블로거 검색 기능 제공
  - 블로거 채택 (선정중 단계)
- 채택한 블로거 목록
  - 각 블로거별 포스팅 마감일 / 제출 상태 확인
  - 이전 포스팅의 반려 기록 확인
  - 작성한 포스팅 확인 -> 승인 / 반려 결정
  - 반려 시, 반려 사유 / 첨부 파일 / 재제출 마감일 설정
- 반려 기록
  - 해당 캠페인의 반려 기록 확인
</details>

<details>
<summary><b>💬 1:1 채팅</b></summary>

- 블로거와 광고주 간 실시간 1:1 채팅 (WebSocket, Stomp 기반)
- 채팅방 목록 조회 및 안읽은 채팅방 필터
  - 새 메시지 수신 시 채팅방 목록 실시간 반영
- 텍스트 및 파일 메시지 송수신
  - 이미지 파일 채팅창 내 미리보기 제공
  - 파일 다운로드 지원
- 채팅방 내 메시지 실시간 표시
- 메시지 읽음 처리 실시간 반영

</details>

<details>
<summary><b>👨‍💼 관리자</b></summary>

- 회원 정보 수정
- 가입 승인/반려
- 블로그 주소 수동 인증
- 문의 답변
- 신고 처리
- 캠페인 승인/반려
- 캠페인 신청자/채택자 조회
- 캠페인 채택자 - 반려 기록 및 재제출 내용 조회
- 캠페인 카테고리 - 노출 순위 변경 / 추가 / 병합 / 분할

</details>

<details>
<summary><b>❓ 문의 / 신고 / 도움말</b></summary>

- 문의 / 신고글 작성 및 조회
- 도움말 조회

</details>

<details>
<summary><b>챗봇</b></summary>

- 모든 이용자들의 질문에 대한 챗봇 응답 (RAG, FastAPI 기반)
  - 질문에 대한 FAQ 데이터 기반 생성형AI 응답

</details>

---

## 프로젝트 아키텍처
<img width="871" height="332" alt="프로젝트아키텍처v2 drawio" src="https://github.com/user-attachments/assets/4f225c73-c8b6-40ad-8470-d126d71bff1f" />

---

## ERD
<img width="2231" height="1168" alt="ERD" src="https://github.com/user-attachments/assets/899269b2-9f1f-4a19-8ecc-b1cc3eb14ba1" />


### 테이블 명세서

<details>
<summary><b>회원</b></summary>
<img width="898" height="850" alt="회원1" src="https://github.com/user-attachments/assets/5e4a0e58-4bef-415c-8fe2-91ab7eafd788" />
<img width="898" height="705" alt="회원2" src="https://github.com/user-attachments/assets/ddbf78d6-87b7-4995-81cd-28849586fe90" />


</details>

<details>
<summary><b>캠페인</b></summary>
<img width="1220" height="953" alt="캠페인1" src="https://github.com/user-attachments/assets/51c355f0-5381-492a-9397-a0a4eadeb628" />
<img width="1219" height="562" alt="캠페인2" src="https://github.com/user-attachments/assets/52d75725-6543-4251-8ed2-ca2ac1f26fd1" />


</details>

<details>
<summary><b>공통코드/지역</b></summary>
<img width="766" height="535" alt="공통코드" src="https://github.com/user-attachments/assets/0d98f649-8aac-4074-b311-10cbe2d8ea61" />

</details>

<details>
<summary><b>포스트 데이터</b></summary>
<img width="850" height="617" alt="포스트데이터" src="https://github.com/user-attachments/assets/06f6eb05-917d-46cb-85d8-42bcd3864fab" />


</details>

<details>
<summary><b>채팅</b></summary>
<img width="952" height="592" alt="채팅" src="https://github.com/user-attachments/assets/dcb931c3-770b-4779-a661-5a03c1829a9a" />


</details>

<details>
<summary><b>결제</b></summary>
<img width="876" height="460" alt="결제" src="https://github.com/user-attachments/assets/49dcf744-bdbe-4de1-b506-66b13cad1d27" />


</details>

<details>
<summary><b>문의/신고/도움말</b></summary>
<img width="950" height="795" alt="문의신고도움말1" src="https://github.com/user-attachments/assets/bf595520-cdbb-4b19-9e1a-67d405ce6ec9" />
<img width="1258" height="183" alt="ㅁㅜㄴㅇㅡㅣㅅㅣㄴㄱㅗㄷㅗㅇㅜㅁㅁㅏㄹ2" src="https://github.com/user-attachments/assets/5ea2a9d1-77fe-4fc4-aa77-73b85fd62642" />

</details>

<details>
<summary><b>첨부파일</b></summary>
<img width="1271" height="469" alt="첨부파일" src="https://github.com/user-attachments/assets/d7048761-afec-4375-81af-25a8701f68bb" />

</details>

---

## 팀원

| 이름 | GitHub |
|------|--------|
| 김동현 | [@dframe9801](https://github.com/dframe9801) |
| 김태현 | [@Hapakata](https://github.com/Hapakata) |
| 심석진 | [@sim748](https://github.com/sim748) |
| 이동은 | [@bbabpuli](https://github.com/bbabpuli) |
| 조성진 | [@choseongjin0815](https://github.com/choseongjin0815) |
| 최지우 | [@JIW0Ozip](https://github.com/JIW0Ozip) |
