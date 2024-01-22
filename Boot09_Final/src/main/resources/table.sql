SELECT * FROM user_tbl;
SELECT * FROM board_gallery;
SELECT * FROM board_cafe;
SELECT * FROM board_cafe_comment;
SELECT * FROM board_file;

-- SELECT num, writer, title, orgFileName, saveFileName, fileSize, regdate FROM board_file;
-- LAG(칼럼명, 칸수, 존재하지 않을 시 기본값) OVER (정렬조건)
SELECT *
FROM
	(SELECT num, writer, caption, saveFileName,
		LAG(num, 1, 0) OVER (ORDER BY num DESC) prevNum,
		LEAD(num, 1, 0) OVER (ORDER BY num DESC) nextNum
	FROM board_gallery
	ORDER BY num DESC)
WHERE num=28;

-- 페이징 처리를 하기위해서는 1. 정렬 2. 행번호 부여 3. 원하는 행만 select
SELECT *
FROM
	(SELECT result1.*, ROWNUM AS rnum
	FROM
		(SELECT num, writer, title, orgFileName, fileSize, regdate
		FROM board_file
		ORDER BY num DESC) result1)
WHERE rnum BETWEEN 6 AND 10

-- board_file
SELECT *
FROM
	(SELECT result1.*, ROWNUM AS rnum
	FROM
		(SELECT num, writer, title, orgFileName, saveFileName, fileSize, TO_CHAR(regdate, 'YYYY.MM.DD HH24:MI') AS regdate
		FROM board_file
		ORDER BY num DESC) result1)
WHERE rnum BETWEEN 1 AND 10

DROP TABLE user_tbl;
DROP TABLE board_gallery;
DROP TABLE board_cafe;
DROP TABLE board_cafe_comment;
DROP TABLE board_file;
DROP SEQUENCE user_seq;
DROP SEQUENCE board_gallery_seq;
DROP SEQUENCE board_cafe_seq;
DROP SEQUENCE board_cafe_comment_seq;
DROP SEQUENCE board_file_seq; 

CREATE TABLE user_tbl(
	id NUMBER PRIMARY KEY,
	userName VARCHAR2(20) UNIQUE,
	password VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) UNIQUE,
	role VARCHAR2(10) NOT NULL,
	profile VARCHAR2(100),
	regdate DATE
);
CREATE SEQUENCE user_seq;

CREATE TABLE board_gallery (
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),
	caption VARCHAR2(100), -- 이미지에 대한 설명
	saveFileName VARCHAR2(100), -- 업로드된 이미지 이름
	regdate DATE -- 이미지 업로드 날짜
);
CREATE SEQUENCE board_gallery_seq;

CREATE TABLE board_cafe (
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),
	title VARCHAR2(100), 
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	regdate DATE
);
CREATE SEQUENCE board_cafe_seq;

-- 댓글을 저장할 테이블
CREATE TABLE board_cafe_comment (
	num NUMBER PRIMARY KEY, 	-- 댓글의 글번호
	writer VARCHAR2(100),		-- 댓글 작성자의 아이디
	content VARCHAR2(500),		-- 댓글 내용
	target_id VARCHAR2(100), 	-- 댓글의 당사자 아이디
	ref_group NUMBER,			-- 원글의 글번호
	comment_group NUMBER,		-- 댓글의 그룹번호
	deleted CHAR(3) DEFAULT 'no',	-- 삭제된 댓글인 지 여부 'yes' or 'no'
	regdate DATE
);
-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_comment_seq;

-- 업로드된 파일의 정보를 저장할 테이블
CREATE TABLE board_file(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	orgFileName VARCHAR2(100) NOT NULL, -- 원본 파일명
	saveFileName VARCHAR2(100) NOT NULL, -- 서버에 실제로 저장된 파일명
	fileSize NUMBER NOT NULL, -- 파일의 크기 
	regdate DATE
);
CREATE SEQUENCE board_file_seq; 