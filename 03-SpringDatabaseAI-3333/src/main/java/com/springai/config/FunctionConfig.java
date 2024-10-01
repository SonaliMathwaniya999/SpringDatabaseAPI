package com.springai.config;

import org.springframework.context.annotation.Configuration;

import com.springai.service.BooksService;

import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;


@Configuration
public class FunctionConfig {
	
	public static final String BOOKS_FUNCTION_NAME = "booksFunction";
	public static final String BOOKS_TOOL_NAME = "booksTool";
	public static final String BOOKS_FUNCTION_DESCRIPTION = "Use this function to execute an SQL query on the database.";
	
	@Bean(BOOKS_FUNCTION_NAME)
	public FunctionCallback booksFunction(BooksService booksService) {
		return FunctionCallbackWrapper.builder(booksService)
				.withName(BOOKS_FUNCTION_NAME).withDescription(BOOKS_FUNCTION_DESCRIPTION)
				.withResponseConverter(response -> response.getResult().toString())
				.build();
	}
	
	@Bean(BOOKS_TOOL_NAME)
	public OpenAiApi.FunctionTool booksFunctionTool() {
		String jsonTool = "{\n"
				+ "	\"type\": \"object\",\n"
				+ "	\"properties\": {\n"
				+ "		\"query\": {\n"
				+ "			\"type\": \"string\",\n"
				+ "			\"description\": \"Query to run in the database. Must be provided in plain text. The DB schema is defined by this DDL: CREATE TABLE 'SPRING_AI_TEST'.'BOOKS' ('TITLE' VARCHAR2(50 BYTE) NOT NULL ENABLE, 'AUTHOR' VARCHAR2(50 BYTE) NOT NULL ENABLE, PRIMARY KEY ('TITLE'))\" \n"
				+ "		}\n"
				+ "	},\n"
				+ "	\"required\": [\"query\"]\n"
				+ "}";
		
		OpenAiApi.FunctionTool.Function function = 
				new OpenAiApi.FunctionTool.Function(
						BOOKS_FUNCTION_DESCRIPTION, BOOKS_FUNCTION_NAME,
						ModelOptionsUtils.jsonToMap(jsonTool));
		
		return new OpenAiApi.FunctionTool(OpenAiApi.FunctionTool.Type.FUNCTION, function);
	}
	
}
