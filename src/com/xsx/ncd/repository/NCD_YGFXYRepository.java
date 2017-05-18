package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.NCD_YGFXY;

public interface NCD_YGFXYRepository extends JpaRepository<NCD_YGFXY, Integer> {

	@Query("select COUNT(n.id) from NCD_YGFXY n where n.reportresult='δ���'")
	public Long findNotHandledReportNum();
	
	@Query("select COUNT(n.id) from NCD_YGFXY n where n.device.did=:deviceId AND n.reportresult='δ���'")
	public Long findThisDeviceNotHandledReportNum(@Param("deviceId")String deviceId);
}
