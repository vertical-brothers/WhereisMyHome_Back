package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	int idCheck(String userId) throws SQLException; // 아이디 중복검사
	void joinMember(MemberDto memberDto) throws SQLException; // 회원가입
	MemberDto login(MemberDto memberDto) throws SQLException; // 로그인
	MemberDto getMember(String userId) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	List<MemberDto> listMember() throws SQLException;	
	
	// 토큰 관련 로직
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
