package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.HouseDto;


@Mapper
public interface HouseMapper {
    HouseDto searchByAptcode(String aptCode) throws SQLException;
    List<HouseDto> searchByDongCode(String dongCode) throws SQLException;
    List<HouseDto> searchByAptName(String apartmentName) throws SQLException;
	List<HouseDto> searchByDongName(String dongName) throws SQLException;
}
