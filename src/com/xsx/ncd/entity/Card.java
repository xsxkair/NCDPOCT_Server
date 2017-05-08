package com.xsx.ncd.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="Card")
@Entity
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3043140969197987478L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String lotnum;					//�Լ�������
	
	@JoinColumn(name="Item")
	@ManyToOne
	private Item item;						//�Լ���������Ŀ��Ϣ
	
	private Date makedate;			//��������
	
	private Date perioddate;		//��������
	
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

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public Date getPerioddate() {
		return perioddate;
	}

	public void setPerioddate(Date perioddate) {
		this.perioddate = perioddate;
	}

	public String getVender() {
		return vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

}
