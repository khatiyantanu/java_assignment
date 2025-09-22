package com.transunion.project.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSayHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
				.andExpect(content().string("Hello from Spring Boot!"));
	}

}
