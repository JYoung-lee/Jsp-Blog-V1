package com.cos.blog.service;

import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardService {

	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int boardWirte(SaveReqDto dto) {
		int result = boardDao.save(dto);
		return result;
	}
}
