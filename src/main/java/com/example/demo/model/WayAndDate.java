package com.example.demo.model;

import java.util.Date;

public class WayAndDate
{

	private long id;
	private Date time;
	private String way;
	private long wayid;

	public long getId()
	{
		return this.id;
	}

	public Date getTime()
	{
		return this.time;
	}

	public String getWay()
	{
		return this.way;
	}

	public long getWayid()
	{
		return this.wayid;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

	public void setWay(final String way)
	{
		this.way = way;
	}

	public void setWayid(final long wayid)
	{
		this.wayid = wayid;
	}

}