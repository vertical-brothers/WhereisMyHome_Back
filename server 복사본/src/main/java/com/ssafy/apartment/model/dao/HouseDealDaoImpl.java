package com.ssafy.apartment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.apartment.model.HouseDealDto;
import com.ssafy.util.DBUtil;

public class HouseDealDaoImpl implements IHouseDealDao {
    DBUtil db;
    private static IHouseDealDao housedealdao = new HouseDealDaoImpl();

    private HouseDealDaoImpl() {
        db = DBUtil.getInstance();
    }

    public static IHouseDealDao getInstance() {
        return housedealdao;
    }

    @Override
    public List<HouseDealDto> list() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" select no,aptCode,dealAmount,dealYear,dealMonth,dealDay,area,floor ");
        sql.append(" from housedeal order by no ");
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<HouseDealDto> housedeals = new ArrayList<>();
        try {
            conn = db.getConnection();
            psmt = conn.prepareStatement(sql.toString());
            rs = psmt.executeQuery();
            while (rs.next()) {
                int i = 1;
                HouseDealDto housedeal = new HouseDealDto(
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++));
                housedeals.add(housedeal);
            }
        } finally {
            db.close(rs, psmt, conn);
        }
        return housedeals;
    }
    
    @Override
    public List<HouseDealDto> listAll(String dong, String ym, String aptName) throws SQLException {
    	
    	String y="";
    	String m="";
    	aptName='%'+aptName+'%';
    	
    	if(ym.length()==6) {
    		y = ym.substring(0, 4);
    		m = ym.substring(4, 6);
    	}
    	else if(ym.length()==4) {
    		y = ym.substring(0, 4);
    	}
    	else if(ym.length()==0) {

    	}
        
        StringBuilder sql = new StringBuilder();
        
        sql.append(" select hd.no,hd.aptCode,hd.dealAmount,hd.dealYear,hd.dealMonth,hd.dealDay,hd.area,hd.floor"
        		+ ",hi.apartmentName "
        		+ ",d.dongName "
        		+ ",hi.roadName,hi.roadNamebonbun "
        		+ "");
        sql.append(" from housedeal as hd "); 
        sql.append("	join houseinfo as hi on hd.aptCode=hi.aptCode "); 
        sql.append("	join dongcode d on hi.dongCode = d.dongCode "); 
        if(ym.length()==6) {
        	sql.append("	where hi.dongCode = ? "); 
        	sql.append("	  and hd.dealYear = ? "); 
        	sql.append("	  and hd.dealMonth = ? ");
        }
        else if(ym.length()==4) {
        	sql.append("	where hi.dongCode = ? "); 
        	sql.append("	  and hd.dealYear = ? ");
        }
        else if(ym.length()==0) {
        	sql.append("	  where hi.dongCode = ? ");
        }
        if(aptName.length()!=0) { //아파트명 입력이 됐으면
        	sql.append("	  and hi.apartmentName like ? ");	
        }
        sql.append(" order by d.dongName, hi.apartmentName ");
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        List<HouseDealDto> housedeals = new ArrayList<>();
        
        try {
            conn = db.getConnection();
            psmt = conn.prepareStatement(sql.toString());
            int index = 1;
            if(ym.length()==6) {
            	psmt.setString(index++, dong);
            	psmt.setString(index++, y);
            	psmt.setString(index++, m);
            }
            else if(ym.length()==4) {
            	psmt.setString(index++, dong);
            	psmt.setString(index++, y);
            }
            else if(ym.length()==0) {
            	psmt.setString(index++, dong);
            }
            if(aptName.length()!=0) {
            	psmt.setString(index++, aptName);
            	//System.out.println(psmt.toString());
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                int i = 1;
                HouseDealDto housedeal = new HouseDealDto(
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++));
                housedeals.add(housedeal);
                
            }
            System.out.println(housedeals.toString());
            
        } finally {
            db.close(rs, psmt, conn);
        }
        return housedeals;
    }
}