package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.apartment.model.StarDto;

public interface StarService {

	void addStar(Map<String, String> map) throws Exception;
	List<StarDto> listStar(String userid) throws Exception;
	void deleteStar(int starNo) throws Exception;
}
