package com.springai.dto;

import java.util.List;
import java.util.Map;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString

public class BookResponse {

	
	private List<Map<String, Object>> result;
	
	
	
}
