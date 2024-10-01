package com.springai.controller;


import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiApi.FunctionTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
//	private final OpenAiChatClient  chatClient;
	private final ChatModel chatModel;
	private final OpenAiApi.FunctionTool booksTool;
	
	@Autowired
	public BookController(ChatModel chatModel, OpenAiApi.FunctionTool booksTool) {
		this.booksTool = booksTool;
		this.chatModel = chatModel;
	}
	
	@GetMapping("/ai/books")
	public Generation books(@RequestParam(value = "message") String message) {
		new UserMessage(message);
		
		List<FunctionTool> toolsList = List.of(booksTool);
		Prompt prompt = new Prompt(message, OpenAiChatOptions.builder()
				.withTools(toolsList)
				.build());
		
	 ChatResponse response = chatModel.call(prompt);
		return response.getResult();
	}

}





