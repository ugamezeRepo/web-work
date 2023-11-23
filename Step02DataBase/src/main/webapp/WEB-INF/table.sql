-- 방명록 글을 저장할 테이블

CREATE TABLE board_guest (
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(30),
	content VARCHAR2(100) NOT NULL,
	pwd VARCHAR2(30) NOT NULL,
	regdate DATE
);

-- 방명록 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_guest_seq;