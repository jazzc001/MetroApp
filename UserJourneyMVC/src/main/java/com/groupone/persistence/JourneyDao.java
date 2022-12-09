package com.groupone.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.entity.Journey;

@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {

	public List<Journey> searchJourneyByUserId(Integer userId);
	
}
