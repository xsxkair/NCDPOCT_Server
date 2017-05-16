package com.xsx.ncd.handler;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.DeviceErrorRecord;
import com.xsx.ncd.service.DeviceErrorRecordService;

@Controller
public class DeviceErrorRecordHandler {
	
	@Autowired DeviceErrorRecordService deviceErrorRecordService;
	
	@ResponseBody
	@RequestMapping("/UpLoadDeviceError")
	public String upLoadDeviceErrorHandler(DeviceErrorRecord deviceErrorRecord, String deviceId,
			Integer operatorId){
		
		return deviceErrorRecordService.upLoadDeviceErrorService(deviceErrorRecord, deviceId, operatorId);
	}
	
	@ResponseBody
	@RequestMapping("/QueryDeviceErrorRecord")
	public List<DeviceErrorRecord> queryDeviceErrorRecordHandler(Date date, String operatorName, String deviceId,
			Integer errorCode, int startIndex, int size){
		System.out.println(date);
		System.out.println(operatorName);
		System.out.println(deviceId);
		System.out.println(errorCode);
		System.out.println(startIndex);
		System.out.println(size);
		return deviceErrorRecordService.queryDeviceErrorRecordService(date, operatorName, deviceId, errorCode, startIndex, size);
	}
}
