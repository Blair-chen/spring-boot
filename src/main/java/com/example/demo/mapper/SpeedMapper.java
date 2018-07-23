package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Speed;

@Mapper
public interface SpeedMapper
{
	@Select("SELECT * FROM speed where wayid= #{wayid} and date=#{date}")
	public List<Speed> findSpeedByWayidAndDate(@Param("wayid") long wayid,
			@Param("date") String date);

}
