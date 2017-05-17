package com.xsx.ncd.handler;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.define.ErrorRecordItem;
import com.xsx.ncd.define.RecordJson;
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
	public RecordJson<ErrorRecordItem> queryDeviceErrorRecordHandler(Date date, String operatorName, String deviceId,
			Integer errorCode, int startIndex, int size){
		return deviceErrorRecordService.queryDeviceErrorRecordService(date, operatorName, deviceId, errorCode, startIndex, size);
	}
}
