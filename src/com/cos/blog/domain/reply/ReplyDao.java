package com.cos.blog.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DB;
import com.cos.blog.domain.reply.dto.SavaReqDto;

public class ReplyDao {

	
	public int saveReply(SavaReqDto dto) {
		String sql = "INSERT INTO REPLY(USERID, BOARDID, CONTENT, CREATEDATE)"
				   + "VALUES(?,?,?,NOW())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generateKey;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			//테이블의 생성된 키를 가져온다. getGeneratedKeys를 사용하려면 위에 Statement.RETURN_GENERATED_KEYS선언해줘야한다. 
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				generateKey = rs.getInt(1);
				return generateKey; 
			}
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return -1;
		
	}
	
	public Reply replyFindById(int id) {
		String sql = "SELECT * FROM REPLY WHERE ID=?";
		
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reply reply = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = Reply.builder()
						.id(rs.getInt("ID"))
						.userId(rs.getInt("USERID"))
						.boardId(rs.getInt("BOARDID"))
						.content(rs.getString("CONTENT"))
						.build();
				return reply;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return reply;
	}
	
	public List<Reply> findAll(int boardId) {
		String sql = "SELECT * FROM REPLY WHERE BOARDID=? ORDER BY ID DESC";
		
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reply reply = null;
		List<Reply> replys = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reply = Reply.builder()
						.id(rs.getInt("ID"))
						.userId(rs.getInt("USERID"))
						.boardId(rs.getInt("BOARDID"))
						.content(rs.getString("CONTENT"))
						.build();
				replys.add(reply);
			}
			
			return replys;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		
		return replys;
	}
	
	
	public int deleteById(int id) {
		String sql = "DELETE FROM REPLY WHERE ID = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		
		return -1;
		
	}
	
	
}
