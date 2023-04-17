package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.ReplyDao;
import com.cos.blog.domain.reply.dto.SavaReqDto;

public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int saveReply(SavaReqDto dto) {
		return replyDao.saveReply(dto);	
	}

	public Reply replyFindById(int id) {
		return replyDao.replyFindById(id);
	}
	
	public List<Reply> replyListById(int boardId){
		return replyDao.findAll(boardId);
	}
	
	public int deleteById(int id) {
		return replyDao.deleteById(id);
	}
}
