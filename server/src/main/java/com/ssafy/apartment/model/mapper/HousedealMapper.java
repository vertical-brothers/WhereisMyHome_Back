package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.HouseDealDto;

@Mapper
public interface HousedealMapper {
	List<HouseDealDto> list() throws SQLException;
    List<HouseDealDto> listAll(Map<String, String> map) throws SQLException;
}
