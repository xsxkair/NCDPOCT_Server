package com.xsx.ncd.handler;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.define.AdjustRecordItem;
import com.xsx.ncd.define.MaintenanceRecordItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.DeviceMaintenanceRecord;
import com.xsx.ncd.service.DeviceMaintenanceRecordService;

@Controller
public class DeviceMaintenanceRecordHandler {

	@Autowired DeviceMaintenanceRecordService deviceMaintenanceRecordService;
	
	@ResponseBody
	@RequestMapping("/UpLoadDeviceMaintenance")
	public String upLoadDeviceMaintenanceHandler(DeviceMaintenanceRecord deviceMaintenanceRecord, String deviceId,
			Integer operatorId){
		
		return deviceMaintenanceRecordService.upLoadDeviceMaintenanceService(deviceMaintenanceRecord, deviceId, operatorId);
	}
	
	@ResponseBody
	@RequestMapping("/QueryDeviceMaintenanceRecord")
	public RecordJson<MaintenanceRecordItem> queryDeviceMaintenanceRecordHandler(Date date, String operatorName, String deviceId,
			String result, int startIndex, int size){
		return deviceMaintenanceRecordService.queryDeviceMaintenanceRecordService(date, operatorName, deviceId, result, startIndex, size);
	}
}
