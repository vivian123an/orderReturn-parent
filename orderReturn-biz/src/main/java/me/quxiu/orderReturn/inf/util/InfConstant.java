package me.quxiu.orderReturn.inf.util;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午3:59:26
 * 
 */

public class InfConstant {
	
	  public static final String ITEM_UOM = "EA";
	  public static final String URL_REQUEST = "_REQUEST";
	  public static final String URL_RESPONSE = "_RESPONSE";
	  public static final String InfCreateUser = "Interface";
	  public static final int HTTP_TIMEOUT = 60000;
	  public static String CDATA_PRE = "<![CDATA[";
	  public static String CDATA_SUF = "]]>";
	  public static final String TRIGGER_CRON_EXPRESSION = "0 */2 * * * ?";
	  public static final int DEFAULT_MAX_NUM = 200;
	  public static final int DEFAULT_TIME_DIFFRENCE = 24;
	  public static final String MAX_PRIORITY_SUFFIX = "_MAX_PRIORITY";
	  
	  public class InfWmsPoOfflineStatus
	  {
	    public static final int INITIAL = 100;
	    public static final int SECOND_RETURN_FINISHED = 300;
	    public static final int THIRD_RETURN_FINISHED = 900;
	    
	    public InfWmsPoOfflineStatus() {}
	  }
	  
	  public static abstract interface TransactionType
	  {
	    public static final String TRANSACTIONTYPE_VI_GIFT = "06006";
	    public static final String TRANSACTIONTYPE_SMPLING_OUT_STOCK = "06008";
	    public static final String TRANSACTIONTYPE_RECEIPT = "06009";
	    public static final String TRANSACTIONTYPE_GIFT_OUT_STOCK = "07003";
	    public static final String TRANSACTIONTYPE_INV_GIFT = "06007";
	    public static final String TRANSACTIONTYPE_VI_NOM = "06010";
	  }
	  
	  public static abstract interface Status
	  {
	    public static final String STATUS_ADD = "ADD";
	    public static final String STATUS_NEW = "NEW";
	    public static final String STATUS_UPDATE = "UPDATE";
	  }
	  
	  public class MapName
	  {
	    public static final String INF_CODES = "INF_CODES";
	    public static final String INF_UNIQ_IDS = "UNIQ_IDS";
	    
	    public MapName() {}
	  }
	  
	  public class InfCodes
	  {
	    public static final String INF_EBS_ORDER_RETURN_F_CODE = "B2C_EBS_001";//客退申请单
	    
	    public static final String INF_EBS_ORDER_RETURN_REFUND_F_CODE = "B2C_EBS_001";//客退申请单
		  
		  
		  
		public static final String INF_B2C_ORDER_EXCEPTION_F_CODE = "WMS_B2C_001";
	    public static final String INF_B2C_INV_ADJUSTMENT_F_CODE = "WMS_B2C_002";
	    public static final String INF_B2C_SALE_ORDER_S_CODE = "WMS_B2C_003";
	    public static final String INF_B2C_ORDER_MODIFY_S_CODE = "WMS_B2C_004";
	    public static final String INF_B2C_ORDER_STATUS_F_CODE = "WMS_B2C_005";
	    public static final String INF_B2C_CUST_RETURN_F_CODE = "WMS_B2C_006";
	    public static final String INF_B2C_SALE_ORDER_CANCEL_S_CODE = "WMS_B2C_007";
	    public static final String INF_B2C_SEND_SMS_F_CODE = "WMS_B2C_008";
	    public static final String INF_B2C_SALE_ORDER_STATUS_S_CODE = "WMS_B2C_009";
	    public static final String INF_B2C_PO_OFFLINE_S_CODE = "WMS_B2C_010";
	    public static final String INF_B2C_JIT_MATCH_S_CODE = "WMS_B2C_011";
	    public static final String INF_TMS_OQC_TRANS_F_CODE = "WMS_TMS_001";
	    public static final String INF_TMS_CUST_RETURN_S_CODE = "WMS_TMS_002";
	    public static final String INF_TMS_RETURN_IN_WH_S_CODE = "WMS_TMS_003";
	    public static final String INF_TMS_SHIPMENT_CONFIRM_S_CODE = "WMS_TMS_004";
	    public static final String INF_TMS_AREA_S_CODE = "WMS_TMS_005";
	    public static final String INF_TMS_VENDOR_TRANS_F_CODE = "WMS_TMS_006";
	    public static final String INF_TMS_PALLET_TRANS_NO_S_CODE = "WMS_TMS_007";
	    public static final String INF_TMS_CARRIER_S_CODE = "WMS_TMS_008";
	    public static final String INF_TMS_ORDER_CARRIER_S_CODE = "WMS_TMS_009";
	    public static final String INF_TMS_ADJUST_F_CODE = "WMS_TMS_010";
	    public static final String INF_TMS_CUST_RETURN_F_CODE = "WMS_TMS_011";
	    public static final String INF_EBS_INV_ADJUSTMENT_F_CODE = "WMS_EBS_001";
	    public static final String INF_EBS_COMPANY_S_CODE = "WMS_EBS_002";
	    public static final String INF_EBS_VENDOR_RETURN_S_CODE = "WMS_EBS_003";
	    public static final String INF_EBS_VENDOR_S_CODE = "WMS_EBS_004";
	    public static final String INF_EBS_ITEM_INFO_S_CODE = "WMS_EBS_005";
	    public static final String INF_EBS_ITEM_CATEGORY_S_CODE = "WMS_EBS_006";
	    public static final String INF_EBS_BRAND_S_CODE = "WMS_EBS_007";
	    public static final String INF_EBS_PO_S_CODE = "WMS_EBS_008";
	    public static final String INF_EBS_PO_MODIFY_S_CODE = "WMS_EBS_009";
	    public static final String INF_EBS_PO_RECEIPT_F_CODE = "WMS_EBS_010";
	    public static final String INF_EBS_PO_STATUS_F_CODE = "WMS_EBS_011";
	    public static final String INF_EBS_TRANS_SHIPMENT_S_CODE = "WMS_EBS_012";
	    public static final String INF_EBS_INV_TRANS_COMMAND_S_CODE = "WMS_EBS_013";
	    public static final String INF_EBS_SALE_ORDER_F_CODE = "WMS_EBS_015";
	    public static final String INF_EBS_JIT_REPICK_F_CODE = "WMS_EBS_016";
	    public static final String INF_EBS_TRANS_SHIPMENT_F_CODE = "WMS_EBS_017";
	    public static final String INF_EBS_TRANS_HEADER_S_CODE = "WMS_EBS_019";
	    public static final String INF_EBS_PORETURN_ADR_S_CODE = "WMS_EBS_020";
	    public static final String INF_WMS_TRANS_RECEIPT_S_CODE = "WMS_WMS_001";
	    public static final String INF_WMS_TRANS_RECEIPT_F_CODE = "WMS_WMS_002";
	    public static final String INF_EBS_ALLOCATE_COMMAND_S_CODE = "WMS_EBS_014";
	    public static final String INF_EBS_JIT_RECEIPT_S_CODE = "WMS_EBS_018";
	    public static final String WMS_SYS_SHIP_AUTOTRANSFER = "WMS_SYS_AUTOTRANSFER";
	    public static final String WMS_SYS_SHIP_OUTBOUND = "WMS_SYS_SHIP_OUTBOUND";
	    public static final String WMS_SYS_SEND_MAIL = "WMS_SYS_SEND_MAIL";
	    public static final String WMS_SYS_PO_RECEIVED_INF = "WMS_SYS_PO_RECEIVED_INF";
	    public static final String WMS_SYS_JIT_REPICK_CODE = "WMS_SYS_JIT_REPICK";
	    
	    public InfCodes() {}
	  }
	  
	  public class DeleteType
	  {
	    public static final String CANCEL_SCAN_SC = "CANCEL_SCAN_SC";
	    
	    public DeleteType() {}
	  }
	  
	  public class poTransfetType
	  {
	    public static final String PO_RETURN = "RETURN";
	    public static final String PO_RECEIPT = "RECEIPT";
	    
	    public poTransfetType() {}
	  }
	  
	  public class logInfo
	  {
	    public static final String LOG_RESPONSE = " RESPONSE:";
	    public static final String LOG_REQUEST = " REQUEST:";
	    public static final String LOG_SCM_TO_INF_START = "上位系统到接口表开始:";
	    public static final String LOG_SCM_TO_INF_START_EX = "上位系统到接口表(priority>0)开始:";
	    public static final String LOG_INF_TO_SCM_START = "接口到上位系统开始:";
	    public static final String LOG_INF_TO_SCM_START_EX = "接口到上位系统(priority>0)开始:";
	    public static final String LOG_INF_TO_BIZ_START = "接口到业务表开始:";
	    public static final String LOG_INF_TO_BIZ_START_EX = "接口到业务表(priority>0)开始:";
	    public static final String LOG_BIZ_TO_INF_START = "业务到接口表开始:";
	    public static final String LOG_BIZ_TO_INF_START_EX = "业务到接口表(priority>0)开始:";
	    public static final String LOG_INF_TO_BIZ_SUCCESS = "接口表到业务表数据传输成功， 接口数据ID：";
	    public static final String LOG_NO_DATA = "没有传输的数据";
	    
	    public logInfo() {}
	  }
	  
	  public class PayType_Ext
	  {
	    public static final String Cash = "现金";
	    public static final String Pos = "Pos机";
	    
	    public PayType_Ext() {}
	  }
	  
	  public class Priority
	  {
	    public static final long DEFAULT_PRIORITY = 0L;
	    public static final long DEFAULT_SHIP_PRIORITY = 5L;
	    public static final int DEFAULT_MAX_PRIORITY = 3;
	    
	    public Priority() {}
	  }
	  
	  public class processState
	  {
	    public static final long NOT_PROCESS = 1L;
	    public static final long HAS_PROCESS = 2L;
	    public static final long HAS_ERROR = 3L;
	    public static final long HAS_DELETED = 9L;
	    
	    public processState() {}
	  }
	  
	  public class B2cOrderStatus
	  {
	    public static final String INTOWAREHOUSE = "004";
	    public static final String PICKED = "005";
	    public static final String OUTOFSTOCK = "006";
	    public static final String PACKAGED = "007";
	    
	    public B2cOrderStatus() {}
	  }
	  
	  public class Configuration
	  {
	    public static final String NOT_TRUE = "0";
	    public static final String ONLY_NUMBER = "1";
	    public static final String NOT_ACTIVE = "2";
	    public static final String ONLY_ZERO = "3";
	    
	    public Configuration() {}
	  }
}
