package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.Repertory;

public interface RepertoryRepository extends JpaRepository<Repertory, Integer> {
	
	@Query("select c, sum(r.num) from Repertory r left join Card c on c.lotnum =: lotnum")
	public Object[] findCardInfoAndNumByLot(@Param("lotnum")String lotnum);
}
