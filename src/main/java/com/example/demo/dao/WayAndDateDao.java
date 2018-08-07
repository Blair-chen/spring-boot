package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.WayAndDate;

public interface WayAndDateDao extends JpaRepository<WayAndDate, Long>
{
	List<WayAndDate> findByWayid(long wayid);
}
