package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.DeviceAdjustRecord;
import com.xsx.ncd.service.DeviceAdjustRecordService;

@Controller
public class DeviceAdjustRecordHandler {

	@Autowired DeviceAdjustRecordService deviceAdjustRecordService;
	
	@ResponseBody
	@RequestMapping("/UpLoadDeviceAdjust")
	public String upLoadDeviceAdjustRecordHandler(DeviceAdjustRecord deviceAdjustRecord, String deviceId,
			Integer operatorId){
		
		return deviceAdjustRecordService.upLoadDeviceAdjustRecordHandler(deviceAdjustRecord, deviceId, operatorId);
	}
}
