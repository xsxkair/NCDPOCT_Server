package com.xsx.ncd.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceErrorRecord;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceErrorRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceErrorRecordService {

	@Autowired DeviceErrorRecordRepository deviceErrorRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	
	public String upLoadDeviceErrorService(DeviceErrorRecord deviceErrorRecord, String deviceId,
			Integer operatorId){
		
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceErrorRecord.setDevice(device);
		deviceErrorRecord.setOperator(operator);
		
		deviceErrorRecordRepository.save(deviceErrorRecord);
		
		return "Success";
	}
	
	public List<DeviceErrorRecord> queryDeviceErrorRecordService(Date date, String operatorName, String deviceId,
			Integer errorCode, int startIndex, int size){

		return deviceErrorRecordRepository.findByDateAndOperatorAndDeviceAndCodeAndPage(date, operatorName, deviceId, errorCode, startIndex, size);

	}
}
