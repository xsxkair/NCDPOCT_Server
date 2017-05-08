package com.xsx.ncd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsx.ncd.entity.Card;
import com.xsx.ncd.entity.Repertory;
import com.xsx.ncd.repository.CardRepository;
import com.xsx.ncd.repository.RepertoryRepository;

@Service
public class RepertoryService {

	@Autowired RepertoryRepository repertoryRepository;
	@Autowired CardRepository cardRepository;
	
	public Repertory saveRepertoryRecordService(Repertory repertory){
		Card card = cardRepository.findByLotnum(repertory.getCard().getLotnum());
		if(card == null){
			card = repertory.getCard();
			cardRepository.save(card);
		}
		repertory.setCard(card);
		
		return repertoryRepository.save(repertory);
	}
	
	public Object[] readRepertoryInfoByCardLotService(String lotnum){
		return repertoryRepository.findCardInfoAndNumByLot(lotnum);
	}
	
	public List<String> queryAllLotnumLikeThisLotnum(String lotnum){
		return cardRepository.findLotnumByLotnumLike(lotnum);
	}
}
