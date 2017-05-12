package com.xsx.ncd.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
