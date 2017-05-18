package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.NCD_XTY;
import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.service.NCD_XTYService;
import com.xsx.ncd.service.NCD_YGFXYService;

@Controller
public class NCD_XTYHandler {

	@Autowired NCD_XTYService ncd_XTYService;
	
	@ResponseBody
	@RequestMapping("/Up_NCDXTYData")
	public String up_NCDXTYDataHandler(NCD_XTY ncd_XTY, String itemCode, Integer userId, String deviceId){
		return ncd_XTYService.upLoadXTYDataService(ncd_XTY, itemCode, userId, deviceId);
	}
}
