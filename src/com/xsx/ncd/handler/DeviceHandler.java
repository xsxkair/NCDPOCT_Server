package com.xsx.ncd.handler;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.define.DeviceJson;
import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.service.DeviceService;

@Controller
public class DeviceHandler {

	@Autowired DeviceService deviceService;
	@Autowired DeviceRepository deviceRepository;
	
	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat( "yyyyMMddHHmmss");
	
	@ResponseBody
	@RequestMapping(value="/QueryThisDepartmentAllDeviceList")
	public List<Device> queryThisDepartmentAllDeviceListHandler(@RequestBody Department department) {
		return deviceService.queryAllDeviceByDepartmentService(department);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryAllDeviceInSample")
	public List<DeviceJson> queryAllDeviceInSampleHandler(String departmentName, String deviceId) {
		return deviceService.QueryAllDeviceInSample(departmentName, deviceId);
	}

	@ResponseBody
	@RequestMapping(value="/QueryAllDeviceByDepartmentInSample")
	public List<DeviceJson> queryAllDeviceByDepartmentInSampleHandler(@RequestBody Department department) {
		return deviceService.QueryAllDeviceByDepartmentInSampleService(department);
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
	@RequestMapping(value="/UpDateDevice")
	public String upDateDeviceHandler(@RequestBody Device device) {
		
		if(device.getId() == null)
			return "Error, Device is not exist!";
		else{
			deviceRepository.save(device);
			return "Success";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryDeviceByDeviceId")
	public Device queryDeviceByDeviceIdHandler(String deviceId) {

		return deviceRepository.findByDid(deviceId);
	
	}
	
	@ResponseBody
	@RequestMapping(value="/RefreshDeviceStatus")
	public String refreshDeviceOnLineStatusHandler(String deviceId) {
		
		return deviceService.refreshDeviceOnLineStatusService(deviceId);
	
	}
	
	
	/*
	 *以下设备使用接口 
	 */
	
	/*
	 * 读取时间
	 */
	@ResponseBody
	@RequestMapping("/ReadTime")
	public LocalDateTime readTimeHandler(String deviceId){

		deviceService.refreshDeviceOnLineStatusService(deviceId);
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}
}
