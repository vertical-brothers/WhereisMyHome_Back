package com.ssafy.homeLog.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homeLog.model.ApartSearchLogDto;
import com.ssafy.homeLog.model.ApartSearchLogResultDto;

@Mapper
public interface ApartSearchLogMapper {
	void addLog(ApartSearchLogDto apartSearchLogDto) throws SQLException;
	List<ApartSearchLogResultDto> getAllLog() throws SQLException;
	List<ApartSearchLogResultDto> getTodayLog() throws SQLException;
}
