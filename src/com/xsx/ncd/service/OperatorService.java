package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired OperatorRepository operatorRepository;
	
	public List<Operator> readAllOperatorService(){
		return operatorRepository.findAll();
	}
	
	public List<Operator> readOperatorInListService(List<Integer> ids){
		return operatorRepository.findByids(ids);
	}
	
	public Operator readOneOperatorByIdService(Integer id){
		return operatorRepository.findOne(id);
	}
	
	public Operator saveOperatorService(Operator operator){
		return operatorRepository.save(operator);
	}
	
	public Boolean deleteOperatorService(Operator operator){
		operatorRepository.delete(operator);
		
		return true;
	}
	
	public Boolean checkOperatorIsExistService(Operator operator){
		if(operatorRepository.findByNameAndDepartment(operator.getName(), operator.getDepartment()).size() > 0)
			return true;
		else
			return false;
	}
}
