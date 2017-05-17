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

		//��ҳ����
		PageRequest pageable = new PageRequest(startIndex, size, sort);
		
		//ͨ��ʹ�� Specification �������ڲ���
		Specification<DeviceErrorRecord> specification = new Specification<DeviceErrorRecord>() {
				/**
				 * @param *root: �����ѯ��ʵ����. 
				 * @param query: ���Դ��пɵ� Root ����, ����֪ JPA Criteria ��ѯҪ��ѯ��һ��ʵ����. ������
				 * ����Ӳ�ѯ����, �����Խ�� EntityManager ����õ����ղ�ѯ�� TypedQuery ����. 
				 * @param *cb: CriteriaBuilder ����. ���ڴ��� Criteria ��ض���Ĺ���. ��Ȼ���Դ��л�ȡ�� Predicate ����
				 * @return: *Predicate ����, ����һ����ѯ����. 
				 */
				@Override
				public Predicate toPredicate(Root<DeviceErrorRecord> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicateRoot = null;
						
					//�����쳣ʱ��
					if(date != null){
						predicateRoot = cb.equal(root.get("testtime").as(java.sql.Date.class), date);
					}
					
					//���˲�����
					if(operatorName != null){
						Path<String> pathName = root.get("operator").get("name");
						Predicate predicateName = cb.like(pathName, "%"+operatorName+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateName;
						else
							predicateRoot = cb.and(predicateName, predicateRoot);
					}
					
					//�쳣�豸id
					if(deviceId != null){
						Path<String> pathDevice = root.get("device").get("did");
						Predicate predicateDevice = cb.like(pathDevice, "%"+deviceId+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateDevice;
						else
							predicateRoot = cb.and(predicateDevice, predicateRoot);
					}
					
					//�쳣����
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
