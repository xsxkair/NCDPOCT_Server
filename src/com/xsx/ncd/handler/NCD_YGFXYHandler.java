package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.service.NCD_YGFXYService;

@Controller
public class NCD_YGFXYHandler {

	@Autowired NCD_YGFXYService ncd_YGFXYService;
	
	@ResponseBody
	@RequestMapping("/YGFXY_Data")
	public String upLoadYGFXYDataHandler(NCD_YGFXY ncd_YGFXY, String itemCode, Integer userId, String deviceId){
		System.out.println(deviceId);
		return ncd_YGFXYService.upLoadYGFXYDataService(ncd_YGFXY, itemCode, userId, deviceId);
	}
	
	
}
