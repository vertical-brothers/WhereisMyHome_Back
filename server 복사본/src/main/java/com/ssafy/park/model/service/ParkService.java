package com.ssafy.park.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.park.model.ParkDto;

public interface ParkService{
	List<ParkDto> selectparks(String lat,String lng) throws SQLException; 
	ParkDto selectpark(String parkName) throws SQLException;
}
