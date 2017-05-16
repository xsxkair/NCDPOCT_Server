package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired DeviceRepository deviceRepository;
	
	public List<Device> queryAllDeviceByDepartmentService(Department department){
		return deviceRepository.findByDepartment(department);
	}
	
	public Device queryDeviceInfoService(String deviceId){
		
		Device tempDevice = deviceRepository.findByDid(deviceId);
		
		if(tempDevice != null){
			tempDevice.setLasttime(System.currentTimeMillis());
			deviceRepository.save(tempDevice);
		}
		
		return tempDevice;
	}
	
	public String refreshDeviceOnLineStatusService(String deviceId){
		
		Device tempDevice = deviceRepository.findByDid(deviceId);
		
		if(tempDevice != null){
			tempDevice.setLasttime(System.currentTimeMillis());
			deviceRepository.save(tempDevice);
			
			return "Success!";
		}
		else {
			return "Fail!";
		}
	}
	
}
