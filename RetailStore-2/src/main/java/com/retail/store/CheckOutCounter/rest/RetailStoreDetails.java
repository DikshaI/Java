package com.retail.store.CheckOutCounter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.store.CheckOutCounter.domain.Item;
import com.retail.store.CheckOutCounter.domain.ItemDetails;
import com.retail.store.CheckOutCounter.service.RetailStoreDetailsSevice;

@RestController
public class RetailStoreDetails {

	@Autowired
	private RetailStoreDetailsSevice retailStoreDetailsSevice;

	/**
	 * Retrieve All the records from database
	 */
	@GetMapping("/items")
	public List<Item> getAllItem() {
		return retailStoreDetailsSevice.getAllItems();
	}

	/**
	 * @param item Adds an Item to the database
	 */
	@PostMapping("/items")
	public List<Item> addAnItemToDataBase(@RequestBody Item item) {
		return retailStoreDetailsSevice.addItemToDataBase(item);
	}

	/**
	 * @param item
	 * @param id   updates an item in the database
	 */
	@PutMapping("/items/{id}")
	public List<Item> updateAnItem(@RequestBody Item item, @PathVariable int id) {
		return retailStoreDetailsSevice.addItemToDataBase(item);
	}

	/**
	 * @param id Deletes an item from the database
	 */
	@DeleteMapping("items/{id}")
	public List<Item> deleteAnItem(@PathVariable int id) {
		return retailStoreDetailsSevice.deleteItemFromDataBase(id);
	}

	/**
	 * @param item
	 * @param id   Adds an item to the cart
	 */
	@PostMapping("/cart/add/{id}")
	public List<Item> addAnItemToCart( @PathVariable int id,@RequestBody Item item) {
		return retailStoreDetailsSevice.addAnItemToTheCart(item, id);
	}

	/**
	 * @param id remove an item from cart
	 */
	@DeleteMapping("/cart/delete/{id}")
	public ItemDetails deleteAnItemFromCart(@PathVariable int id) {
		return retailStoreDetailsSevice.deleteItemFromCart(id);
	}

	/**
	 * Generates the Bill
	 */
	@GetMapping("cart/billDetails")
	public ItemDetails getTheBillingDetails() {
		return retailStoreDetailsSevice.getTheBillDetails();

	}

}
