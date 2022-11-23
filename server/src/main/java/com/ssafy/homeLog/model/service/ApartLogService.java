package com.ssafy.homeLog.model.service;

import java.util.List;

import com.ssafy.homeLog.model.ApartLogDto;

public interface ApartLogService {
	void addLog(String aptCode) throws Exception;
	List<ApartLogDto> getLog() throws Exception;
}
