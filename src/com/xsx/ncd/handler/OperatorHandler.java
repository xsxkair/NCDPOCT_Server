package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.OperatorRepository;
import com.xsx.ncd.service.OperatorService;

@Controller
public class OperatorHandler {

	@Autowired OperatorService operatorService;
	@Autowired OperatorRepository operatorRepository;
	
	@ResponseBody
	@RequestMapping(value="/ReadAllOperator")
	public List<Operator> readAllOperatorHandler() {
		return operatorService.readAllOperatorService();
	}
	
	@ResponseBody
	@RequestMapping(value="/ReadOperatorByIds")
	public List<Operator> readOperatorByIdsHandler(@RequestBody List<Integer> ids) {
		return operatorService.readOperatorInListService(ids);
	}
	
	@ResponseBody
	@RequestMapping(value="/ReadOneOperatorById")
	public Operator readOneOperatorByIdHandler(@RequestBody Integer id) {
		return operatorService.readOneOperatorByIdService(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/SaveOperator")
	public Operator saveOperatorHandler(@RequestBody Operator operator) {
		return operatorService.saveOperatorService(operator);
	}
	
	@ResponseBody
	@RequestMapping(value="/DeleteOperator")
	public Boolean deleteOperatorHandler(@RequestBody Operator operator) {
		return operatorService.deleteOperatorService(operator);
	}
	
	@ResponseBody
	@RequestMapping(value="/CheckOperatorIsExist")
	public Boolean checkOperatorIsExistHandler(@RequestBody Operator operator) {
		return operatorService.checkOperatorIsExistService(operator);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryOperatorByDepartment")
	public List<Operator> queryOperatorByDepartmentHandler(@RequestBody Department department) {
		return operatorRepository.findByDepartment(department);
	}
}
