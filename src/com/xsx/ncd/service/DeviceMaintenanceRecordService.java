package com.xsx.ncd.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xsx.ncd.define.AdjustRecordItem;
import com.xsx.ncd.define.MaintenanceRecordItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceAdjustRecord;
import com.xsx.ncd.entity.DeviceMaintenanceRecord;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceMaintenanceRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceMaintenanceRecordService {
	
	@Autowired DeviceMaintenanceRecordRepository deviceMaintenanceRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	
	public String upLoadDeviceMaintenanceService(DeviceMaintenanceRecord deviceMaintenanceRecord, String deviceId,
			Integer operatorId){
		
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceMaintenanceRecord.setDevice(device);
		deviceMaintenanceRecord.setOperator(operator);
		
		deviceMaintenanceRecordRepository.save(deviceMaintenanceRecord);
		
		return "Success";
	}
	
	public RecordJson<MaintenanceRecordItem> queryDeviceMaintenanceRecordService(Date date, String operatorName, String deviceId,
			String result, int startIndex, int size){
		
		Sort sort = new Sort(Direction.DESC, "testtime");

		//��ҳ����
		PageRequest pageable = new PageRequest(startIndex, size, sort);
		
		//ͨ��ʹ�� Specification �������ڲ���
		Specification<DeviceMaintenanceRecord> specification = new Specification<DeviceMaintenanceRecord>() {
				/**
				 * @param *root: �����ѯ��ʵ����. 
				 * @param query: ���Դ��пɵ� Root ����, ����֪ JPA Criteria ��ѯҪ��ѯ��һ��ʵ����. ������
				 * ����Ӳ�ѯ����, �����Խ�� EntityManager ����õ����ղ�ѯ�� TypedQuery ����. 
				 * @param *cb: CriteriaBuilder ����. ���ڴ��� Criteria ��ض���Ĺ���. ��Ȼ���Դ��л�ȡ�� Predicate ����
				 * @return: *Predicate ����, ����һ����ѯ����. 
				 */
				@Override
				public Predicate toPredicate(Root<DeviceMaintenanceRecord> root,
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
					if(result != null){
						Path<String> pathResult = root.get("result");
						Predicate predicateResult = cb.like(pathResult, "%"+result+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateResult;
						else
							predicateRoot = cb.and(predicateResult, predicateRoot);
					}

					return predicateRoot;
				}
			};

		Page<DeviceMaintenanceRecord> page = deviceMaintenanceRecordRepository.findAll(specification, pageable);
		
		List<DeviceMaintenanceRecord> deviceErrorRecords = page.getContent();
		List<MaintenanceRecordItem> errorRecordItems = new ArrayList<>();
		for (DeviceMaintenanceRecord deviceMaintenanceRecord : deviceErrorRecords) {
			MaintenanceRecordItem tempMaintenanceRecordItem = new MaintenanceRecordItem(deviceMaintenanceRecord.getId(), deviceMaintenanceRecord.getTesttime(),	
					null, null, deviceMaintenanceRecord.getResult(), deviceMaintenanceRecord.getDsc());
			
			if(deviceMaintenanceRecord.getDevice() != null)
				tempMaintenanceRecordItem.setDeviceId(deviceMaintenanceRecord.getDevice().getDid());
			
			if(deviceMaintenanceRecord.getOperator() != null)
				tempMaintenanceRecordItem.setUserName(deviceMaintenanceRecord.getOperator().getName());
			
			errorRecordItems.add(tempMaintenanceRecordItem);
		}
		RecordJson<MaintenanceRecordItem> errorRecordJson = new RecordJson<MaintenanceRecordItem>(page.getTotalPages(), errorRecordItems);

		return errorRecordJson;
	}
}
