package com.groupone.persistence;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groupone.entity.Journey;

@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {

	public List<Journey> searchJourneyByUserId(Integer userId);

	// Native Query
	@Modifying
	@Transactional
	@Query(value = "insert into journey values(:journeyId, :stationId, :userId, :swipeInS, :swipeOutS, :swipeInDT, :swipeOutDT, :journeyFare)", nativeQuery = true)
	int createJourney(@Param("journeyId") int journeyId, 
					@Param("stationId") int stationId, 
					@Param("userId") int userId,
					@Param("swipeInS") String swipeInStation, 
					@Param("swipeOutS") String swipeOutStation, 
					@Param("swipeInDT") LocalDateTime swipeInDateAndTime,
					@Param("swipeOutDT") LocalDateTime swipeOutDateAndTime,
					@Param("journeyFare") double jounryFare) throws SQLIntegrityConstraintViolationException;
}
