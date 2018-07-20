package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BoundRequest
{
	private Position northeast;
	private Position sourthwest;
	private int zoom;

	@JsonCreator
	public BoundRequest(@JsonProperty("northeast") final Position northeast,
			@JsonProperty("sourthwest") final Position sourthwest,
			@JsonProperty("zoom") final int zoom)
	{
		super();
		this.northeast = northeast;
		this.sourthwest = sourthwest;
		this.zoom = zoom;
	}

	public Position getNortheast()
	{
		return this.northeast;
	}

	public Position getSourthwest()
	{
		return this.sourthwest;
	}

	public int getZoom()
	{
		return this.zoom;
	}

	public void setNortheast(final Position northeast)
	{
		this.northeast = northeast;
	}

	public void setSourthwest(final Position sourthwest)
	{
		this.sourthwest = sourthwest;
	}

	public void setZoom(final int zoom)
	{
		this.zoom = zoom;
	}

}
