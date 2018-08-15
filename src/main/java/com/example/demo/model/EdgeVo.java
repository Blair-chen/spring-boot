package com.example.demo.model;

import java.util.List;

import com.telenav.modules.mapping.geography.Location;

public class EdgeVo
{
	private Long id;
	private List<Location> location;

	public Long getId()
	{
		return this.id;
	}

	public List<Location> getLocation()
	{
		return this.location;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public void setLocation(final List<Location> location)
	{
		this.location = location;
	}

}
