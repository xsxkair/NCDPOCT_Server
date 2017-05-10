package com.xsx.ncd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="Card")
@Entity
public class Card {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String lotnum;					//�Լ�������
	
	@JoinColumn(name="Item")
	@ManyToOne
	private Item item;						//�Լ���������Ŀ��Ϣ
	
	private java.sql.Timestamp makedate;			//��������
	
	private java.sql.Timestamp perioddate;		//��������
	
	private String vender;					//����

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLotnum() {
		return lotnum;
	}

	public void setLotnum(String lotnum) {
		this.lotnum = lotnum;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public java.sql.Timestamp getMakedate() {
		return makedate;
	}

	public void setMakedate(java.sql.Timestamp makedate) {
		this.makedate = makedate;
	}

	public java.sql.Timestamp getPerioddate() {
		return perioddate;
	}

	public void setPerioddate(java.sql.Timestamp perioddate) {
		this.perioddate = perioddate;
	}

	public String getVender() {
		return vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

}
