package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Speed;
import com.example.demo.model.SpeedVo;
import com.example.demo.utils.BeanUtil;

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

	@Test
	public void beanTest()
	{
		final Speed s = new Speed();
		s.setId(12345);
		s.setWayid(123);
		s.setSpeed(23);
		final SpeedVo svo = new SpeedVo();
		BeanUtil.copyBeanNotNull2Bean(s, svo);
		// BeanUtil.copyPro(s, svo);
		// final int[] i = new int[] { 0, 1, 2, 4, 5, 7, 20 };
		// final int t = this.binSearch(i, 0, i.length - 1, 10);
		// System.out.println(t);
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

	public int SpecIndexOfStr(final String str, final char s, final int i, int index)
	{
		if (i > 0)
		{
			index += str.indexOf(s);
			SpecIndexOfStr(str.substring(str.indexOf(s), str.length()), s, i - 1, index);
		}
		else
		{
			return index;
		}
		return 0;

	}

	@Test
	public void test() throws Exception
	{

		final String str = "";
		final Map<String, String> map = new HashMap<String, String>();
		map.containsKey("id");
		System.out.println(str.length());

	}
}
