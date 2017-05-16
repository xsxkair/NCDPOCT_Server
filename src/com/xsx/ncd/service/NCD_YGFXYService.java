package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.Item;
import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.DeviceTypeRepository;
import com.xsx.ncd.repository.ItemRepository;
import com.xsx.ncd.repository.NCD_YGFXYRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class NCD_YGFXYService {

	@Autowired NCD_YGFXYRepository ncd_YGFXYRepository;
	@Autowired OperatorRepository operatorRepository;
	@Autowired ItemRepository itemRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired DeviceTypeRepository deviceTypeRepository;
	
	public String upLoadYGFXYDataService(NCD_YGFXY ncd_YGFXY, String itemCode, Integer userId, String deviceId){
		Item item = itemRepository.findByCode(itemCode);
		Operator operator = operatorRepository.findOne(userId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(item == null)
			return "Fail, Item is not exist!";
		
		if(operator == null)
			return "Fail, Operator is not exist!";
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		ncd_YGFXY.setOperator(operator);
		ncd_YGFXY.setDevice(device);
		ncd_YGFXY.setItem(item);
		
		ncd_YGFXYRepository.save(ncd_YGFXY);
		
		return "Success!";
	}
}
