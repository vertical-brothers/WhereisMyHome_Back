package com.ssafy.apartment.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apartment.model.StarDto;

public interface ApartmentDao {

	void addStar(StarDto starDto) throws SQLException;
	List<StarDto> listStar(String userid) throws SQLException;
	void deleteStar(int starNo) throws SQLException;
	
}
