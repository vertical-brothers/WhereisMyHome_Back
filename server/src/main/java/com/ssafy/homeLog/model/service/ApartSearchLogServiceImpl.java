package com.ssafy.homeLog.model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homeLog.model.ApartSearchLogDto;
import com.ssafy.homeLog.model.ApartSearchLogResultDto;
import com.ssafy.homeLog.model.mapper.ApartSearchLogMapper;

@Service
public class ApartSearchLogServiceImpl implements ApartSearchLogService {

	@Autowired
	private ApartSearchLogMapper apartSearchLogMapper;
	private final Logger logger = LoggerFactory.getLogger(ApartSearchLogServiceImpl.class);
	
	
	/*
	 * 아파트 검색 로그 기능
	 * input : ApartSearchLogDto
	 * 22.11.24
	 * */
	@Override
	@Transactional
	public void addLog(ApartSearchLogDto apartSearchLogDto) throws Exception {	
		logger.debug("ApartmentSearchLog addLog : {}", apartSearchLogDto);
		apartSearchLogMapper.addLog(apartSearchLogDto);
	}

	/*
	 * 아파트 검색 로그 기능 ! 주의. DTO select 결과에 맞춰져 있음
	 * input : ApartSearchLogResultDto
	 * 22.11.24
	 * */
	@Override
	public List<ApartSearchLogResultDto> getAllLog() throws SQLException {
		return apartSearchLogMapper.getAllLog();
	}

	@Override
	public List<ApartSearchLogResultDto> getTodayLog() throws SQLException {
		return apartSearchLogMapper.getTodayLog();
	}

}
