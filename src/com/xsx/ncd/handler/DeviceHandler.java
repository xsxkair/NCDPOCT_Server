package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.service.DeviceService;

@Controller
public class DeviceHandler {

	@Autowired DeviceService deviceService;
	@Autowired DeviceRepository deviceRepository;
	
	@ResponseBody
	@RequestMapping(value="/QueryThisDepartmentAllDeviceList")
	public List<Device> queryThisDepartmentAllDeviceListHandler(@RequestBody Department department) {
		return deviceService.queryAllDeviceByDepartmentService(department);
	}
	
	@ResponseBody
	@RequestMapping(value="/DeleteDevice")
	public Boolean deleteDeviceHandler(@RequestBody Device device) {
		try {
			deviceRepository.delete(device);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/AddNewDevice")
	public String addNewDeviceHandler(@RequestBody Device device) {
		
		try {
			if(device.getId() == null){
				deviceRepository.save(device);
	
				return "Success!";
			}
			else
				return "Error, Device Id should be null!";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Fail"+e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryDeviceInfo")
	public Device queryDeviceInfoHandler(String deviceId) {
		
		return deviceService.queryDeviceInfoService(deviceId);
	
	}
	
	@ResponseBody
	@RequestMapping(value="/RefreshDeviceStatus")
	public String refreshDeviceOnLineStatusHandler(String deviceId) {
		
		return deviceService.refreshDeviceOnLineStatusService(deviceId);
	
	}
}
