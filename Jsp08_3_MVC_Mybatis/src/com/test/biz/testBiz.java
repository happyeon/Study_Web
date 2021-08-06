package com.test.biz;

import java.util.List;

import com.test.dto.testDto;

public interface testBiz {
	
	public List<testDto> selectList();
	public testDto selectOne(int seq);
	public int insert(testDto dto);
	public int update(testDto dto);
	public int delete(int seq);

}
