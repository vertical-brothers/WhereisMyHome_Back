package com.ssafy.homeLog.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homeLog.model.ApartLogDto;
import com.ssafy.homeLog.model.mapper.ApartLogMapper;


@Service
public class ApartLogServiceImpl implements ApartLogService{


	@Autowired
	private ApartLogMapper apartLogMapper;
	private final Logger logger = LoggerFactory.getLogger(ApartLogServiceImpl.class);
	
	@Override
	@Transactional
	public void addLog(String aptCode) throws Exception {
		// TODO Auto-generated method stub
		apartLogMapper.addLog(aptCode);
	}

	@Override
	public List<ApartLogDto> getLog() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("apartLogserviceImpl {}");
		return apartLogMapper.getLog(); 
	}

}
