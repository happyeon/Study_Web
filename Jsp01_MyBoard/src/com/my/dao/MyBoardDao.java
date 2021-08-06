package com.my.dao;

import static com.my.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {
	
	public List<MyBoardDto> selectList() {
		Connection con = getConnection();
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				   + " FROM MYBOARD "
				   + " ORDER BY MYNO DESC ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비");
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				MyBoardDto dto = new MyBoardDto(
									rs.getInt("MYNO"),
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
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return list;
	}
	
	public MyBoardDto selectOne(int myno) {
		Connection con = getConnection();
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
			       + " FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyBoardDto dto = new MyBoardDto();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
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
	
	public int insert(MyBoardDto dto) {
		Connection con = getConnection();
		
		String sql = " INSERT INTO MYBOARD "
				   + " VALUES(MYBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("3. query 준비 : " + sql);
			
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
	
	public int update(MyBoardDto dto) {
		Connection con = getConnection();
		
		String sql = " UPDATE MYBOARD "
				   + " SET MYTITLE = ?, "
				   + " MYCONTENT = ? "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			System.out.println("3. query 준비 : " + sql);
			
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
	
	public int delete(int myno) {
		Connection con = getConnection();
		
		String sql = " DELETE FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비 : " + sql);
			
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
