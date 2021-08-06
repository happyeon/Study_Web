package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MYDto;

public interface MYBiz {
	
	public List<MYDto> selectList();
	public MYDto selectOne(int myseq);
	public int insert(MYDto dto);
	public int update(MYDto dto);
	public int delete(int myseq);

}
