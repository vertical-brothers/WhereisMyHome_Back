package com.ssafy.apartment.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apartment.model.HouseDto;
import com.ssafy.apartment.model.mapper.HouseMapper;
import com.ssafy.board.model.mapper.BoardMapper;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseMapper houseMapper;

	@Autowired
	public HouseServiceImpl(HouseMapper houseMapper) {
		this.houseMapper = houseMapper;
	}
	
	@Override
	public HouseDto searchByAptcode(String aptCode) throws SQLException {
		return houseMapper.searchByAptcode(aptCode);
	}

	@Override
	public List<HouseDto> searchByDongCode(String dongCode) throws SQLException {
		// TODO Auto-generated method stub
		return houseMapper.searchByDongCode(dongCode);
	}

	@Override
	public List<HouseDto> searchByAptName(String apartmentName) throws SQLException {
		// TODO Auto-generated method stub
		return houseMapper.searchByAptName(apartmentName);
	}

	@Override
	public List<HouseDto> searchByDongName(String dongName) throws SQLException {
		// TODO Auto-generated method stub
		return houseMapper.searchByDongName(dongName);
	}

}
