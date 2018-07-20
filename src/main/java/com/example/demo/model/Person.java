package com.example.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "person")
public class Person
{
	private String name;
	private int age;

	public int getAge()
	{
		return this.age;
	}

	public String getName()
	{
		return this.name;
	}

	public void setAge(final int age)
	{
		this.age = age;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

}
