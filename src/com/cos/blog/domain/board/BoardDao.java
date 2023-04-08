package com.cos.blog.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DB;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardDao {

	public int save(SaveReqDto dto) {
		String sql = "INSERT INTO BOARD(USERID, TITLE, CONTENT, CREATEDATE)"
							  + "VALUES(?,?,?,NOW())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return result;
	}
	
	public List<Board> findAll(int page){
		String sql = "SELECT * FROM BOARD ORDER BY ID DESC LIMIT ?,4";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page*4);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = Board.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.readCount(rs.getInt("readCount"))
						.userId(rs.getInt("userId"))
						.createDate(rs.getTimestamp("createDate"))
						.build();
				boards.add(board);
			}
			return boards;
		}catch(Exception e) {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
