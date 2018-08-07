package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Speed implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private long wayid;
	@Column(nullable = false)
	private double speed;
	@Column(nullable = false)
	private Date dtime;
	@Column(nullable = false)
	private String date;

	public Speed()
	{
		super();
	}

	public Speed(final long wayid, final double speed, final Date dtime, final String date)
	{
		super();
		this.wayid = wayid;
		this.speed = speed;
		this.dtime = dtime;
		this.date = date;
	}

	public String getDate()
	{
		return this.date;
	}

	public Date getDtime()
	{
		return this.dtime;
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
