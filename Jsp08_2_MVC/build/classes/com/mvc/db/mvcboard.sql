
DROP SEQUENCE MYMVCBOARDSEQ;
DROP TABLE MYMVCBOARD;

CREATE SEQUENCE MYMVCBOARDSEQ;

CREATE TABLE MYMVCBOARD(
	MYSEQ NUMBER PRIMARY KEY,
	MYNAME VARCHAR2(100) NOT NULL,
	MYTITLE VARCHAR2(1000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL
);

SELECT MYSEQ, MYNAME, MYTITLE, MYCONTENT, MYDATE
FROM MYMVCBOARD
ORDER BY MYSEQ DESC;
