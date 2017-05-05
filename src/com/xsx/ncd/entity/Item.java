package com.xsx.ncd.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Item")
@Entity
public class Item implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7752780793735001924L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String code;					//��Ŀ����
		
	private String name;					//��Ŀ��
	
	private String namecn;					//������
	
	private Integer accuracy;				//����
	
	private String unit;					//��λ
	
	private Float minv;				//��ͼ����
		
	private Float maxv;				//��߼����
	
	private Float nlowvalue1;				//�ο�ֵ1����
	
	private Float nhighvalue1;			//�ο�ֵ1����
	
	private Integer age1;					//�ο�ֵ1����ֶ�
	
	private Float nlowvalue2;				//�ο�ֵ2����
	
	private Float nhighvalue2;			//�ο�ֵ2����
	
	private Integer age2;					//�ο�ֵ2����ֶ�
	
	private Float nlowvalue3;				//�ο�ֵ3����

	private Float nhighvalue3;			//�ο�ֵ3����
	
	private Integer age3;					//�ο�ֵ3����ֶ�
	
	private Float nlowvalue4;				//�ο�ֵ4����
	
	private Float nhighvalue4;			//�ο�ֵ4����

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamecn() {
		return namecn;
	}

	public void setNamecn(String namecn) {
		this.namecn = namecn;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getMinv() {
		return minv;
	}

	public void setMinv(Float minv) {
		this.minv = minv;
	}

	public Float getMaxv() {
		return maxv;
	}

	public void setMaxv(Float maxv) {
		this.maxv = maxv;
	}

	public Float getNlowvalue1() {
		return nlowvalue1;
	}

	public void setNlowvalue1(Float nlowvalue1) {
		this.nlowvalue1 = nlowvalue1;
	}

	public Float getNhighvalue1() {
		return nhighvalue1;
	}

	public void setNhighvalue1(Float nhighvalue1) {
		this.nhighvalue1 = nhighvalue1;
	}

	public Integer getAge1() {
		return age1;
	}

	public void setAge1(Integer age1) {
		this.age1 = age1;
	}

	public Float getNlowvalue2() {
		return nlowvalue2;
	}

	public void setNlowvalue2(Float nlowvalue2) {
		this.nlowvalue2 = nlowvalue2;
	}

	public Float getNhighvalue2() {
		return nhighvalue2;
	}

	public void setNhighvalue2(Float nhighvalue2) {
		this.nhighvalue2 = nhighvalue2;
	}

	public Integer getAge2() {
		return age2;
	}

	public void setAge2(Integer age2) {
		this.age2 = age2;
	}

	public Float getNlowvalue3() {
		return nlowvalue3;
	}

	public void setNlowvalue3(Float nlowvalue3) {
		this.nlowvalue3 = nlowvalue3;
	}

	public Float getNhighvalue3() {
		return nhighvalue3;
	}

	public void setNhighvalue3(Float nhighvalue3) {
		this.nhighvalue3 = nhighvalue3;
	}

	public Integer getAge3() {
		return age3;
	}

	public void setAge3(Integer age3) {
		this.age3 = age3;
	}

	public Float getNlowvalue4() {
		return nlowvalue4;
	}

	public void setNlowvalue4(Float nlowvalue4) {
		this.nlowvalue4 = nlowvalue4;
	}

	public Float getNhighvalue4() {
		return nhighvalue4;
	}

	public void setNhighvalue4(Float nhighvalue4) {
		this.nhighvalue4 = nhighvalue4;
	}
	
	
}
