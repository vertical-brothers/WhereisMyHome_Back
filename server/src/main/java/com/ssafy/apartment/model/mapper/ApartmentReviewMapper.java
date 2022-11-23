package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.ApartmentReviewDto;


@Mapper
public interface ApartmentReviewMapper {
	List<ApartmentReviewDto> showReviewByAptcode(String aptCode) throws SQLException;
	void writeReview(ApartmentReviewDto apartmentReviewDto) throws SQLException;
	void updateReview(ApartmentReviewDto apartmentReviewDto) throws SQLException;
	void deleteReview(String id) throws SQLException;
	List<ApartmentReviewDto> showRecentReview(int limit) throws SQLException;
}
