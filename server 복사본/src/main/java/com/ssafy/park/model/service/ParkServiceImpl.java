package com.ssafy.park.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.park.model.ParkDto;
import com.ssafy.park.model.mapper.ParkMapper;

@Service
public class ParkServiceImpl implements ParkService{
	@Autowired
	private ParkMapper parkMapper;
	
	@Override
	public List<ParkDto> selectparks(String lat,String lng) throws SQLException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("lat", lat);
		map.put("lng", lng);
		map.put("distance", 5);
		return parkMapper.selectparks(map);
	}

	@Override
	public ParkDto selectpark(String parkName) throws SQLException {
		return parkMapper.selectpark(parkName);
	}

}
