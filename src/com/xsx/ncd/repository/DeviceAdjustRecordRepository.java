package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xsx.ncd.entity.DeviceAdjustRecord;

public interface DeviceAdjustRecordRepository extends JpaRepository<DeviceAdjustRecord, Integer>, JpaSpecificationExecutor<DeviceAdjustRecord> {

}
