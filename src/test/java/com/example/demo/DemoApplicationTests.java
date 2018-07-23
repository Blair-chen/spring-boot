package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoControlller;
import com.example.demo.mapper.WayAndDateMapper;
import com.example.demo.model.WayAndDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests
{

	private MockMvc mockMvc;

	@Autowired
	private WayAndDateMapper wayAndDateMapper;

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

	@Test
	public void test()
	{

		final List<WayAndDate> list = this.wayAndDateMapper.findWayAndDateById(761699319);
		System.out.println(list);
	}

}
