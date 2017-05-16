package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceAdjustRecord;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceAdjustRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceAdjustRecordService {

	@Autowired DeviceAdjustRecordRepository deviceAdjustRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	
	public String upLoadDeviceAdjustRecordHandler(DeviceAdjustRecord deviceAdjustRecord, String deviceId,
			Integer operatorId){
		
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceAdjustRecord.setDevice(device);
		deviceAdjustRecord.setOperator(operator);
		
		deviceAdjustRecordRepository.save(deviceAdjustRecord);
		
		return "Success";
	}
}
