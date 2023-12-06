-- 사용자(회원) 정보를 저장할 테이블
--CREATE TABLE user_info (
--	id VARCHAR2(100) CONSTRAINT user_info_id_pk PRIMARY KEY,
--	pwd VARCHAR2(100) CONSTRAINT user_info_pwd_nn NOT NULL,
--	email VARCHAR2(100), 
--	profile VARCHAR2(100), -- 프로필 이미지 경로를 저장할 컬럼
--	regdate DATE
--);

-- SELECT id, pwd, email, profile, regdate FROM user_info;

-- 업로드된 파일의 정보를 저장할 테이블
--CREATE TABLE board_file(
--	num NUMBER PRIMARY KEY,
--	writer VARCHAR2(100) NOT NULL,
--	title VARCHAR2(100) NOT NULL,
--	orgFileName VARCHAR2(100) NOT NULL, -- 원본 파일명
--	saveFileName VARCHAR2(100) NOT NULL, -- 서버에 실제로 저장된 파일명
--	fileSize NUMBER NOT NULL, -- 파일의 크기 
--	regdate DATE
--);
--
--CREATE SEQUENCE board_file_seq; 

-- SELECT num, writer, title, orgFileName, saveFileName, fileSize, regdate FROM board_file;

-- 페이징 처리를 하기위해서는 1. 정렬 2. 행번호 부여 3. 원하는 행만 select
--SELECT *
--FROM
--	(SELECT result1.*, ROWNUM AS rnum
--	FROM
--		(SELECT num, writer, title, orgFileName, fileSize, regdate
--		FROM board_file
--		ORDER BY num DESC) result1)
--WHERE rnum BETWEEN 6 AND 10

-- 게시글을 저장할 테이블
CREATE TABLE board_cafe (
	num NUMBER PRIMARY KEY, -- 글번호
	writer VARCHAR2(100) NOT NULL, -- 작성자 (로그인된 아이디)
	title VARCHAR2(100) NOT NULL, -- 제목
	content CLOB, -- 글 내용
	viewCount NUMBER, -- 조회수
	regdate DATE -- 글 작성일
);

-- 게시글의 번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_seq;