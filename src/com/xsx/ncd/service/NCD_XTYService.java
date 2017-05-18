package com.xsx.ncd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.Item;
import com.xsx.ncd.entity.NCD_XTY;
import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.DeviceTypeRepository;
import com.xsx.ncd.repository.ItemRepository;
import com.xsx.ncd.repository.NCD_XTYRepository;
import com.xsx.ncd.repository.NCD_YGFXYRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class NCD_XTYService {

	@Autowired NCD_XTYRepository ncd_XTYRepository;
	@Autowired OperatorRepository operatorRepository;
	@Autowired ItemRepository itemRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired DeviceTypeRepository deviceTypeRepository;
	
	public String upLoadXTYDataService(NCD_XTY ncd_XTY, String itemCode, Integer userId, String deviceId){
		Item item = itemRepository.findByCode(itemCode);
		Operator operator = operatorRepository.findOne(userId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(item == null)
			return "Fail, Item is not exist!";
		
		if(operator == null)
			return "Fail, Operator is not exist!";
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		ncd_XTY.setOperator(operator);
		ncd_XTY.setDevice(device);
		ncd_XTY.setItem(item);
		ncd_XTY.setReportresult("Œ¥…Û∫À");
		
		ncd_XTYRepository.save(ncd_XTY);
		
		return "Success!";
	}
}
