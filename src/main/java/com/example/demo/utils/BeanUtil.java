package com.example.demo.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.StartInitCompareEdge;
import com.example.demo.model.FunctionClassCount;

public class BeanUtil
{
	/**
	 * copy bean
	 *
	 * @param source
	 * @param target
	 */
	public static void copyBeanNotNull2Bean(final Object source, final Object target)
	{
		final PropertyDescriptor sourceDescriptors[] = PropertyUtils.getPropertyDescriptors(source);
		for (int i = 0; i < sourceDescriptors.length; i++)
		{
			final String name = sourceDescriptors[i].getName();
			if ("class".equals(name))
			{
				continue; // No point in trying to set an object's class
			}
			if (PropertyUtils.isReadable(source, name) && PropertyUtils.isWriteable(target, name))
			{
				try
				{
					final Object value = PropertyUtils.getSimpleProperty(source, name);
					if (value != null)
					{
						PropertyUtils.setSimpleProperty(target, name, value);
					}
				}
				catch (final IllegalAccessException e)
				{
					e.printStackTrace();
				}
				catch (final InvocationTargetException e)
				{
					e.printStackTrace();
				}
				catch (final NoSuchMethodException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * copy bean with reflect
	 *
	 * @param srcObj
	 * @param destObj
	 */
	public static void copyPro(final Object srcObj, final Object destObj)
	{

		final Map<String, Object> srcMap = new HashMap<String, Object>();
		final Field[] srcFields = srcObj.getClass().getDeclaredFields();
		for (final Field srcField : srcFields)
		{
			try
			{
				srcField.setAccessible(true);
				srcMap.put(srcField.getName(), srcField.get(srcObj)); // 获取属性值
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}
		final Field[] destFields = destObj.getClass().getDeclaredFields();
		for (final Field destField : destFields)
		{
			if (srcMap.get(destField.getName()) == null)
			{
				continue;
			}
			try
			{
				destField.setAccessible(true);
				destField.set(destObj, srcMap.get(destField.getName())); // 给属性赋值
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	public static FunctionClassCount FunctionClassCount(final String str)
	{
		int functionClass;
		final FunctionClassCount functionClassCount = new FunctionClassCount(0, 0, 0, 0, 0);
		final String[] strAutoArr = str.substring(1, str.length() - 1).split(",");
		System.out.println(StartInitCompareEdge.compareMapEdge.size());

		for (int i = 0; i < strAutoArr.length; i++)
		{
			if (StartInitCompareEdge.compareMapEdge.containsKey(strAutoArr[i]))
			{
				functionClass = StartInitCompareEdge.compareMapEdge.get(strAutoArr[i]);

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

	public static String functionClassList(final int zoom, final String str)
	{
		final List<String> functionClassList = new ArrayList<String>();

		int functionClass = -1;
		final String[] strArr = str.split(",");
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
	public static void Json2Object(final Object target, final JSONObject jsonObject)
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
}
