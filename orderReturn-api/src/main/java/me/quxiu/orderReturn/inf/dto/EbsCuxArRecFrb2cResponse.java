package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.List;

public class EbsCuxArRecFrb2cResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141090654136245710L;
	
	private List<EbsCuxArRecFrb2cItemResponse> items;

	public List<EbsCuxArRecFrb2cItemResponse> getItems() {
		return items;
	}

	public void setItems(List<EbsCuxArRecFrb2cItemResponse> items) {
		this.items = items;
	}
	
	

}
