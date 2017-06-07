package com.xsx.ncd.handler;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.define.AdjustRecordItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.DeviceAdjustRecord;
import com.xsx.ncd.service.DeviceAdjustRecordService;

@Controller
public class DeviceAdjustRecordHandler {

	@Autowired DeviceAdjustRecordService deviceAdjustRecordService;
	
	@ResponseBody
	@RequestMapping("/UpLoadDeviceAdjust")
	public DeviceAdjustRecord upLoadDeviceAdjustRecordHandler(DeviceAdjustRecord deviceAdjustRecord){
		
		return deviceAdjustRecordService.upLoadDeviceAdjustRecordHandler(deviceAdjustRecord);
	}
	
	@ResponseBody
	@RequestMapping("/QueryDeviceAdjustRecord")
	public RecordJson<AdjustRecordItem> queryDeviceAdjustRecordHandler(Date date, String operatorName, String deviceId,
			String result, int startIndex, int size){
		return deviceAdjustRecordService.queryDeviceAdjustRecordService(date, operatorName, deviceId, result, startIndex, size);
	}
}
