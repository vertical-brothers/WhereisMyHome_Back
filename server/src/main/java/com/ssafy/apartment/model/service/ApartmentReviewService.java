package com.ssafy.apartment.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.apartment.model.ApartmentReviewDto;

public interface ApartmentReviewService {
	List<ApartmentReviewDto> showReviewByAptcode(String aptCode) throws SQLException;
	void writeReview(ApartmentReviewDto apartmentReviewDto) throws SQLException;
	void updateReview(ApartmentReviewDto apartmentReviewDto) throws SQLException;
	void deleteReview(String id) throws SQLException;
	List<ApartmentReviewDto> showRecentReview(int limit) throws SQLException;
}
