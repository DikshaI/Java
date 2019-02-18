package com.retail.store.CheckOutCounter.repo;

import org.springframework.data.repository.CrudRepository;

import com.retail.store.CheckOutCounter.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

}
