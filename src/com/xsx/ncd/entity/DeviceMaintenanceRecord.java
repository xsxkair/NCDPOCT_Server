package com.xsx.ncd.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="DeviceMaintenanceRecord")
@Entity
public class DeviceMaintenanceRecord implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 2009773139949817302L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;

	private java.sql.Timestamp testtime;
	
	@JoinColumn(name="Device")
	@ManyToOne
	private Device device;
	
	@JoinColumn(name="Operator")
	@ManyToOne
	private Operator operator;
	
	private String dsc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.sql.Timestamp getTesttime() {
		return testtime;
	}

	public void setTesttime(java.sql.Timestamp testtime) {
		this.testtime = testtime;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	
	
}
