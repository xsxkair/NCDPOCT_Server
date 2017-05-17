package com.xsx.ncd.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xsx.ncd.define.ErrorRecordItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceErrorRecord;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceErrorRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceErrorRecordService {

	@Autowired DeviceErrorRecordRepository deviceErrorRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	
	public String upLoadDeviceErrorService(DeviceErrorRecord deviceErrorRecord, String deviceId,
			Integer operatorId){
		
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceErrorRecord.setDevice(device);
		deviceErrorRecord.setOperator(operator);
		
		deviceErrorRecordRepository.save(deviceErrorRecord);
		
		return "Success";
	}
	
	public RecordJson<ErrorRecordItem> queryDeviceErrorRecordService(Date date, String operatorName, String deviceId,
			Integer errorCode, int startIndex, int size){
		
		Sort sort = new Sort(Direction.DESC, "testtime");

		//分页条件
		PageRequest pageable = new PageRequest(startIndex, size, sort);
		
		//通常使用 Specification 的匿名内部类
		Specification<DeviceErrorRecord> specification = new Specification<DeviceErrorRecord>() {
				/**
				 * @param *root: 代表查询的实体类. 
				 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
				 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象. 
				 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
				 * @return: *Predicate 类型, 代表一个查询条件. 
				 */
				@Override
				public Predicate toPredicate(Root<DeviceErrorRecord> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicateRoot = null;
						
					//过滤异常时间
					if(date != null){
						predicateRoot = cb.equal(root.get("testtime").as(java.sql.Date.class), date);
					}
					
					//过滤操作人
					if(operatorName != null){
						Path<String> pathName = root.get("operator").get("name");
						Predicate predicateName = cb.like(pathName, "%"+operatorName+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateName;
						else
							predicateRoot = cb.and(predicateName, predicateRoot);
					}
					
					//异常设备id
					if(deviceId != null){
						Path<String> pathDevice = root.get("device").get("did");
						Predicate predicateDevice = cb.like(pathDevice, "%"+deviceId+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateDevice;
						else
							predicateRoot = cb.and(predicateDevice, predicateRoot);
					}
					
					//异常代码
					if(errorCode != null){
						Path<Integer> pathCode = root.get("errorcode");
						Predicate predicateCode = cb.equal(pathCode, errorCode);
						
						if(predicateRoot == null)
							predicateRoot = predicateCode;
						else
							predicateRoot = cb.and(predicateCode, predicateRoot);
					}

					return predicateRoot;
				}
			};

		Page<DeviceErrorRecord> page = deviceErrorRecordRepository.findAll(specification, pageable);
		
		List<DeviceErrorRecord> deviceErrorRecords = page.getContent();
		List<ErrorRecordItem> errorRecordItems = new ArrayList<>();
		for (DeviceErrorRecord deviceErrorRecord : deviceErrorRecords) {
			ErrorRecordItem tempErrorRecordItem = new ErrorRecordItem(deviceErrorRecord.getId(), deviceErrorRecord.getTesttime(),
					null, null, deviceErrorRecord.getErrorcode(), deviceErrorRecord.getDsc());
			
			if(deviceErrorRecord.getDevice() != null)
				tempErrorRecordItem.setDeviceId(deviceErrorRecord.getDevice().getDid());
			
			if(deviceErrorRecord.getOperator() != null)
				tempErrorRecordItem.setUserName(deviceErrorRecord.getOperator().getName());
			
			errorRecordItems.add(tempErrorRecordItem);
		}
		RecordJson<ErrorRecordItem> errorRecordJson = new RecordJson<ErrorRecordItem>(page.getTotalPages(), errorRecordItems);

		return errorRecordJson;
	}
}
