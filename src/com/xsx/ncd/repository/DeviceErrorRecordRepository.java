package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsx.ncd.entity.DeviceErrorRecord;

public interface DeviceErrorRecordRepository extends JpaRepository<DeviceErrorRecord, Integer> {

}
