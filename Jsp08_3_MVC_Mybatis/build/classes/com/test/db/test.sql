 
DROP SEQUENCE TESTSEQ;
DROP TABLE TESTBOARD;

CREATE SEQUENCE TESTSEQ;

CREATE TABLE TESTBOARD(
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(100) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);

INSERT INTO TESTBOARD
VALUES(TESTSEQ.NEXTVAL, '강사', '오늘의 숙제', '어려운 부분 찾아주세요', SYSDATE);

SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE
FROM TESTBOARD
ORDER BY SEQ DESC;

SELECT WRITER
FROM TESTBOARD;