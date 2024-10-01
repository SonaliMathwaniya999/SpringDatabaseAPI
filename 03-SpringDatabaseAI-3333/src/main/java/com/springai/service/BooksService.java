package com.springai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.springai.dto.BookRequest;
import com.springai.dto.BookResponse;

@Service
public class BooksService implements Function<BookRequest , BookResponse> {
	
	
	
	@Autowired
	protected NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public BookResponse apply(BookRequest t) {
		// TODO Auto-generated method stub
		
		 List<Map<String, Object>> result = 
				 namedJdbcTemplate.query(t.getQuery(), new HashMap<>(), new ColumnMapRowMapper());

		 BookResponse response= new BookResponse();
		
		 response.setResult(result);
		
		return response;
	}
	
	
	
	
	
	
}