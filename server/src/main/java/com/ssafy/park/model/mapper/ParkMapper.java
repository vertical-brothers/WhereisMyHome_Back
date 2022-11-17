package com.ssafy.park.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.park.model.ParkDto;

@Mapper
public interface ParkMapper {

	List<ParkDto> selectparks(Map<String,Object> latlng);

	ParkDto selectpark(String parkName);

}
