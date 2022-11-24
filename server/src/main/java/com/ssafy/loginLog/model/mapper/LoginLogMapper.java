package com.ssafy.loginLog.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
	void addLoginLog() throws SQLException;
	int getLoginLog() throws SQLException;
}
