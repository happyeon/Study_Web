package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.test.dto.testDto;

public class testDaoImpl extends SqlMapConfig implements testDao {
	
	private String namespace = "mvc.mapper.";

	@Override
	public List<testDto> selectList() {
		
		List<testDto> list = new ArrayList<testDto>();
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"selectList");
		}
		
		return list;
	}

	@Override
	public testDto selectOne(int seq) {
		
		SqlSession session = null;
		testDto dto = null;
	
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace+"selectOne", seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insert(testDto dto) {
		
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.insert(namespace+"insert",dto);
		}

		return res;
	}

	@Override
	public int update(testDto dto) {
		
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.update(namespace+"update", dto);
		}

		return res;
	}

	@Override
	public int delete(int seq) {
		
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.delete(namespace+"delete", seq);
		}

		return res;
	}

}
