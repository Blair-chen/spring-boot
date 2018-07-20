package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoControlller;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests
{

	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception
	{
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/findPerson").accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());
	}

	@Before
	public void setUp() throws Exception
	{
		this.mockMvc = MockMvcBuilders.standaloneSetup(new DemoControlller()).build();
	}

}
