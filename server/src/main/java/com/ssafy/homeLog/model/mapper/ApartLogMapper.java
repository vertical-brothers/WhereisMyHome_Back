package com.ssafy.homeLog.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homeLog.model.ApartLogDto;

@Mapper
public interface ApartLogMapper {

	void addLog(String aptCode) throws SQLException;
	List<ApartLogDto> getLog() throws SQLException;
	void addLoginLog() throws SQLException;
//	List<integer> getloginCount throws
	
}
