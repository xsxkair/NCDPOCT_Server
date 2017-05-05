package com.xsx.ncd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.ncd.entity.Item;
import com.xsx.ncd.repository.ItemRepository;

@Controller
public class ItemHandler {

	@Autowired ItemRepository itemRepository;
	
	@ResponseBody
	@RequestMapping(value="/ReadAllItems")
	public List<Item> readAllItemsHandler() {
		return itemRepository.findAll();
	}
}
