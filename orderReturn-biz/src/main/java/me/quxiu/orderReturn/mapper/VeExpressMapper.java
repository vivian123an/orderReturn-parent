package me.quxiu.orderReturn.mapper;

import java.util.List;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.model.VeExpress;

public interface VeExpressMapper extends BaseMapper<VeExpress>{

	/**
	 * 查询快递公司列表（不含print_tmpl、config字段）
	 * @return
	 */
	public List<VeExpress> getSimpleExpressAll();
}