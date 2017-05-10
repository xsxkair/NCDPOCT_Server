package com.xsx.ncd.handler;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsx.ncd.entity.Device;
import com.xsx.ncd.repository.DeviceRepository;

@Controller
public class DeviceTypeHandler {
	
	@Autowired DeviceRepository deviceRepository;
	
	@ResponseBody
	@RequestMapping(value="/SaveDeviceType")
	public Device saveDeviceTypeHandler(@RequestBody Device deviceType) {
		return deviceRepository.save(deviceType);
	}
	
	@ResponseBody
	@RequestMapping(value="/DeleteDeviceType")
	public Boolean deleteDeviceTypeHandler(@RequestBody Device deviceType) {
		 
		deviceRepository.delete(deviceType);
		 
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/SaveDeviceTypeAndIco")
	public Boolean saveDeviceTypeAndIcoHandler(@RequestParam("deviceType")String deviceTypeInfo,
			@RequestParam("offico") CommonsMultipartFile offIcoFile,
			@RequestParam("onico") CommonsMultipartFile onIcoFile,
			@RequestParam("errorico") CommonsMultipartFile errorIcoFile
			){

		try {
			ObjectMapper mapper = new ObjectMapper();
			Device deviceType = mapper.readValue(deviceTypeInfo, Device.class);
			
			String offPath="/var/NCD_DeviceIco/" + offIcoFile.getOriginalFilename();
			String onPath="/var/NCD_DeviceIco/" + onIcoFile.getOriginalFilename();
			String errorPath="/var/NCD_DeviceIco/" + errorIcoFile.getOriginalFilename();
			
			File direct = new File("/var/NCD_DeviceIco/");
			if(!direct.exists())
				direct.mkdir();

			File newFile1=new File(offPath);
			//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			offIcoFile.transferTo(newFile1);
			
			File newFile2=new File(onPath);
			//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			onIcoFile.transferTo(newFile2);
			
			File newFile3=new File(errorPath);
			//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			errorIcoFile.transferTo(newFile3);
			
			deviceType.setOfficon(offPath);
			deviceType.setOnicon(onPath);
			deviceType.setErroricon(errorPath);
			deviceRepository.save(deviceType);
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
