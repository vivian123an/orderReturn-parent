package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.List;

public class EbsCuxArRecFrb2c implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3663361930459246040L;
	
	
	private List<EbsCuxArRecFrb2cItem> items;


	public List<EbsCuxArRecFrb2cItem> getItems() {
		return items;
	}


	public void setItems(List<EbsCuxArRecFrb2cItem> items) {
		this.items = items;
	}
	
	
	
	

}
