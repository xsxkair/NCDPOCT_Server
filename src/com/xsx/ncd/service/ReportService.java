package com.xsx.ncd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceType;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.DeviceTypeRepository;
import com.xsx.ncd.repository.NCD_XTYRepository;
import com.xsx.ncd.repository.NCD_YGFXYRepository;

@Service
public class ReportService {

	@Autowired NCD_YGFXYRepository ncd_YGFXYRepository;
	@Autowired NCD_XTYRepository ncd_XTYRepository;
	@Autowired DeviceTypeRepository deviceTypeRepository;
	@Autowired DeviceRepository deviceRepository;
	
	public Long queryAllNotHandledReportNum(){
		List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
		Long sumNum = new Long(0);
		
		for (DeviceType deviceType : deviceTypes) {
			if(deviceType.getCode().equals("NCD_YGFXY"))
				sumNum += ncd_YGFXYRepository.findNotHandledReportNum();
			else if(deviceType.getCode().equals("NCD_XTY"))
				sumNum += ncd_XTYRepository.findNotHandledReportNum();
		}
		
		return sumNum;
	}
	
	public Long queryThisDeviceNotHandledReportNum(String deviceId, String deviceTypeCode){

		Long sumNum = new Long(0);
		
		switch (deviceTypeCode) {
		case "NCD_YGFXY":
			sumNum = ncd_YGFXYRepository.findThisDeviceNotHandledReportNum(deviceId);
			break;

		case "NCD_XTY":
			sumNum = ncd_XTYRepository.findThisDeviceNotHandledReportNum(deviceId);
			break;
			
		default:
			break;
		}
		
		return sumNum;
	}
	
	public List<Long> queryThisDeviceNotHandledReportNumAndLastTime(String deviceId, String deviceTypeCode){

		List<Long> datas = new ArrayList<>();
		Long sumNum = new Long(0);
		
		//查询设备未审核报告数目
		switch (deviceTypeCode) {
		case "NCD_YGFXY":
			datas.add(ncd_YGFXYRepository.findThisDeviceNotHandledReportNum(deviceId));
			break;

		case "NCD_XTY":
			datas.add(ncd_XTYRepository.findThisDeviceNotHandledReportNum(deviceId));
			break;
			
		default:
			break;
		}
		
		//查询设备上次在线时间
		Device device = deviceRepository.findByDid(deviceId);
		datas.add(device.getLasttime());
		
		return datas;
	}
}
