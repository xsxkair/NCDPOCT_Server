package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired DepartmentRepository departmentRepository;
	
	public List<Department> readAllDepartmentService(){
		return departmentRepository.findAll();
	}
}
