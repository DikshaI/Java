package com.retail.store.CheckOutCounter.service;

import org.springframework.stereotype.Service;

import com.retail.store.CheckOutCounter.Constants.ApplicationConstants;
import com.retail.store.CheckOutCounter.domain.Item;
import com.retail.store.CheckOutCounter.domain.ItemDetails;

@Service
public class ItemTaxAndCostService {

	public Item calculateTheTaxAndTotalCost(Item item) {
		if (ApplicationConstants.Category.A.equals(item.getItemCategory())) {
			item.setTax(item.getItemCost() * 0.1);

		} else if (ApplicationConstants.Category.B.equals(item.getItemCategory())) {
			item.setTax(item.getItemCost() * 0.2);
		} else {
			item.setTax(0);
		}
		item.setCost(item.getItemCost() + item.getTax());
		return item;

	}

	public void getTheBillDetails(ItemDetails itemDetails) {
		
		for(Item item:itemDetails.getListOfItem()) {
			itemDetails.setTotalCost(itemDetails.getTotalCost()+item.getItemCost());
			itemDetails.setTotaltax(itemDetails.getTotaltax()+item.getTax());
			itemDetails.setTotalBillToBePaid(itemDetails.getTotalBillToBePaid()+item.getCost());
		}
	}

}
