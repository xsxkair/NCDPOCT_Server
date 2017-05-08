/**
 * 
 */
package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	
	
	public Card findByLotnum(String lotnum);
	
	@Query("select c.lotnum from Card c where c.lotnum like %:lotnum% ")
	public List<String> findLotnumByLotnumLike(@Param("lotnum")String lotnum);
}
