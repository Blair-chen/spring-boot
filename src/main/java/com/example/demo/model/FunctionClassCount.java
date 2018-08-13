package com.example.demo.model;

public class FunctionClassCount
{
	private String name;
	private int functionClassZero;
	private int functionClassTwo;
	private int functionClassOne;
	private int functionClassThree;
	private int functionClassFour;

	public FunctionClassCount(final int functionClassZero, final int functionClassTwo,
			final int functionClassOne, final int functionClassThree, final int functionClassFour)
	{
		super();
		this.functionClassZero = functionClassZero;
		this.functionClassTwo = functionClassTwo;
		this.functionClassOne = functionClassOne;
		this.functionClassThree = functionClassThree;
		this.functionClassFour = functionClassFour;
	}

	public int getFunctionClassFour()
	{
		return this.functionClassFour;
	}

	public int getFunctionClassOne()
	{
		return this.functionClassOne;
	}

	public int getFunctionClassThree()
	{
		return this.functionClassThree;
	}

	public int getFunctionClassTwo()
	{
		return this.functionClassTwo;
	}

	public int getFunctionClassZero()
	{
		return this.functionClassZero;
	}

	public String getName()
	{
		return this.name;
	}

	public void setFunctionClassFour(final int functionClassFour)
	{
		this.functionClassFour = functionClassFour;
	}

	public void setFunctionClassOne(final int functionClassOne)
	{
		this.functionClassOne = functionClassOne;
	}

	public void setFunctionClassThree(final int functionClassThree)
	{
		this.functionClassThree = functionClassThree;
	}

	public void setFunctionClassTwo(final int functionClassTwo)
	{
		this.functionClassTwo = functionClassTwo;
	}

	public void setFunctionClassZero(final int functionClassZero)
	{
		this.functionClassZero = functionClassZero;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

}
