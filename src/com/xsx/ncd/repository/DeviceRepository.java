package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer>, JpaSpecificationExecutor<Device> {

	public List<Device> findByDepartment(Department department);
	
	public Device findByDid(String did);
}
