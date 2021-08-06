package com.test.dao;

import java.util.List;

import com.test.dto.testDto;

public interface testDao {
	
	String SELECT_LIST_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM TESTBOARD ORDER BY SEQ DESC ";
	String SELECT_ONE_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM TESTBOARD WHERE SEQ = ? ";
	String INSERT_SQL = " INSERT INTO TESTBOARD VALUES(TESTSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
	String UPDATE_SQL = " UPDATE TESTBOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
	String DELETE_SQL = " DELETE FROM TESTBOARD WHERE SEQ = ? ";
	
	public List<testDto> selectList();
	public testDto selectOne(int seq);
	public int insert(testDto dto);
	public int update(testDto dto);
	public int delete(int seq);

}
