package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MYDao;
import com.mvc.dao.MYDaoImpl;
import com.mvc.dto.MYDto;

public class MYBizImpl implements MYBiz {
	
	private MYDao dao = new MYDaoImpl();
	
	
	
	@Override
	public List<MYDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MYDto selectOne(int myseq) {
		return dao.selectOne(myseq);
	}

	@Override
	public int insert(MYDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MYDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int myseq) {
		return dao.delete(myseq);
	}

}
