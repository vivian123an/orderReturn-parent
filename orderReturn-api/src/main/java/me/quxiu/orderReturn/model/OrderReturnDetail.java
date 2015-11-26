package me.quxiu.orderReturn.model;

import java.io.Serializable;
import java.math.BigDecimal;

import me.quxiu.share.model.BaseEntity;

/**
 * 客退申请单明细
 * @author  wenan.chen@quxiu.me
 * @version 2015年10月19日 下午5:03:08
 */

public class OrderReturnDetail extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -885122124411192034L;

	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 客退申请单ID
     */
    private Integer orderReturnHeaderId;
    /**
     * 退货原因 (1:产品与描述不符，2：不喜欢/不好看，3：做工瑕疵，4：发错货，5：商品包裹破损，6：漏发商品/附件，
     * 7：褪色掉色，8：颜色/款式与描述不符，9：材质/面料与描述不符，10：其他)
     */
    private Integer returnReason;    
    /**
     * 商品条码
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品数量
     */
    private Integer qty;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 商品单价(按实际售价)
     */
    private Double unitPrice;

    /**
     * 商品单位
     */
    private String skuUnit;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * PO单号
     */
    private String poNo;

    /**
     * 备注
     */
    private String memo;

    /**
     * sku扣点
     */
    private BigDecimal discount;

    /**
	 * 主键
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 客退申请单ID
	 * @return orderReturnHeaderId
	 */
	public Integer getOrderReturnHeaderId() {
		return orderReturnHeaderId;
	}

	/**
	 * 客退申请单ID
	 * @param orderReturnHeaderId
	 */
	public void setOrderReturnHeaderId(Integer orderReturnHeaderId) {
		this.orderReturnHeaderId = orderReturnHeaderId;
	}
	/**
	 * 商品条码
	 * @return skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * 商品条码
	 * @param skuCode
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * 商品名称
	 * @return skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * 商品名称
	 * @param skuName
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * 商品数量
	 * @return qty
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * 商品数量
	 * @param qty
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	/**
	 * 总价
	 * @return totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * 总价
	 * @param totalPrice
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * 商品单价(按实际售价)
	 * @return unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * 商品单价(按实际售价)
	 * @param unitPrice
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * 商品单位
	 * @return skuUnit
	 */
	public String getSkuUnit() {
		return skuUnit;
	}

	/**
	 * 商品单位
	 * @param skuUnit
	 */
	public void setSkuUnit(String skuUnit) {
		this.skuUnit = skuUnit;
	}

	/**
	 * 供应商ID
	 * @return supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * 供应商ID
	 * @param supplierId
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * PO单号
	 * @return poNo
	 */
	public String getPoNo() {
		return poNo;
	}

	/**
	 * PO单号
	 * @param poNo
	 */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	/**
	 * 备注
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 备注
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 退货原因(1:产品与描述不符，2：不喜欢不好看，3：做工瑕疵，4：发错货，5：商品包裹破损，6：漏发商品附件，7：褪色掉色，8：颜色款式与描述不符，9：材质面料与描述不符，10：其他)
	 * @return returnReason
	 */
	public Integer getReturnReason() {
		return returnReason;
	}

	/**
	 * 退货原因(1:产品与描述不符，2：不喜欢不好看，3：做工瑕疵，4：发错货，5：商品包裹破损，6：漏发商品附件，7：褪色掉色，8：颜色款式与描述不符，9：材质面料与描述不符，10：其他)
	 * @param returnReason
	 */
	public void setReturnReason(Integer returnReason) {
		this.returnReason = returnReason;
	}

	/**
	 * sku扣点
	 * @return discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * sku扣点
	 * @param discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
	
}