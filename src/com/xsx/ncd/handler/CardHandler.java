package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Card;
import com.xsx.ncd.entity.Repertory;
import com.xsx.ncd.service.CardService;
import com.xsx.ncd.service.RepertoryService;

@Controller
public class CardHandler {

	@Autowired CardService cardService;
	
	@ResponseBody
	@RequestMapping(value="/QueryCardLotNumLikeThis")
	public List<String> queryCardLotNumLikeThisHandler(@RequestBody Object lot) {
		return cardService.queryAllLotnumLikeThisLotnumService((String) lot);
	}
	
	@ResponseBody
	@RequestMapping(value="/QueryCardByLotNum")
	public Card queryCardByLotNumHandler(@RequestBody Object card) {
		return cardService.queryCardByLotNumService((String) card);
	}
}
