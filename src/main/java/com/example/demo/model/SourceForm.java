package com.example.demo.model;

public class SourceForm
{
	private String type;
	private long id;
	private String reportTime;
	private double speed;
	private int level;

	public SourceForm(final String type, final int level)
	{
		super();
		this.type = type;
		this.level = level;
	}

	public SourceForm(final String type, final long id, final String reportTime, final double speed,
			final int level)
	{
		super();
		this.type = type;
		this.id = id;
		this.reportTime = reportTime;
		this.speed = speed;
		this.level = level;
	}

	public long getId()
	{
		return this.id;
	}

	public int getLevel()
	{
		return this.level;
	}

	public String getReportTime()
	{
		return this.reportTime;
	}

	public double getSpeed()
	{
		return this.speed;
	}

	public String getType()
	{
		return this.type;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public void setReportTime(final String reportTime)
	{
		this.reportTime = reportTime;
	}

	public void setSpeed(final double speed)
	{
		this.speed = speed;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

}
