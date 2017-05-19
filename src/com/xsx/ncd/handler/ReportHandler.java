package com.xsx.ncd.handler;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.define.DeviceReportItem;
import com.xsx.ncd.define.RecordJson;
import com.xsx.ncd.service.ReportService;

@Controller
public class ReportHandler {

	@Autowired ReportService reportService;
	
	@ResponseBody
	@RequestMapping(value="/QueryAllNotHandledReportNum")
	public Long queryAllNotHandledReportNumHandler() {
		return reportService.queryAllNotHandledReportNum();
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryThisDeviceNotHandledReportNum")
	public Long queryThisDeviceNotHandledReportNumHandler(String deviceId, String deviceTypeCode) {
		return reportService.queryThisDeviceNotHandledReportNum(deviceId, deviceTypeCode);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryThisDeviceNotHandledReportNumAndLastTime")
	public List<Long> queryThisDeviceNotHandledReportNumAndLastTimeHandler(String deviceId, String deviceTypeCode) {
		return reportService.queryThisDeviceNotHandledReportNumAndLastTime(deviceId, deviceTypeCode);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryDeviceReportNotHandled")
	public RecordJson<DeviceReportItem> queryDeviceReportNotHandledHandler(Integer id) {
		return reportService.queryDeviceReportNotHandledService(id);
	}
}
