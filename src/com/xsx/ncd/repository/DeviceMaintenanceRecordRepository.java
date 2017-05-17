package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xsx.ncd.entity.DeviceMaintenanceRecord;

public interface DeviceMaintenanceRecordRepository extends JpaRepository<DeviceMaintenanceRecord, Integer>, JpaSpecificationExecutor<DeviceMaintenanceRecord> {

}
