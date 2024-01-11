SELECT * FROM user_tbl;
SELECT * FROM board_gallery;
SELECT * FROM board_cafe;
-- LAG(칼럼명, 칸수, 존재하지 않을 시 기본값) OVER (정렬조건)
SELECT *
FROM
	(SELECT num, writer, caption, saveFileName,
		LAG(num, 1, 0) OVER (ORDER BY num DESC) prevNum,
		LEAD(num, 1, 0) OVER (ORDER BY num DESC) nextNum
	FROM board_gallery
	ORDER BY num DESC)
WHERE num=28;

DROP TABLE user_tbl;
DROP TABLE board_gallery;
DROP TABLE board_cafe;
DROP SEQUENCE user_seq;
DROP SEQUENCE board_gallery_seq;
DROP SEQUENCE board_cafe_seq;

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

