package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apartment.model.HouseDealDto;
import com.ssafy.apartment.model.mapper.HousedealMapper;

@Service
public class HousedealServiceImpl implements HousedealService{
	
	private HousedealMapper housedealMapper;
	
	@Autowired
	public HousedealServiceImpl(HousedealMapper housedealMapper) {
		this.housedealMapper = housedealMapper;
	}
	
	@Override
	public List<HouseDealDto> list() throws Exception {
		System.out.println("HousedealServiceImpl");
		return housedealMapper.list();
	}

	@Override
	public List<HouseDealDto> listAll(Map<String, String> map) throws Exception {
		return housedealMapper.listAll(map);
	}

	
}
