
DROP SEQUENCE CALBOARDSEQ;

DROP TABLE CALBOARD;

CREATE SEQUENCE CALBOARDSEQ;

CREATE TABLE CALBOARD(
	SEQ NUMBER PRIMARY KEY,
	ID VARCHAR2(1000) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	MDATE VARCHAR2(12) NOT NULL,			--일정
	REGDATE DATE NOT NULL
);

SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE
FROM CALBOARD;


-- row_number() over (partition by ?? order by ??)

SELECT *
FROM (
		SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE, 1, 8) ORDER BY MDATE)) RN, SEQ, ID, TITLE, CONTENT, MDATE, REGDATE 
		FROM CALBOARD
		WHERE ID = 'kh' AND SUBSTR(MDATE, 1, 6) = '202104'
		)
WHERE RN BETWEEN 1 AND 3;