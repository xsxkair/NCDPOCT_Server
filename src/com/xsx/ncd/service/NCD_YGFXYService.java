package com.xsx.ncd.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.Item;
import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.CardRepository;
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
	@Autowired CardRepository cardRepository;
	
	public NCD_YGFXY upLoadYGFXYDataService(NCD_YGFXY ncd_YGFXY){
		Item item = null;
		Operator operator = null;
		Device device = null;

		//根据数据序列号查看是否已存在,则覆盖老数据
		NCD_YGFXY temp = ncd_YGFXYRepository.findBySerialnum(ncd_YGFXY.getSerialnum());
		if(temp != null){	
			ncd_YGFXY.setId(temp.getId());
		}
		
		try {
			device = deviceRepository.findByDid(ncd_YGFXY.getDevice().getDid());
		} catch (Exception e) {
			// TODO: handle exception
			device = null;
		}
		
		try {
			item = itemRepository.findByCode(ncd_YGFXY.getItem().getCode());
		} catch (Exception e) {
			// TODO: handle exception
			item = null;
		}
		
		try {
			operator = operatorRepository.findByDepartmentAndName(device.getDepartment(), ncd_YGFXY.getOperator().getName());
		} catch (Exception e) {
			// TODO: handle exception
			operator = null;
		}
		
		if(device == null)
			return null;
		
		if(item == null)
			return null;
		
		ncd_YGFXY.setOperator(operator);
		ncd_YGFXY.setDevice(device);
		ncd_YGFXY.setItem(item);
		ncd_YGFXY.setUptime(new Timestamp(System.currentTimeMillis()));
		
		ncd_YGFXYRepository.save(ncd_YGFXY);
		
		//减小回传的数据量，设置设备为null，操作人为null，项目为null，曲线为null
		ncd_YGFXY.setOperator(null);
		ncd_YGFXY.setDevice(null);
		ncd_YGFXY.setItem(null);
		ncd_YGFXY.setSeries(null);
		
		return ncd_YGFXY;
	}
}
