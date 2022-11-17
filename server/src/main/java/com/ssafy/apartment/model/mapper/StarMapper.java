package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.StarDto;

@Mapper
public interface StarMapper {
	
	void addStar(Map<String, String> map) throws SQLException;
	List<StarDto> listStar(String userId) throws SQLException;
	void deleteStar(int starNo) throws SQLException;
}
