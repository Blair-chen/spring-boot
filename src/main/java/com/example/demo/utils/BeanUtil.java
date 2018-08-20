package com.example.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.StartInitCompareEdge;
import com.example.demo.model.FunctionClassCount;

@Component
public class BeanUtil
{
	/**
	 * Count the function class of a string
	 *
	 * @param str
	 * @return FunctionClassCount
	 */
	public FunctionClassCount countTheFunctionClass(final String str)
	{

		final FunctionClassCount functionClassCount = new FunctionClassCount(0, 0, 0, 0, 0);
		final String[] strAutoArr = StringUtils.split(str.substring(1, str.length() - 1), ",");

		System.out.println(StartInitCompareEdge.compareMapEdge.size());

		for (int i = 0; i < strAutoArr.length; i++)
		{
			if (StartInitCompareEdge.compareMapEdge.containsKey(strAutoArr[i]))
			{
				final int functionClass = StartInitCompareEdge.compareMapEdge.get(strAutoArr[i]);

				switch (functionClass)
				{
					case 0:
						functionClassCount.setFunctionClassZero(
								functionClassCount.getFunctionClassZero() + 1);
						break;
					case 1:
						functionClassCount
								.setFunctionClassOne(functionClassCount.getFunctionClassOne() + 1);
						break;
					case 2:
						functionClassCount
								.setFunctionClassTwo(functionClassCount.getFunctionClassTwo() + 1);
						break;
					case 3:
						functionClassCount.setFunctionClassThree(
								functionClassCount.getFunctionClassThree() + 1);
						break;
					case 4:
						functionClassCount.setFunctionClassFour(
								functionClassCount.getFunctionClassFour() + 1);

						break;
				}
			}

		}

		return functionClassCount;
	}

	/**
	 * Get the wayid of the specified zoom from a string of wayid strings
	 *
	 * @param zoom
	 * @param str
	 * @return String
	 */
	public String functionClassList(final int zoom, final String wayidStr)
	{
		final List<String> functionClassList = new ArrayList<String>();

		int functionClass = -1;
		final String[] strArr = StringUtils.split(wayidStr, ",");

		for (int i = 0; i < strArr.length; i++)
		{
			if (StartInitCompareEdge.compareMapEdge.containsKey(strArr[i].toString()))
			{
				functionClass = StartInitCompareEdge.compareMapEdge.get(strArr[i].toString());

				if (functionClass == zoom)
				{
					functionClassList.add(strArr[i].toString());
				}
			}
		}
		return functionClassList.toString();
	}

	/**
	 * Assign the value of jsonObject to Object by reflection
	 *
	 * @param target
	 * @param jsonObject
	 */
	public void Json2Object(final Object target, final JSONObject jsonObject)
	{
		final Field[] fields = target.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			if (jsonObject.containsKey(fields[i].getName()))
			{
				try
				{
					fields[i].setAccessible(true);
					fields[i].set(target, jsonObject.get(fields[i].getName()));
				}
				catch (IllegalArgumentException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Find the position of the nth split character in the String
	 * 
	 * @param str
	 * @param split
	 * @param i
	 * @return int
	 */
	public int ordIndexOf(final String str, final String split, final int n)
	{
		if (str.length() == 0 || str == null || split == null || n < 1)
		{
			return -1;
		}
		int count = 0;
		int index = -1;
		int in = -1;
		String s;
		do
		{
			s = str.substring(++index);
			in = s.indexOf(split);
			if (in != -1)
			{
				index += in;
				count++;
			}
			else
			{
				break;
			}
		}
		while (count < n);
		index = count < n ? -1 : index;
		return index;
	}
}
