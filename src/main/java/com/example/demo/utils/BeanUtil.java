package com.example.demo.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

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
}
