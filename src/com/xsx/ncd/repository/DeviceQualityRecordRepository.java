package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xsx.ncd.entity.DeviceQualityRecord;

public interface DeviceQualityRecordRepository extends JpaRepository<DeviceQualityRecord, Integer>, JpaSpecificationExecutor<DeviceQualityRecord> {

}
