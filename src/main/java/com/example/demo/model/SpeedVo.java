package com.example.demo.model;

import java.util.Date;

public class SpeedVo
{

	private long id;

	private long wayid;

	private double speed;

	private Date dtime;

	private String date;
	private String dtimeStr;

	public String getDate()
	{
		return this.date;
	}

	public Date getDtime()
	{
		return this.dtime;
	}

	public String getDtimeStr()
	{
		return this.dtimeStr;
	}

	public long getId()
	{
		return this.id;
	}

	public double getSpeed()
	{
		return this.speed;
	}

	public long getWayid()
	{
		return this.wayid;
	}

	public void setDate(final String date)
	{
		this.date = date;
	}

	public void setDtime(final Date dtime)
	{
		this.dtime = dtime;
	}

	public void setDtimeStr(final String dtimeStr)
	{
		this.dtimeStr = dtimeStr;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setSpeed(final double speed)
	{
		this.speed = speed;
	}

	public void setWayid(final long wayid)
	{
		this.wayid = wayid;
	}

}
