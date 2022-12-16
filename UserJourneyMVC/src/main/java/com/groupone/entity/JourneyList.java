package com.groupone.entity;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JourneyList {
	
	List<Journey> journeyList;
	
}
