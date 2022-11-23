package com.ssafy.apartment.model.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apartment.model.ApartmentReviewDto;
import com.ssafy.apartment.model.mapper.ApartmentReviewMapper;

@Service
public class ApartmentReviewServiceImpl implements ApartmentReviewService {
	
	private ApartmentReviewMapper apartmentReviewMapper;
	
	
	@Autowired
	public ApartmentReviewServiceImpl(ApartmentReviewMapper apartmentReviewMapper) {
		this.apartmentReviewMapper = apartmentReviewMapper;
	}

	@Override
	public List<ApartmentReviewDto> showReviewByAptcode(String aptCode) throws SQLException {
		return apartmentReviewMapper.showReviewByAptcode(aptCode);
	}

	@Override
	public void writeReview(ApartmentReviewDto apartmentReviewDto) throws SQLException {
		apartmentReviewMapper.writeReview(apartmentReviewDto);
	}

	@Override
	public void updateReview(ApartmentReviewDto apartmentReviewDto) throws SQLException {
		apartmentReviewMapper.updateReview(apartmentReviewDto);
	}

	@Override
	public void deleteReview(String id) throws SQLException {
		apartmentReviewMapper.deleteReview(id);
	}

	@Override
	public List<ApartmentReviewDto> showRecentReview(int limit) throws SQLException {
		// TODO Auto-generated method stub
		return apartmentReviewMapper.showRecentReview(limit);
	}

}
