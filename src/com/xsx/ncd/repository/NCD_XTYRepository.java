package com.xsx.ncd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsx.ncd.entity.NCD_XTY;

public interface NCD_XTYRepository extends JpaRepository<NCD_XTY, Integer> {

	@Query("select COUNT(n.id) from NCD_XTY n where n.reportresult='Œ¥…Û∫À'")
	public Long findNotHandledReportNum();
	
	@Query("select COUNT(n.id) from NCD_XTY n where n.device.did=:deviceId AND n.reportresult='Œ¥…Û∫À'")
	public Long findThisDeviceNotHandledReportNum(@Param("deviceId")String deviceId);
	
	@Query("select n from NCD_XTY n where n.device.did=:deviceId AND n.reportresult='Œ¥…Û∫À'")
	public List<NCD_XTY> findThisDeviceNotHandledReportList(@Param("deviceId")String deviceId);
}
