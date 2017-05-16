package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsx.ncd.entity.DeviceType;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer>{
	
	public DeviceType findByNameAndModel(String name, String model);
	
	@Query("select d.icon from DeviceType d")
	public List<String> findAllIcoPath();
}
