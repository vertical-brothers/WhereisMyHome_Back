//package com.ssafy.apartment.model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.apartment.model.StarDto;
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.util.DBUtil;
//
//public class ApartmentDaoImpl implements ApartmentDao {
//
//	private static ApartmentDao apartmentDao = new ApartmentDaoImpl();
//	private DBUtil dbUtil;
//	
//	private ApartmentDaoImpl() {
//		dbUtil = DBUtil.getInstance();
//	}
//
//	public static ApartmentDao getApartmentDao() {
//		return apartmentDao;
//	}
//
//	@Override
//	public void addStar(StarDto starDto) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("insert into star (user_id, dongCode, dealYM) \n");
//			sql.append("values (?, ?, ?)" );
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, starDto.getUserId());
//			pstmt.setString(2, starDto.getDongCode());
//			pstmt.setString(3, starDto.getDealYM());
//			pstmt.executeUpdate();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//
//		
//	}
//
//	@Override
//	public List<StarDto> listStar(String userid) throws SQLException {
//		List<StarDto> list = new ArrayList<StarDto>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select s.star_no, d.dongCode, d.sidoName, d.gugunName, d.dongName, s.dealYM \n");
//			sql.append("from dongcode d join star s \n");
//			sql.append("on d.dongCode = s.dongCode \n");
//			sql.append("where user_id = ? \n");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, userid);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				StarDto starDto = new StarDto();
//				starDto.setStarNo(rs.getInt("star_no"));
//				starDto.setDongCode(rs.getString("dongCode"));
//				starDto.setSidoName(rs.getString("sidoName"));
//				starDto.setGugunName(rs.getString("gugunName"));
//				starDto.setDongName(rs.getString("dongName"));
//				starDto.setDealYM(rs.getString("dealYM"));
//				
//				list.add(starDto);
//			}
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//		return list;
//	}
//
//	@Override
//	public void deleteStar(int starNo) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("delete from star \n");
//			sql.append("where star_no = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, starNo);
//
//			pstmt.executeUpdate();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		
//	}
//	
//	
//
//}
