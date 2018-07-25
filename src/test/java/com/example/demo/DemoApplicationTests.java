package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Calendar;

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
import com.example.demo.mapper.SpeedMapper;
import com.example.demo.mapper.WayAndDateMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests
{

	public static int binSearch(final int srcArray[], final int start, final int end, final int key)
	{
		final int mid = (end - start) / 2 + start;
		if (srcArray[mid] == key)
		{
			return srcArray[mid];
		}
		if (start >= end)
		{
			return srcArray[start - 1];
		}
		else if (key > srcArray[mid])
		{
			return binSearch(srcArray, mid + 1, end, key);
		}
		else if (key < srcArray[mid])
		{
			return binSearch(srcArray, start, mid - 1, key);
		}
		return -1;
	}

	private MockMvc mockMvc;
	@Autowired
	private WayAndDateMapper wayAndDateMapper;

	@Autowired
	private SpeedMapper speedMapper;

	@Test
	public void beanTest()
	{
		// final Speed s = new Speed();
		// s.setWayid(123);
		// s.setSpeed(23);
		// final SpeedVo svo = new SpeedVo();
		// BeanUtil.copyPro(s, svo);
		// // BeanUtil.copyPro(s, svo);
		final int[] i = new int[] { 0, 1, 2, 4, 5, 7, 20 };
		final int t = this.binSearch(i, 0, i.length - 1, 10);
		System.out.println(t);
	}

	public int binarySearch(final int[] speeds, final int date, final int min, final int max)
	{
		final int middle2 = min + max;
		if (speeds[middle2 / 2 - 1] > date && max > min)
		{
			this.binarySearch(speeds, date, min, middle2 / 2 - 1);
		}
		else if (speeds[middle2 / 2 - 1] < date && max > min)
		{
			this.binarySearch(speeds, date, middle2 / 2 - 1, max);
		}
		else if (speeds[middle2 / 2 - 1] == date)
		{
			return speeds[middle2 / 2 - 1];
		}
		else if (min == max)
		{
			return speeds[min];
		}
		return 100;
	}

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

		// nowTime.add(field, amount);
		for (int i = 0; i < 500; i++)
		{
			final Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.MINUTE, i);

			this.speedMapper.insert(i + "10", 1143235428, 23, nowTime.getTime(), "2018-07-12");
		}

		// System.out.println(this.speedMapper.findSpeedByWayidAndDate(1143235428, "2018-07-12"));
	}
}
