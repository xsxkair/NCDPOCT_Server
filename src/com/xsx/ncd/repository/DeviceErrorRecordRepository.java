package com.xsx.ncd.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.DeviceErrorRecord;


public interface DeviceErrorRecordRepository extends JpaRepository<DeviceErrorRecord, Integer> {
	
	@Query("select der from DeviceErrorRecord der where DATE(der.testtime)=:date and der.operator.name=:name "
			+ "and der.device.did=:did and der.errorcode=:code limit(:starti, :size)")
	public List<DeviceErrorRecord> findByDateAndOperatorAndDeviceAndCodeAndPage(@Param("date")Date date, 
			@Param("name")String operatorName, @Param("did")String deviceId,
			@Param("code")Integer errorCode, @Param("starti")int startIndex, @Param("size")int size);
}
