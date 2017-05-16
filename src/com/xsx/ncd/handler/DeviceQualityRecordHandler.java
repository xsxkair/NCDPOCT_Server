package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.DeviceQualityRecord;
import com.xsx.ncd.service.DeviceQualityRecordService;

@Controller
public class DeviceQualityRecordHandler {
	
	@Autowired DeviceQualityRecordService deviceQualityRecordService;
	
	@ResponseBody
	@RequestMapping("/UpLoadDeviceQuality")
	public String upLoadDeviceQualityHandler(DeviceQualityRecord deviceQualityRecord, String itemCode, String deviceId,
			Integer operatorId){
		
		return deviceQualityRecordService.upLoadDeviceQualityService(deviceQualityRecord, itemCode, deviceId, operatorId);
	}
}
