package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.NCD_XTY;
import com.xsx.ncd.entity.NCD_YGFXY;

public interface NCD_YGFXYRepository extends JpaRepository<NCD_YGFXY, Integer> {

	@Query("select COUNT(n.id) from NCD_YGFXY n where n.reportisok is null")
	public Long findNotHandledReportNum();
	
	@Query("select COUNT(n.id) from NCD_YGFXY n where n.device.did=:deviceId AND n.reportisok is null")
	public Long findThisDeviceNotHandledReportNum(@Param("deviceId")String deviceId);
	
	@Query("select n from NCD_YGFXY n where n.device.did=:deviceId AND n.reportisok is null")
	public List<NCD_YGFXY> findThisDeviceNotHandledReportList(@Param("deviceId")String deviceId);
	
	public NCD_YGFXY findBySerialnum(String serialNum);
}
