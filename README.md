## spring mvc 게시판

### 사용기술 
* java, jsp, javascript, css
* bootstrap4, jQuery , Ajax, jstl, el 
* spring, springJDBC
* RDBMS - mysql

### 개발환경
* 이클립스
* spring 4.3.5
* maven 3.5
* jdk 1.8
* tomcat 8.5

### 데이터베이스 모델링
<img width="539" alt="데이터베이스 모델링" src="https://user-images.githubusercontent.com/45925158/71545555-cd722380-29cf-11ea-8274-3cce132a4acf.PNG">

### 게시글
 * **게시글 조회**
 * **게시글 검색**
 * **페이징 처리**
<img width="826" alt="board" src="https://user-images.githubusercontent.com/45925158/71576621-8a7d9080-2b34-11ea-8942-7af3e37ea348.PNG">
 <img width="781" alt="detail1" src="https://user-images.githubusercontent.com/45925158/71574779-a2054b00-2b2d-11ea-9f77-719bee5e6f73.PNG">
 
### 글쓰기
 * **게시글 등록**
 * **게시글 수정**
 * **게시글 삭제**
 * **이미지 파일 업로드 및 다운로드**
<img width="781" alt="write" src="https://user-images.githubusercontent.com/45925158/71575014-99f9db00-2b2e-11ea-83db-26a990d94fed.PNG">
<img width="782" alt="writeResult" src="https://user-images.githubusercontent.com/45925158/71575163-420fa400-2b2f-11ea-8844-3fdab9617509.PNG">

### 댓글
 * **댓글 등록**
 * **댓글 삭제**
<img width="762" alt="detail3" src="https://user-images.githubusercontent.com/45925158/71574902-20fa8380-2b2e-11ea-91ce-1d983a20bc57.PNG">
 
 
### 로그인 / 회원가입
 * **유효성 검사** - 로그인)해당 유저ID와 비밀번호가 회원가입정보의 데이터와 일치하는지 비지니스 로직을 통해 검사합니다.
                   회원가입)중복된 유저ID가 있는지 검사합니다.
 * **비밀번호 암호화** - BCryptPasswordEncoder객체의 passwordEncoder메소드를 사용하여 암호화한 후 비밀번호를 테이블에 저장했습니다. 
<img width="803" alt="login" src="https://user-images.githubusercontent.com/45925158/71574847-e5f85000-2b2d-11ea-8f05-9d6ad331c0c3.PNG">
<img width="788" alt="join" src="https://user-images.githubusercontent.com/45925158/71574869-fc061080-2b2d-11ea-8978-6e184c62b630.PNG">


### 기타 (프론트 앤드)
* 모바일에서 봤을때도 화면이 틀어지지 않도록 반응형웹으로 시도했습니다.
* 상단의 navigation Bar가 모든 페이지에서 보여지게 하기위해 jsp상속 라이브러리를 사용해 구현했습니다.

