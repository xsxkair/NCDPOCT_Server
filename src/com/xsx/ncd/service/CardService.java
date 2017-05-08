package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Card;
import com.xsx.ncd.repository.CardRepository;

@Service
public class CardService {

	@Autowired CardRepository cardRepository;

	public List<String> queryAllLotnumLikeThisLotnumService(String lotnum){
		return cardRepository.findLotnumByLotnumLike(lotnum);
	}
	
	public Card queryCardByLotNumService(String lotnum){
		return cardRepository.findByLotnum(lotnum);
	}
}
