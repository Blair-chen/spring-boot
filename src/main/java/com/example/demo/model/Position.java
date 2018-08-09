package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Position
{
	private double lat;
	private double lng;

	public Position()
	{
		super();
	}

	@JsonCreator
	public Position(@JsonProperty("lat") final double lat, @JsonProperty("lng") final double lng)
	{
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat()
	{
		return this.lat;
	}

	public double getLng()
	{
		return this.lng;
	}

	public void setLat(final double lat)
	{
		this.lat = lat;
	}

	public void setLng(final double lng)
	{
		this.lng = lng;
	}

	@Override
	public String toString()
	{
		return "Position [lat=" + this.lat + ", lng=" + this.lng + "]";
	}

}
