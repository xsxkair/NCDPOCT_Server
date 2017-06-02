package com.xsx.ncd.handler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

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
				device.setLasttime(System.currentTimeMillis());
				device.setModifyTimeStamp(device.getLasttime()/1000);
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
			device.setModifyTimeStamp(System.currentTimeMillis()/1000);
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
	@RequestMapping("/DeviceReadTime")
	public LocalDateTime deviceReadTimeHandler(String deviceId){

		deviceService.refreshDeviceOnLineStatusService(deviceId);
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}
	
	@ResponseBody
	@RequestMapping(value="/DeviceQueryDeviceByDeviceId", produces = "application/json;charset=utf-8")
	public Device deviceQueryDeviceByDeviceIdHandler(String deviceId) {
		Device device = deviceRepository.findByDid(deviceId);
		
		//去掉设备类型数据，减小数据量
		device.setDeviceType(null);
		
		System.out.println(device.getDepartment().getName());
		
		//去掉操作人的部门信息，减小数据量

		return device;
	}
}
