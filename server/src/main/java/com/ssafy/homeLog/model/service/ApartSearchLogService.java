package com.ssafy.homeLog.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homeLog.model.ApartSearchLogDto;
import com.ssafy.homeLog.model.ApartSearchLogResultDto;

public interface ApartSearchLogService {
	void addLog(ApartSearchLogDto apartSearchLogDto) throws Exception;
	List<ApartSearchLogResultDto> getAllLog() throws SQLException;
	List<ApartSearchLogResultDto> getTodayLog() throws SQLException;
}
