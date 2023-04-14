package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DeleteReqDto;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;


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
		return boardDao.findAll(page);
	}
	
	//게시글 전체 Count
	public int getArticleCount(){	
		return boardDao.getArticleCount();
	}
	
	public DetailRespDto getDetailArticle(int id){
		//readcount 업데이트
		int result = boardDao.readCountUp(id);
		if(result == 1) {
			return boardDao.findById(id);
		}else {
			return null;
		}
		
	}
	
	public int deleteById(DeleteReqDto dto) {
		return boardDao.deleteById(dto); 
	}

	public int updateById(UpdateReqDto dto) {
		return boardDao.updateById(dto); 
	}
	
}
