package me.quxiu.orderReturn.mapper;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.model.InfConfiguration;

public interface InfConfigurationMapper extends BaseMapper<InfConfiguration>{

	String getByKey(String key);
}