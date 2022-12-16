package com.groupone.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.entity.Journey;

@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {

	List<Journey> searchJourneyByUserId(Integer userId);

	Journey searchJourneyByJourneyId(int journeyId);

//	@Query(value = "select max(journeyId) from journey", nativeQuery = true)
//	int findMaxJourneyId();

//	@Modifying
//	@Transactional
//	@Query(value = "insert into journey values(:journeyId, :journeyFare, :swipeInDateTime, :swipeIn, :swipeOutDateTime,:swipeOut, :userId", nativeQuery = true)
//	int insertJourney(Journey journey);

	// Native Query
//	@Modifying
//	@Transactional
//	@Query(value = "insert into journey values(:userId, :swipeInS, :swipeOutS, :swipeInDT, :swipeOutDT, :journeyFare)", nativeQuery = true)
//	int createJourney(@Param("userId") int userId, @Param("swipeInS") String swipeInStation,
//			@Param("swipeOutS") String swipeOutStation, @Param("swipeInDT") LocalDateTime swipeInDateAndTime,
//			@Param("swipeOutDT") LocalDateTime swipeOutDateAndTime, @Param("journeyFare") double jounryFare)
//			throws SQLIntegrityConstraintViolationException;
}
