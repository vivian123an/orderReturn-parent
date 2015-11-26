package me.quxiu.orderReturn.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月5日 上午11:17:30
 * 
 */

public class OrderReturnType implements Serializable{

	private static final long serialVersionUID = -6333924710940404977L;

	private Integer id;

    private Integer code;

    private String name;

    private Boolean isEffect;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @JsonIgnore
    public Boolean getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Boolean isEffect) {
        this.isEffect = isEffect;
    }
	
	
}
