package com.cos.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.blog.config.DB;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDao;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.domain.user.dto.UpdateReqDto;


public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	//회원가입
	public int userInsert(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}
	
	//회원수정
	public int userModify(UpdateReqDto dto) {
		
		return -1;
	}
	
	//로그인
	// SELETE * FROM USER WHERE USERNAME=? AND PASSWORD = ?
	public User login(LoginReqDto dto) {
		
		return null;
	}
	
	//아이디중복체크
	public int idDubChk(String username) {
		
		return -1;
	}
	

	
}
