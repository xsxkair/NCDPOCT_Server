package com.xsx.ncd.handler;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Card;
import com.xsx.ncd.entity.Repertory;
import com.xsx.ncd.service.RepertoryService;

@Controller
public class RepertoryHandler {

	@Autowired RepertoryService repertoryService;
	
	@ResponseBody
	@RequestMapping(value="/SaveRepertoryRecord")
	public Repertory saveRepertoryRecordHandler(@RequestBody Repertory repertory) {
		return repertoryService.saveRepertoryRecordService(repertory);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryRepertoryNumByCard")
	public Long queryRepertoryNumByCard(@RequestBody Card card) {
		return repertoryService.queryRepertoryNumByCardService(card);
	}
}
