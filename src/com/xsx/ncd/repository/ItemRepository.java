package com.xsx.ncd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsx.ncd.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item findByCode(String code);
}
