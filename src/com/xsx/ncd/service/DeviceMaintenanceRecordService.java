package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceMaintenanceRecord;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceMaintenanceRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceMaintenanceRecordService {
	
	@Autowired DeviceMaintenanceRecordRepository deviceMaintenanceRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	
	public String upLoadDeviceMaintenanceService(DeviceMaintenanceRecord deviceMaintenanceRecord, String deviceId,
			Integer operatorId){
		
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceMaintenanceRecord.setDevice(device);
		deviceMaintenanceRecord.setOperator(operator);
		
		deviceMaintenanceRecordRepository.save(deviceMaintenanceRecord);
		
		return "Success";
	}
}
