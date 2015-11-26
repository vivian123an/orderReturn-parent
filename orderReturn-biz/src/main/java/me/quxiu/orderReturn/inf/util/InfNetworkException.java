package me.quxiu.orderReturn.inf.util;

/**
 * 接口调用异常
 * <p>
 * 调用其它系统接口时，出现网络或其它通讯问题时，抛此异常
 * {@link com.wef.inf.core.utils.client.HttpClientUtil}
 * @version 1.0
 */
public class InfNetworkException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348442235623096506L;

	public InfNetworkException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfNetworkException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InfNetworkException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InfNetworkException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
