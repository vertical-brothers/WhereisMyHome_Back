package com.ssafy.loginLog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.loginLog.model.mapper.LoginLogMapper;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Override
	@Transactional
	public void addLoginLog() throws Exception {
		// TODO Auto-generated method stub
		loginLogMapper.addLoginLog();
	}

	@Override
	public int getLoginLog() throws Exception {
		// TODO Auto-generated method stub
		return loginLogMapper.getLoginLog();
	}

}
