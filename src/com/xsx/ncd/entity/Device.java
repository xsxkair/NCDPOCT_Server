package com.xsx.ncd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="Device")
@Entity
public class Device {


	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@Column(nullable=false)
	private String did;
	
	@Column(nullable=false)
	private String name;
	
	private String status;
	
	private String icopath;
	
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
	
	@ManyToMany
    @JoinTable(name = "Device_Item",
            joinColumns = { @JoinColumn(name = "device", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "item", referencedColumnName = "id") }
	)
	private Set<Item> items = new HashSet<>();
	
	@JoinColumn(name="Department")
	@ManyToOne
	private Department department;
	
	private String addr;
	
	private String vender;
	
	private String venderphone;
	
	private String venderaddr;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcopath() {
		return icopath;
	}

	public void setIcopath(String icopath) {
		this.icopath = icopath;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String getVender() {
		return vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

	public String getVenderphone() {
		return venderphone;
	}

	public void setVenderphone(String venderphone) {
		this.venderphone = venderphone;
	}

	public String getVenderaddr() {
		return venderaddr;
	}

	public void setVenderaddr(String venderaddr) {
		this.venderaddr = venderaddr;
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
