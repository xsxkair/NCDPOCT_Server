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
import com.xsx.ncd.define.QualityRecordItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceAdjustRecord;
import com.xsx.ncd.entity.DeviceQualityRecord;
import com.xsx.ncd.entity.Item;
import com.xsx.ncd.entity.Operator;
import com.xsx.ncd.repository.DeviceQualityRecordRepository;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.ItemRepository;
import com.xsx.ncd.repository.OperatorRepository;

@Service
public class DeviceQualityRecordService {
	
	@Autowired DeviceQualityRecordRepository deviceQualityRecordRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired OperatorRepository operatorRepository;
	@Autowired ItemRepository itemRepository;
	
	public String upLoadDeviceQualityService(DeviceQualityRecord deviceQualityRecord, String itemCode, String deviceId,
			Integer operatorId){
		
		Item item = itemRepository.findByCode(itemCode);
		Operator operator = operatorRepository.findOne(operatorId);
		Device device = deviceRepository.findByDid(deviceId);
		
		if(item == null)
			return "Fail, Item is not exist!";
		
		if(device == null)
			return "Fail, Device is not exist!";
		
		deviceQualityRecord.setDevice(device);
		deviceQualityRecord.setOperator(operator);
		deviceQualityRecord.setItem(item);
		
		deviceQualityRecordRepository.save(deviceQualityRecord);
		
		return "Success";
	}
	
	public RecordJson<QualityRecordItem> queryDeviceQualityRecordService(Date date, String operatorName, String deviceId,
			String itemName, String result, int startIndex, int size){
		
		Sort sort = new Sort(Direction.DESC, "testtime");

		//分页条件
		PageRequest pageable = new PageRequest(startIndex, size, sort);
		
		//通常使用 Specification 的匿名内部类
		Specification<DeviceQualityRecord> specification = new Specification<DeviceQualityRecord>() {
				/**
				 * @param *root: 代表查询的实体类. 
				 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
				 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象. 
				 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
				 * @return: *Predicate 类型, 代表一个查询条件. 
				 */
				@Override
				public Predicate toPredicate(Root<DeviceQualityRecord> root,
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
					
					if(itemName != null){
						Path<String> pathItem = root.get("item").get("name");
						Predicate predicateItem = cb.like(pathItem, "%"+itemName+"%");
						
						if(predicateRoot == null)
							predicateRoot = predicateItem;
						else
							predicateRoot = cb.and(predicateItem, predicateRoot);
					}
					
					//结果
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

		Page<DeviceQualityRecord> page = deviceQualityRecordRepository.findAll(specification, pageable);
		
		List<DeviceQualityRecord> deviceErrorRecords = page.getContent();
		List<QualityRecordItem> errorRecordItems = new ArrayList<>();
		for (DeviceQualityRecord deviceAdjustRecord : deviceErrorRecords) {
			QualityRecordItem tempAdjustRecordItem = new QualityRecordItem(deviceAdjustRecord.getId(), deviceAdjustRecord.getNormalv(),
					deviceAdjustRecord.getMeasurev(), deviceAdjustRecord.getTesttime(),	null, null, null, 
					deviceAdjustRecord.getResult(), deviceAdjustRecord.getDsc());
			
			if(deviceAdjustRecord.getDevice() != null)
				tempAdjustRecordItem.setDeviceId(deviceAdjustRecord.getDevice().getDid());
			
			if(deviceAdjustRecord.getItem() != null)
				tempAdjustRecordItem.setItemName(deviceAdjustRecord.getItem().getName());
			
			if(deviceAdjustRecord.getOperator() != null)
				tempAdjustRecordItem.setOperatorName(deviceAdjustRecord.getOperator().getName());
			
			errorRecordItems.add(tempAdjustRecordItem);
		}
		RecordJson<QualityRecordItem> errorRecordJson = new RecordJson<QualityRecordItem>(page.getTotalPages(), errorRecordItems);

		return errorRecordJson;
	}
}
