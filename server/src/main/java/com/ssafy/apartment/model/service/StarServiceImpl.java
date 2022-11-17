package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.apartment.model.StarDto;
import com.ssafy.apartment.model.mapper.StarMapper;

@Service
public class StarServiceImpl implements StarService {

	private StarMapper apartmentMapper;
	
	@Autowired
	public StarServiceImpl(StarMapper apartmentMapper) {
		this.apartmentMapper = apartmentMapper;
	}
	@Override
	@Transactional
	public void addStar(Map<String, String> map) throws Exception {
		apartmentMapper.addStar(map);
	}

	@Override
	public List<StarDto> listStar(String userid) throws Exception {
		return apartmentMapper.listStar(userid);
	}

	@Override
	@Transactional
	public void deleteStar(int starNo) throws Exception {
		apartmentMapper.deleteStar(starNo);
	}
	
	
}
