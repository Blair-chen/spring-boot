package com.example.demo.model;

import java.util.List;

public class RoadesResponse
{
	private Long wayid;
	private List<Position> positions;
	private int flow;
	private String color;
	private List<SourceForm> listSource;

	public RoadesResponse(final Long wayid, final List<Position> positions)
	{
		super();
		this.wayid = wayid;
		this.positions = positions;

	}

	public RoadesResponse(final Long wayid, final List<Position> positions, final int flow)
	{
		super();
		this.wayid = wayid;
		this.positions = positions;
		this.flow = flow;
	}

	public RoadesResponse(final Long wayid, final List<Position> positions, final String color,
			final List<SourceForm> listSource)
	{
		super();
		this.wayid = wayid;
		this.positions = positions;
		this.color = color;
		this.listSource = listSource;
	}

	public String getColor()
	{
		return this.color;
	}

	public int getFlow()
	{
		return this.flow;
	}

	public List<SourceForm> getListSource()
	{
		return this.listSource;
	}

	public List<Position> getPositions()
	{
		return this.positions;
	}

	public Long getWayid()
	{
		return this.wayid;
	}

	public void setColor(final String color)
	{
		this.color = color;
	}

	public void setFlow(final int flow)
	{
		this.flow = flow;
	}

	public void setListSource(final List<SourceForm> listSource)
	{
		this.listSource = listSource;
	}

	public void setPositions(final List<Position> positions)
	{
		this.positions = positions;
	}

	public void setWayid(final Long wayid)
	{
		this.wayid = wayid;
	}

}
