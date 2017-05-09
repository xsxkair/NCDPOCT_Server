package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.Card;
import com.xsx.ncd.entity.Repertory;

public interface RepertoryRepository extends JpaRepository<Repertory, Integer> {
	
	@Query("select sum(r.num) from Repertory r where r.card=:card")
	public Long findCardInfoAndNumByCard(@Param("card")Card card);
}
