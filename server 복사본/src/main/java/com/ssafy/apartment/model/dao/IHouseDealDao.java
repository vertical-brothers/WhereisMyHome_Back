package com.ssafy.apartment.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apartment.model.HouseDealDto;

public interface IHouseDealDao {

    public List<HouseDealDto> list() throws SQLException;
    public List<HouseDealDto> listAll(String dong,String ym, String aptName) throws SQLException;
}
