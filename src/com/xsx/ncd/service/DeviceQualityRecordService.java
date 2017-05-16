package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceQualityRecord;
import com.xsx.ncd.entity.Item;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceQualityRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.ItemRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceQualityRecordService {
	
	@Autowired DeviceQualityRecordRepository deviceQualityRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	@Autowired ItemRepository itemRepository;
	
	public String upLoadDeviceQualityService(DeviceQualityRecord deviceQualityRecord, String itemCode, String deviceId,
			Integer operatorId){
		
		Item item = itemRepository.findByCode(itemCode);
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(item == null)
			return "Fail, Item is not exist!";
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceQualityRecord.setDevice(device);
		deviceQualityRecord.setOperator(operator);
		deviceQualityRecord.setItem(item);
		
		deviceQualityRecordRepository.save(deviceQualityRecord);
		
		return "Success";
	}
}
