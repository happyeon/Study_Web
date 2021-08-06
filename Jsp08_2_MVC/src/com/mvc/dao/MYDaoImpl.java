package com.mvc.dao;

import static com.mvc.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MYDto;

public class MYDaoImpl implements MYDao {

	@Override
	public List<MYDto> selectList() {
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MYDto> list = new ArrayList<MYDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3. query 준비 : "+ SELECT_LIST_SQL);
			
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				MYDto dto = new MYDto(
							rs.getInt("MYSEQ"),
							rs.getString("MYNAME"),
							rs.getString("MYTITLE"),
							rs.getString("MYCONTENT"),
							rs.getDate("MYDATE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("5. db 종료");
		}

		return list;
	}

	@Override
	public MYDto selectOne(int myseq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MYDto dto = new MYDto();
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, myseq);
			System.out.println("3. query 준비 : " + SELECT_ONE_SQL);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto = new MYDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}

		return dto;
	}

	@Override
	public int insert(MYDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("3. query 준비 : " + INSERT_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

	@Override
	public int update(MYDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyseq());
			
			System.out.println("3. query 준비 : " + UPDATE_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

	@Override
	public int delete(int myseq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, myseq);
			System.out.println("3. query 준비 : " + DELETE_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

}
