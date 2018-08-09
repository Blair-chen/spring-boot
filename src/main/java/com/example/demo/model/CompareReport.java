package com.example.demo.model;

import java.util.List;

public class CompareReport
{
	private int palmgoCount;
	private int autonaviCount;
	private int inPalmgoButNotInAutoNavi;
	private int inAutoNaviButNotInPalmgo;
	private int same;
	private int differentSpeedCount;
	private int differentLevelCount;
	private List<RoadesResponse> roadeslist;
	private BoundRequest bound;

	public int getAutonaviCount()
	{
		return this.autonaviCount;
	}

	public BoundRequest getBound()
	{
		return this.bound;
	}

	public int getDifferentLevelCount()
	{
		return this.differentLevelCount;
	}

	public int getDifferentSpeedCount()
	{
		return this.differentSpeedCount;
	}

	public int getInAutoNaviButNotInPalmgo()
	{
		return this.inAutoNaviButNotInPalmgo;
	}

	public int getInPalmgoButNotInAutoNavi()
	{
		return this.inPalmgoButNotInAutoNavi;
	}

	public int getPalmgoCount()
	{
		return this.palmgoCount;
	}

	public List<RoadesResponse> getRoadeslist()
	{
		return this.roadeslist;
	}

	public int getSame()
	{
		return this.same;
	}

	public void setAutonaviCount(final int autonaviCount)
	{
		this.autonaviCount = autonaviCount;
	}

	public void setBound(final BoundRequest bound)
	{
		this.bound = bound;
	}

	public void setDifferentLevelCount(final int differentLevelCount)
	{
		this.differentLevelCount = differentLevelCount;
	}

	public void setDifferentSpeedCount(final int differentSpeedCount)
	{
		this.differentSpeedCount = differentSpeedCount;
	}

	public void setInAutoNaviButNotInPalmgo(final int inAutoNaviButNotInPalmgo)
	{
		this.inAutoNaviButNotInPalmgo = inAutoNaviButNotInPalmgo;
	}

	public void setInPalmgoButNotInAutoNavi(final int inPalmgoButNotInAutoNavi)
	{
		this.inPalmgoButNotInAutoNavi = inPalmgoButNotInAutoNavi;
	}

	public void setPalmgoCount(final int palmgoCount)
	{
		this.palmgoCount = palmgoCount;
	}

	public void setRoadeslist(final List<RoadesResponse> roadeslist)
	{
		this.roadeslist = roadeslist;
	}

	public void setSame(final int same)
	{
		this.same = same;
	}

}
