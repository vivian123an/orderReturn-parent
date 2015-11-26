package me.quxiu.orderReturn.model;

import java.io.Serializable;

public class VeExpress implements Serializable{
	
	private static final long serialVersionUID = -1588215868272079444L;

	private Integer id;

    private String className;

    private String name;

    private Boolean isEffect;

    private String nameLetter;
    
    private String printTmpl;

    private String config;

    public String getPrintTmpl() {
        return printTmpl;
    }

    public void setPrintTmpl(String printTmpl) {
        this.printTmpl = printTmpl == null ? null : printTmpl.trim();
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Boolean isEffect) {
        this.isEffect = isEffect;
    }

    public String getNameLetter() {
        return nameLetter;
    }

    public void setNameLetter(String nameLetter) {
        this.nameLetter = nameLetter == null ? null : nameLetter.trim();
    }
}