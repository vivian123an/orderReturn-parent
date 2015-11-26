package me.quxiu.orderReturn.base.impl;

import java.io.Serializable;
import java.util.List;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.base.BaseService;
import me.quxiu.orderReturn.util.BaseUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;

public abstract class BaseServiceImpl<T> implements BaseService<T>, InitializingBean {

	protected BaseMapper<T> mapper = null;

	public Class<T> entityClass;

	protected final transient Log logger = LogFactory.getLog(getClass());

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		entityClass = BaseUtils.getSuperClassGenricType(getClass());
	}

	public final void afterPropertiesSet() throws IllegalArgumentException,
			BeanInitializationException {
		initMapper();
		if (mapper == null) {
			logger.info("Base mapper is not set. Please set it in the initManager method if database access is desired!");
		}
	}
	
	/**
	 * 子类需要重载这个方法来实现初始化，包括设置mapper
	 * 
	 */
	protected abstract void initMapper();
	
	
	@Override
	public int save(T t) {
		return mapper.save(t);
	}

	@Override
	public int update(T t) {
		return mapper.update(t);
	}

	@Override
	public T get(Serializable id) {
		return mapper.get(id);
	}
	
	@Override
	public int saveBatch(List<T> ts){
		return mapper.saveBatch(ts);
	}
	
}
