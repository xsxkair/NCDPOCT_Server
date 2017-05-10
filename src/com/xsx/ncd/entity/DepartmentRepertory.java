package com.xsx.ncd.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="DepartmentRepertory")
@Entity
public class DepartmentRepertory {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@JoinColumn(name="Card")
	@ManyToOne
	private Card card;								//操作的卡信息
	
	private Integer num;							//操作数目
	
	private java.sql.Timestamp time;				//操作时间
	
	@JoinColumn(name="Operator")
	@ManyToOne
	private User operator;							//操作人
	
	@JoinColumn(name="User")
	@ManyToOne
	private User user;								//领用人
	
	@JoinColumn(name="Department")
	@ManyToOne
	private Department department;					//科室名
	
	private String detailed;						//明细
	
	private String dsc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public java.sql.Timestamp getTime() {
		return time;
	}

	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	
}
