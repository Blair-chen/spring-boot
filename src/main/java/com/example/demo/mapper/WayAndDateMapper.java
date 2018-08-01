package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.WayAndDate;

@Mapper
public interface WayAndDateMapper
{
	@Select("SELECT * FROM way_and_date where time= #{time}")
	List<WayAndDate> findByTime(@Param("time") Date time);

	@Select("SELECT * FROM way_and_date WHERE id = #{id}")
	List<WayAndDate> findWayAndDateById(@Param("id") long id);

	@Select("SELECT * FROM way_and_date WHERE wayid = #{id}")
	List<WayAndDate> findWayAndDateByWayid(@Param("id") long id);

}
