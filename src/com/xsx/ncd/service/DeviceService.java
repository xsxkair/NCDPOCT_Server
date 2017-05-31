package com.xsx.ncd.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xsx.ncd.define.DeviceJson;
import com.xsx.ncd.entity.Department;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired DeviceRepository deviceRepository;
	
	public List<Device> queryAllDeviceByDepartmentService(Department department){
		return deviceRepository.findByDepartment(department);
	}
	
	public List<DeviceJson> QueryAllDeviceInSample(String departmentName, String deviceId){
		
		Specification<Device> specification = new Specification<Device>() {
			/**
			 * @param *root: 代表查询的实体类. 
			 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象. 
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件. 
			 */
			@Override
			public Predicate toPredicate(Root<Device> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateRoot = null;
					
				//过滤异常时间
				if(departmentName != null){
					Path<String> pathDepartmentName = root.get("department").get("name");
					predicateRoot = cb.like(pathDepartmentName, "%"+departmentName+"%");
				}
				
				//过滤操作人
				if(deviceId != null){
					Path<String> pathDeviceId = root.get("did");
					Predicate predicateName = cb.like(pathDeviceId, "%"+deviceId+"%");
					
					if(predicateRoot == null)
						predicateRoot = predicateName;
					else
						predicateRoot = cb.and(predicateName, predicateRoot);
				}
				return predicateRoot;
			}
		};

		List<Device> page = deviceRepository.findAll(specification);
		List<DeviceJson> deviceItems = new ArrayList<>();
		
		for (Device device : page) {
			DeviceJson deviceItem = new DeviceJson(device.getId(), null, device.getDid(), null, null, null, 
					device.getLasttime(), null, device.getAddr());
			
			if(device.getDepartment() != null){
				deviceItem.setDepartmentName(device.getDepartment().getName());
			}
			
			if(device.getDeviceType() != null){
				deviceItem.setDeviceTypeCode(device.getDeviceType().getCode());
				deviceItem.setName(device.getDeviceType().getName());
				deviceItem.setIco(device.getDeviceType().getIcon());
				deviceItem.setModel(device.getDeviceType().getModel());
			}
			
			deviceItems.add(deviceItem);
		}
		
		return deviceItems;
	}
	
	public List<DeviceJson> QueryAllDeviceByDepartmentInSampleService(Department department){
		
		List<Device> devices = deviceRepository.findByDepartment(department);
		List<DeviceJson> deviceItems = new ArrayList<>();
		
		for (Device device : devices) {
			DeviceJson deviceItem = new DeviceJson(device.getId(), null, device.getDid(), null, null, null, 
					device.getLasttime(), null, device.getAddr());
			
			if(device.getDepartment() != null){
				deviceItem.setDepartmentName(device.getDepartment().getName());
			}
			
			if(device.getDeviceType() != null){
				deviceItem.setDeviceTypeCode(device.getDeviceType().getCode());
				deviceItem.setName(device.getDeviceType().getName());
				deviceItem.setIco(device.getDeviceType().getIcon());
				deviceItem.setModel(device.getDeviceType().getModel());
			}
			
			deviceItems.add(deviceItem);
		}
		
		return deviceItems;
	}
	
	public String refreshDeviceOnLineStatusService(String deviceId){
		
		Device tempDevice = deviceRepository.findByDid(deviceId);
		
		if(tempDevice != null){
			tempDevice.setLasttime(System.currentTimeMillis());
			deviceRepository.save(tempDevice);
			
			return "Success!";
		}
		else {
			return "Fail!";
		}
	}

}
