package com.example.demo.model;

import java.util.List;

public class CompareReport
{
	private int palmgoTotalCount;
	private int palmgoCurrentCount;
	private int autonaviCount;
	private int inPalmgoButNotInAutoNaviCount;
	private int inAutoNaviButNotInPalmgoCount;
	private int sameCount;
	private int differentSpeedCount;
	private int differentLevelCount;
	private List<RoadesResponse> roadeslist;
	private BoundRequest bound;
	private List<FunctionClassCount> functionClasslist;

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

	public List<FunctionClassCount> getFunctionClasslist()
	{
		return this.functionClasslist;
	}

	public int getInAutoNaviButNotInPalmgoCount()
	{
		return this.inAutoNaviButNotInPalmgoCount;
	}

	public int getInPalmgoButNotInAutoNaviCount()
	{
		return this.inPalmgoButNotInAutoNaviCount;
	}

	public int getPalmgoCurrentCount()
	{
		return this.palmgoCurrentCount;
	}

	public int getPalmgoTotalCount()
	{
		return this.palmgoTotalCount;
	}

	public List<RoadesResponse> getRoadeslist()
	{
		return this.roadeslist;
	}

	public int getSameCount()
	{
		return this.sameCount;
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

	public void setFunctionClasslist(final List<FunctionClassCount> functionClasslist)
	{
		this.functionClasslist = functionClasslist;
	}

	public void setInAutoNaviButNotInPalmgoCount(final int inAutoNaviButNotInPalmgoCount)
	{
		this.inAutoNaviButNotInPalmgoCount = inAutoNaviButNotInPalmgoCount;
	}

	public void setInPalmgoButNotInAutoNaviCount(final int inPalmgoButNotInAutoNaviCount)
	{
		this.inPalmgoButNotInAutoNaviCount = inPalmgoButNotInAutoNaviCount;
	}

	public void setPalmgoCurrentCount(final int palmgoCurrentCount)
	{
		this.palmgoCurrentCount = palmgoCurrentCount;
	}

	public void setPalmgoTotalCount(final int palmgoTotalCount)
	{
		this.palmgoTotalCount = palmgoTotalCount;
	}

	public void setRoadeslist(final List<RoadesResponse> roadeslist)
	{
		this.roadeslist = roadeslist;
	}

	public void setSameCount(final int sameCount)
	{
		this.sameCount = sameCount;
	}

}
