package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SaveReqDto;


public class BoardService {

	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	//게시글 Insert
	public int boardWirte(SaveReqDto dto) {
		int result = boardDao.save(dto);
		return result;
	}
	
	//게시글 BoardList
	public List<Board> getBoardList(int page){	
		return  boardDao.findAll(page);
	} 
}
