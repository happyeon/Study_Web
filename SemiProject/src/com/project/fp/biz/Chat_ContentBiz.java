package com.project.fp.biz;

import java.util.List;

import com.project.fp.dto.Chat_ContentDto;

public interface Chat_ContentBiz {
	
	public List<Chat_ContentDto> selectList();
	public List<Chat_ContentDto> selectOne(int ch_num);
	public int insert(Chat_ContentDto dto);
	public int update(Chat_ContentDto dto);
	public int delete(int ch_num);

}
