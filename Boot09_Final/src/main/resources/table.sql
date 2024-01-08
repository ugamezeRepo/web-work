DROP TABLE user_tbl;
DROP TABLE board_gallery;
DROP SEQUENCE user_seq;
DROP SEQUENCE board_gallery_seq;

CREATE TABLE user_tbl(
	id NUMBER PRIMARY KEY,
	userName VARCHAR2(20) UNIQUE,
	password VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) UNIQUE,
	role VARCHAR2(10) NOT NULL
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

SELECT * FROM user_tbl;
SELECT * FROM board_gallery;