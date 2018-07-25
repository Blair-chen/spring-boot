package com.example.demo.model;

import java.util.List;

public class RoadesResponse
{
	private Long wayid;
	private List<Position> positions;
	private int flow;

	public RoadesResponse(final Long wayid, final List<Position> positions, final int flow)
	{
		super();
		this.wayid = wayid;
		this.positions = positions;
		this.flow = flow;
	}

	public int getFlow()
	{
		return this.flow;
	}

	public List<Position> getPositions()
	{
		return this.positions;
	}

	public Long getWayid()
	{
		return this.wayid;
	}

	public void setFlow(final int flow)
	{
		this.flow = flow;
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
