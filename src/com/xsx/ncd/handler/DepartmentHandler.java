package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.service.DepartmentService;

@Controller
public class DepartmentHandler {

	@Autowired DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping(value="/readAllDepartment")
	public List<Department> readAllDepartmentHandler() {
		return departmentService.readAllDepartmentService();
	}
}
