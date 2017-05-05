package com.xsx.ncd.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Device")
@Entity
public class Device implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4205891974622255900L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String did;
	
	@JoinColumn(name="DeviceType")
	@ManyToOne
	private DeviceType devicetype;
	
	private String status;
	
	private Long lasttime;
	
	@JoinColumn(name="Liabler")
	@ManyToOne
	private Operator operator;
	
	@ManyToMany
    @JoinTable(name = "Device_Operator",
            joinColumns = { @JoinColumn(name = "device", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "operator", referencedColumnName = "id") }
	)
	private Set<Operator> operators = new HashSet<>();
	
	@JoinColumn(name="Department")
	@ManyToOne
	private Department department;
	
	private String addr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public DeviceType getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(DeviceType devicetype) {
		this.devicetype = devicetype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLasttime() {
		return lasttime;
	}

	public void setLasttime(Long lasttime) {
		this.lasttime = lasttime;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
