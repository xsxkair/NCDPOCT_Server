package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.service.DeviceService;

@Controller
public class DeviceHandler {

	@Autowired DeviceService deviceService;
	
	@ResponseBody
	@RequestMapping(value="/QueryThisDepartmentAllDeviceList")
	public List<Device> queryThisDepartmentAllDeviceListHandler(@RequestBody Department department) {
		return deviceService.queryAllDeviceByDepartmentService(department);
	}
}
