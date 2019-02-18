package com.retail.store.CheckOutCounter.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.store.CheckOutCounter.domain.Item;
import com.retail.store.CheckOutCounter.domain.ItemDetails;
import com.retail.store.CheckOutCounter.repo.ItemRepository;

@Service
public class RetailStoreDetailsSevice {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemTaxAndCostService itemTaxAndCostService;

	public List<Item> getAllItems() {
		List<Item> itemList = (List<Item>) itemRepository.findAll();
		itemList.sort(new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if (o1.getItemNo() < o2.getItemNo()) {
					return -1;
				} else if (o1.getItemNo() > o2.getItemNo()) {
					return +1;
				} else {
					return 0;
				}
			}});
		return itemList;
	}

	public List<Item> addItemToDataBase(Item item) {
		item = itemTaxAndCostService.calculateTheTaxAndTotalCost(item);
		itemRepository.save(item);
		return getAllItems();
	}

	public List<Item> deleteItemFromDataBase(int id) {
		itemRepository.deleteById(id);
		return getAllItems();
	}

	public List<Item> addAnItemToTheCart(Item item, int id) {
		Optional<Item> item1 = itemRepository.findById(id);
		try {
			Item item2 = item1.get();
			item2.setItemSelected(true);
			itemRepository.save(item2);
		} catch (NoSuchElementException e) {
			item = itemTaxAndCostService.calculateTheTaxAndTotalCost(item);
			item.setItemSelected(true);
			itemRepository.save(item);
		}
		return getAllItems().parallelStream().filter(new Predicate<Item>() {

			@Override
			public boolean test(Item t) {
				return t.isItemSelected();
			}
		}).collect(Collectors.toList());
	}

	public ItemDetails deleteItemFromCart(int id) {
		ItemDetails itemDetails = new ItemDetails();
		List itemlist = getAllItems();
		getTheItemAddedToCart(itemDetails, itemlist);
		itemDetails.getListOfItem().remove(itemDetails.getListOfItem().parallelStream().filter(new Predicate<Item>() {

			@Override
			public boolean test(Item t) {
				if (t.getItemNo() == id) {
					return true;
				}
				return false;
			}
		}).findFirst().get());
		Optional<Item> item=itemRepository.findById(id);
		try {
		Item item2=item.get();
		item2.setItemSelected(false);
		itemRepository.save(item2);
		}catch(NoSuchElementException e) {
			
		}
		itemTaxAndCostService.getTheBillDetails(itemDetails);
		return itemDetails;
	}

	public ItemDetails getTheBillDetails() {
		ItemDetails itemDetails = new ItemDetails();
		List itemlist = getAllItems();
		getTheItemAddedToCart(itemDetails, itemlist);
		itemTaxAndCostService.getTheBillDetails(itemDetails);
		return itemDetails;
	}

	private void getTheItemAddedToCart(ItemDetails itemDetails, List itemlist) {
		itemDetails.getListOfItem().addAll((List<Item>) itemlist.parallelStream().filter(new Predicate<Item>() {

			@Override
			public boolean test(Item t) {
				return t.isItemSelected();
			}
		}).collect(Collectors.toList()));
	}
}
