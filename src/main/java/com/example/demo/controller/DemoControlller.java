package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;

@RestController
public class DemoControlller
{
	@Value("${value}")
	private int value;
	@Autowired
	private Person person;

	@RequestMapping("/findPerson")
	public List<Person> getPerson()
	{
		final List<Person> list = new ArrayList<Person>();
		list.add(this.person);
		return list;

	}

	@RequestMapping("/")
	public String heloWord()
	{

		return "名字" + this.person.getName() + "年纪" + this.person.getAge();
	}

	@RequestMapping("/test/{name}")
	public String test(@PathVariable final String name)
	{
		System.out.println(name);
		return "this is second";
	}
}
