package com.muldel.dao;

import static com.muldel.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.muldel.dto.MDBoardDto;

public class MDBoardDaoImpl implements MDBoardDao {

	@Override
	public List<MDBoardDto> selectList() {
		// 1.
		// 2.
		Connection con = getConnection();
		
		// 3.
		Statement stmt = null;
		ResultSet rs = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3. query 준비 : " + SELECT_LIST_SQL);
			
			// 4.
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				MDBoardDto dto = new MDBoardDto(
									rs.getInt("SEQ"),
									rs.getString("WRITER"),
									rs.getString("TITLE"),
									rs.getString("CONTENT"),
									rs.getDate("REGDATE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.
			close(rs, stmt, con);
			System.out.println("5. db 종료");
		}
		
		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MDBoardDto dto = new MDBoardDto();
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비 : " + SELECT_ONE_SQL);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				// 이렇게 한 줄로 적을 수도 있다.
				// dto = new MDBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
			System.out.println("5. db 종료");
		}
		
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비 : " + INSERT_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3. query 준비 : " + UPDATE_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5. db 종료");
		}
		
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비 : " + DELETE_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

	@Override
	public int multiDelete(String[] seqs) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			System.out.println("3. query 준비");
			for (int i = 0; i < seqs.length; i++) {
				pstm.setString(1, seqs[i]);
				// 메모리에 sql문을 적재 후, executeBatch() 메소드가 호출되면 한 번에 실행!
				pstm.addBatch();
				System.out.println("삭제할 번호 : " + seqs[i]);
			}
			
			// 메모리에 저장된 sql문을 한 번에 실행!
			// 리턴 : int[]  / 배열로 리턴된다.
			// 성공 -2 / 실패 -3
			cnt = pstm.executeBatch();
			System.out.println("4. query 실행 및 리턴");
			for (int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2) {
					// 성공인 갯수 카운트
					res++;
				}
			}
			
			if (seqs.length == res) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}

}
