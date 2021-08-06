package com.test.biz;

import java.util.List;

import com.test.dao.testDao;
import com.test.dao.testDaoImpl;
import com.test.dto.testDto;

public class testBizImpl implements testBiz {
	
	private testDao dao = new testDaoImpl();

	@Override
	public List<testDto> selectList() {
		return dao.selectList();
	}

	@Override
	public testDto selectOne(int seq) {
		return dao.selectOne(seq);
	}

	@Override
	public int insert(testDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(testDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}

}
