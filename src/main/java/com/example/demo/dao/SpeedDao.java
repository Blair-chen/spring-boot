package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Speed;

public interface SpeedDao extends JpaRepository<Speed, Long>
{

	List<Speed> findByWayidAndDateOrderByDtimeAsc(long wayid, String date);
}
