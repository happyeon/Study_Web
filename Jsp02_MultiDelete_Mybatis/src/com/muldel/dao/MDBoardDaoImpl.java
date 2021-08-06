package com.muldel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.muldel.dto.MDBoardDto;

public class MDBoardDaoImpl extends SqlMapConfig implements MDBoardDao {
	
	private String namespace = "muldel.mapper.";

	@Override
	public List<MDBoardDto> selectList() {	
		
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		// try() 괄호 안에 들어간 참조변수의 리소스는 구문이 끝날 때 자동으로 해제해줌
		// closeable 인터페이스를 상속한 객체만이 try 소괄호 안으로 들어올 수 있다.
		// SqlSession : The primary Java interface for working with MyBatis. Through this interface you can execute commands, get mappers and manage transactions.
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"selectList");
		}
		
		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		
		SqlSession session = null;
		MDBoardDto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOne", seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		
		int res = 0;
		
		try (SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.insert(namespace+"insert",dto);
		}
		
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.update(namespace+"update", dto);
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		
		int res = 0;
		
		try (SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.delete(namespace+"delete", seq);
		}
		
		return res;
	}

	@Override
	public int multiDelete(String[] seqs) {
		
		int count = 0;
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			count = session.delete(namespace+"multiDelete", map);
			if (count == seqs.length) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return count;
	}

}
