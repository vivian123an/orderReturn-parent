package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.List;

public class EbsRefundStatusResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2669663494759630668L;
	
	private List<EbsRefundStatusResponseItemDto> ebsRefundStatusResponseItemDtos;

	public List<EbsRefundStatusResponseItemDto> getEbsRefundStatusResponseItemDtos() {
		return ebsRefundStatusResponseItemDtos;
	}

	public void setEbsRefundStatusResponseItemDtos(
			List<EbsRefundStatusResponseItemDto> ebsRefundStatusResponseItemDtos) {
		this.ebsRefundStatusResponseItemDtos = ebsRefundStatusResponseItemDtos;
	}



	
	
	
	

}
