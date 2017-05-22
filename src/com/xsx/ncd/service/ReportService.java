package com.xsx.ncd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.define.DeviceItem;
import com.xsx.ncd.define.DeviceReportItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.entity.DeviceType;
import com.xsx.ncd.entity.NCD_XTY;
import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.repository.DeviceRepository;
import com.xsx.ncd.repository.DeviceTypeRepository;
import com.xsx.ncd.repository.NCD_XTYRepository;
import com.xsx.ncd.repository.NCD_YGFXYRepository;

@Service
public class ReportService {

	@Autowired NCD_YGFXYRepository ncd_YGFXYRepository;
	@Autowired NCD_XTYRepository ncd_XTYRepository;
	@Autowired DeviceTypeRepository deviceTypeRepository;
	@Autowired DeviceRepository deviceRepository;
	
	public Long queryAllNotHandledReportNum(){
		List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
		Long sumNum = new Long(0);
		
		for (DeviceType deviceType : deviceTypes) {
			if(deviceType.getCode().equals("NCD_YGFXY"))
				sumNum += ncd_YGFXYRepository.findNotHandledReportNum();
			else if(deviceType.getCode().equals("NCD_XTY"))
				sumNum += ncd_XTYRepository.findNotHandledReportNum();
		}
		
		return sumNum;
	}
	
	public Long queryThisDeviceNotHandledReportNum(String deviceId, String deviceTypeCode){

		Long sumNum = new Long(0);
		
		switch (deviceTypeCode) {
		case "NCD_YGFXY":
			sumNum = ncd_YGFXYRepository.findThisDeviceNotHandledReportNum(deviceId);
			break;

		case "NCD_XTY":
			sumNum = ncd_XTYRepository.findThisDeviceNotHandledReportNum(deviceId);
			break;
			
		default:
			break;
		}
		
		return sumNum;
	}
	
	public List<Long> queryThisDeviceNotHandledReportNumAndLastTime(String deviceId, String deviceTypeCode){

		List<Long> datas = new ArrayList<>();
		Long sumNum = new Long(0);
		
		//查询设备未审核报告数目
		switch (deviceTypeCode) {
		case "NCD_YGFXY":
			datas.add(ncd_YGFXYRepository.findThisDeviceNotHandledReportNum(deviceId));
			break;

		case "NCD_XTY":
			datas.add(ncd_XTYRepository.findThisDeviceNotHandledReportNum(deviceId));
			break;
			
		default:
			break;
		}
		
		//查询设备上次在线时间
		Device device = deviceRepository.findByDid(deviceId);
		datas.add(device.getLasttime());
		
		return datas;
	}
	
	public RecordJson<DeviceReportItem> queryDeviceReportNotHandledService(Integer id){
		
		RecordJson<DeviceReportItem> recordJson = new RecordJson<>();
		List<DeviceReportItem> deviceItems = new ArrayList<>();
		
		//查询设备在线时间
		Device device = deviceRepository.findOne(id);
		
		//查询设备未审核报告
		switch (device.getDeviceType().getCode()) {
		
		case "NCD_YGFXY":
			List<NCD_YGFXY> NCD_YGFXYLists = ncd_YGFXYRepository.findThisDeviceNotHandledReportList(device.getDid());
			
			for (NCD_YGFXY ncd_YGFXY : NCD_YGFXYLists) {
				DeviceReportItem deviceReportItem = new DeviceReportItem(ncd_YGFXY.getId(), ncd_YGFXY.getTesttime(), ncd_YGFXY.getUptime(), ncd_YGFXY.getSampleid(),
						null);
				if(ncd_YGFXY.getOperator() != null)
					deviceReportItem.setOperatorName(ncd_YGFXY.getOperator().getName());
				
				deviceItems.add(deviceReportItem);
			}
			break;

		case "NCD_XTY":
			List<NCD_XTY> NCD_XTYLists = ncd_XTYRepository.findThisDeviceNotHandledReportList(device.getDid());
			for (NCD_XTY ncd_XTY : NCD_XTYLists) {
				DeviceReportItem deviceReportItem = new DeviceReportItem(ncd_XTY.getId(), ncd_XTY.getTesttime(), ncd_XTY.getUptime(), ncd_XTY.getSampleid(),
						null);
				if(ncd_XTY.getOperator() != null)
					deviceReportItem.setOperatorName(ncd_XTY.getOperator().getName());
				
				deviceItems.add(deviceReportItem);
			}
			break;

		default:
			break;
		}
		
		recordJson.setParm1(device.getLasttime());
		recordJson.setRecords(deviceItems);
		
		return recordJson;
	}
	
	public Object queryDeviceReportService(String deviceType, Integer reportId){
		
		//查询设备未审核报告
		switch (deviceType) {
		
		case "NCD_YGFXY":
			NCD_YGFXY ncd_YGFXY = ncd_YGFXYRepository.findOne(reportId);
			return ncd_YGFXY;

		case "NCD_XTY":
			NCD_XTY ncd_XTY = ncd_XTYRepository.findOne(reportId);
			return ncd_XTY;

		default:
			break;
		}

		return null;
	}
}
