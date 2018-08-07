package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WayAndDate implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private Date time;

	private String way;
	@Column(nullable = false)
	private long wayid;

	public WayAndDate()
	{
		super();
	}

	public WayAndDate(final Date time, final String way, final long wayid)
	{
		super();
		this.time = time;
		this.way = way;
		this.wayid = wayid;
	}

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
