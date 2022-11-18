package com.ssafy.apartment.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apartment.model.HouseDto;

public interface HouseService {
    HouseDto searchByAptcode(String aptCode) throws SQLException;
    List<HouseDto> searchByDongCode(String dongCode) throws SQLException;
    List<HouseDto> searchByAptName(String apartmentName) throws SQLException;
	List<HouseDto> searchByDongName(String dongName) throws SQLException;
}
