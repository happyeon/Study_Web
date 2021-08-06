package com.project.fp.biz;

import java.util.List;

import com.project.fp.dao.AnimalDao;
import com.project.fp.dao.AnimalDaoImpl;
import com.project.fp.dto.AnimalDto;

public class AnimalBizImpl implements AnimalBiz {

	AnimalDao dao = new AnimalDaoImpl();
	@Override
	public List<AnimalDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public AnimalDto selectOne(int animal_no) {
		// TODO Auto-generated method stub
		return dao.selectOne(animal_no);
	}

	@Override
	public int insert(AnimalDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(AnimalDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int animal_no) {
		// TODO Auto-generated method stub
		return dao.delete(animal_no);
	}

	@Override
	public AnimalDto selectoneDetail(String member_id) {
		// TODO Auto-generated method stub
		return dao.selectoneDetail(member_id);
	}

}
