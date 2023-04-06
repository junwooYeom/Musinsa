# Musinsa

# movieApplication

## 사용 기술 스택

### 프로젝트 구성
- MVVM 프레임워크
- Android Clean Architecture

### 의존성 주입
- Hilt

### 로컬 데이터베이스
- Room

### 네트워크
- Retrofit2 with Moshi

### 비동기 처리
- Kotlin Coroutines
- Kotlin Flow

### 이미지 리소스
- Glide
### 뷰 관련 라이브러리
- Jetpack Compose
- Android Navigation
- Paging3

## 프로젝트 설치 및 빌드 방법
> Android Studio Electric Eel 버전으로 개발 진행하였습니다.
> Android Studio 가 설치되어 있지 않다면 설치 부탁드립니다.
### 링크로 확인
1. Git 을 통한 Source code 클론 받기
2. Android Studio 로 받아온 아이템 추가
3. Emulator 혹은 실 기기와 연결하여 Run 혹은 Debug 실행

### Zip 파일로 확인
1. 전송한 .zip 파일 압축 풀기
2. Android Studio 실행
3. Profile Or Debug 실행
4. 압축 풀기 된 폴더에서 빌드 완료된 Apk 클릭
5. 연결된 실 기기 혹은 에뮬레이터로 실행

## 프로젝트 사용법 및 기능
### 헤더
1. 아이템 여부에 따라 "전체" 텍스트를 보여주거나, icon 을 보여줌
2. 만약 헤더 아이템이 없으면 무시하고 컨텐츠를 보여주게 됨
### 배너 컴포넌트
1. 상단의 배너를 통해서 이미지 스와이프 기능
2. 배너 우측 하단의 현재 페이지 확인 가능
3. 우측으로 무한 스와이프 가능
### 그리드 컴포넌트
1. 3열의 그리드 형식으로 보여짐
2. 하단 더보기 버튼 클릭 시, 리스트에 아이템 추가
3. 만약 보여줄 아이템이 더 존재하지 않는다면, 하단 푸터는 사라지고, 최대의 아이템 갯수를 보여줌.
### 스크롤 컴포넌트
1. 헤더가 2줄 이상으로 나올 때 Ellipsis 처리가 되도록 수정
2. 스크롤 부분은 ContentPadding 을 통해 화면 패딩에 맞춰 처리되지 않도록 설정.
3. 하단 새로고침 버튼 클릭 시, 아이템의 순서가 변경되어 노출
### 스타일 컴포넌트
1. 상단 3개는 Row/Column을 통해 독립적으로 운용
2. 나머지 갯수는 LazyVerticalGrid 를 통하여 진행
3. 하단 더보기 버튼 클릭 시, 리스트에 아이템 추가
4. 만약 보여줄 아이템이 더 존재하지 않는다면, 하단 푸터는 사라지고, 최대의 아이템 갯수를 보여줌.
## 완료되지 않은 이슈
1. TestCode 미작성.
2. 스크롤 컴포넌트에서 새로 고침 버튼 누를 시에 애니메이션이 사용자 관점에서 매끄럽지 못함.
3. 스타일 컴포넌트에서 상단 Row/Column 부분과 LazyVerticalGrid 부분과의 간격이 약간 다르게 됨.
