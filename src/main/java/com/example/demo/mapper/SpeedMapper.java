package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Speed;

@Mapper
public interface SpeedMapper
{
	@Select("SELECT * FROM speed where wayid= #{wayid} and date=#{date} order by dtime")
	public List<Speed> findSpeedByWayidAndDate(@Param("wayid") long wayid,
			@Param("date") String date);

	@Insert("INSERT INTO speed(id,wayid,speed,dtime,date) VALUES(#{id}, #{wayid}, #{speed},#{dtime},#{date})")
	void insert(@Param("id") String id, @Param("wayid") long wayid, @Param("speed") double speed,
			@Param("dtime") Date dtime, @Param("date") String date);

}
