package com.xsx.ncd.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.DeviceErrorRecord;


public interface DeviceErrorRecordRepository extends JpaRepository<DeviceErrorRecord, Integer>, JpaSpecificationExecutor<DeviceErrorRecord> {
	
}
