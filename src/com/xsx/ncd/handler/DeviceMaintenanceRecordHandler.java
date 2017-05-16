package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
