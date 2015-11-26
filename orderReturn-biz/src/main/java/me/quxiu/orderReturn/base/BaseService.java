package me.quxiu.orderReturn.base;

import java.io.Serializable;
import java.util.List;

/**
 * 公共Service接口，定义常用方法
 * 
 * @author wenan.chen@quxiu.me
 * @since  2015-07-21
 */
public interface BaseService<T> {

	/**
	 * 保存数据，过滤空的字段
	 * 
	 * @param t 实体对象
	 * @return int
	 * @throws
	 */
	int save(T t);

	/**
	 * 根据主键更新非空的字段
	 * 
	 * @param t 实体对象
	 * @return int
	 * @throws
	 */
	int update(T t);

	/**
	 * 根据主键查询表数据
	 * 
	 * @param id 主键
	 * @return T
	 * @throws
	 */
	T get(Serializable id);
	
	/**
	 * 批量保存数据，过滤空的字段
	 * 
	 * @param t 实体对象集
	 * @return int
	 * @throws
	 */
	int saveBatch(List<T> ts);
}
