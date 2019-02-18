package com.retail.store.CheckOutCounter.domain;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails {
	private List<Item> listOfItem;
	private double totalCost;
	private double totaltax;
	private double totalBillToBePaid;

	public List<Item> getListOfItem() {
		if (this.listOfItem == null) {
			this.listOfItem = new ArrayList<>();
		}
		return listOfItem;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
	}
	public double getTotalBillToBePaid() {
		return totalBillToBePaid;
	}
	public void setTotalBillToBePaid(double totalBillToBePaid) {
		this.totalBillToBePaid = totalBillToBePaid;
	}
	

}
