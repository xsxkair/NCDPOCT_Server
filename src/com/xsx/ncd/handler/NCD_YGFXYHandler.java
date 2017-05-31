package com.xsx.ncd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.NCD_YGFXY;
import com.xsx.ncd.repository.NCD_YGFXYRepository;
import com.xsx.ncd.service.NCD_YGFXYService;

@Controller
public class NCD_YGFXYHandler {

	@Autowired NCD_YGFXYService ncd_YGFXYService;
	@Autowired NCD_YGFXYRepository ncd_YGFXYRepository;
	
	@ResponseBody
	@RequestMapping("/YGFXY_Data")
	public String upLoadYGFXYDataHandler(NCD_YGFXY ncd_YGFXY, String itemCode, Integer userId, String deviceId){

		return ncd_YGFXYService.upLoadYGFXYDataService(ncd_YGFXY, itemCode, userId, deviceId);
	}
	
	@ResponseBody
	@RequestMapping("/QueryNcdYGFXYReportById")
	public NCD_YGFXY queryNcdYGFXYReportByIdHandler(Integer reportId){

		return ncd_YGFXYRepository.findOne(reportId);
	}
	
	@ResponseBody
	@RequestMapping("/SaveNcdYGFXYReport")
	public String saveNcdYGFXYReportHandler(@RequestBody NCD_YGFXY report){

		ncd_YGFXYRepository.save(report);
		
		return "Success";
	}
}
