package me.quxiu.orderReturn.inf.util;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * XML2VO转换工具
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午3:59:26
 * 
 */
public class XmlTranslateUtil {
	/**
	 * 将xml转换成VO对象
	 * 
	 * @param <T>
	 *            目标对象泛型
	 * @param xml
	 *            要转换成VO的xml字符串
	 * @param clz
	 *            目标对象实际类型
	 * @param alias
	 *            转换过程中的别名
	 * @return 目标对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToPOJO(String xml, Class<T> clz, Map<String, Class<?>> alias) {
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))) {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		Iterator<Entry<String, Class<?>>> entrys = alias.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<String, Class<?>> entry = entrys.next();
			xstream.alias(entry.getKey(), entry.getValue());
		}
		xstream.alias("LINES", List.class);
		xstream.alias("HEADERS", List.class);
		return (T) xstream.fromXML(xml);
	}

	/**
	 * 将xml转换成VO对象,此方法只针对TMS接口文档中进行特殊处理，不具有广泛使用性
	 * 
	 * @param <T>
	 *            目标对象泛型
	 * @param xml
	 *            要转换成VO的xml字符串
	 * @param clz
	 *            目标对象实际类型
	 * @param alias
	 *            转换过程中的别名
	 * @return 目标对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToPOJO(String xml, Class<T> clz, List<Class<?>> alias) {
		xml = StringUtils.trim(xml);
		XStream xstream = new XStream() {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		for (Class ali : alias) {
			xstream.alias("item", ali);
		}
		xstream.alias("HEADERS", List.class);
		return (T) xstream.fromXML(xml);
	}
	
	/**
	 * 将xml转换成VO对象,此方法只针对TMS接口文档中进行特殊处理，不具有广泛使用性
	 * 
	 * @param <T>
	 *            目标对象泛型
	 * @param xml
	 *            要转换成VO的xml文件路径
	 * @param clz
	 *            目标对象实际类型
	 * @param alias
	 *            转换过程中的别名
	 * @return 目标对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToPOJO(File xml, Class<T> clz, List<Class<?>> alias) {
		XStream xstream = new XStream() {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		for (Class ali : alias) {
			xstream.alias("item", ali);
		}
		xstream.alias("root", List.class);
		return (T) xstream.fromXML(xml);
	}

	/**
	 * 将xml转换成VO对象,此方法只针对TMS接口文档中进行特殊处理，不具有广泛使用性
	 * 
	 * @param <T>
	 *            目标对象泛型
	 * @param xml
	 *            要转换成VO的xml输入流
	 * @param clz
	 *            目标对象实际类型
	 * @param alias
	 *            转换过程中的别名
	 * @return 目标对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToPOJO(InputStream xml, Class<T> clz, List<Class<?>> alias) {
		XStream xstream = new XStream() {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		for (Class ali : alias) {
			xstream.alias("item", ali);
		}
		xstream.alias("root", List.class);
		return (T) xstream.fromXML(xml);
	}

	/**
	 * 将VO转换成xml,此方法只针对TMS接口文档中进行特殊处理，不具有广泛使用性
	 * 
	 * @param obj
	 *            要转成xml的对象
	 * @param alias
	 *            转换过程中的别名设置
	 * @return xml字符串
	 */
	@SuppressWarnings("unchecked")
	public static String POJOToXML(Object obj, List<Class<?>> alias) {
		XStream xstream = new XStream(new XppDriver() {

			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new CompactWriter(out, new XmlFriendlyNameCoder("_-", "_")) {

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (text.startsWith(InfConstant.CDATA_PRE) && text.endsWith(InfConstant.CDATA_SUF)) {
							writer.write(text);
						} else {
							super.writeText(writer, text);
						}
					}
				};
			}
		});
		for (Class ali : alias) {
			xstream.alias("HEADER", ali);
		}
		xstream.alias("HEADERS", List.class);
		return xstream.toXML(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static String POJOToXML(Object obj, Class<?> aliasHeader,Class<?> aliasLine) {
		XStream xstream = new XStream(new XppDriver() {

			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new CompactWriter(out, new XmlFriendlyNameCoder("_-", "_")) {

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (text.startsWith(InfConstant.CDATA_PRE) && text.endsWith(InfConstant.CDATA_SUF)) {
							writer.write(text);
						} else {
							super.writeText(writer, text);
						}
					}
				};
			}
		});
		
		xstream.alias("HEADER", aliasHeader);
		xstream.alias("LINE", aliasLine);
		
		xstream.alias("HEADERS", List.class);
		return xstream.toXML(obj);
	}

	/**
	 * 将VO转换成xml,此方法只针对没有CDATA
	 * 
	 * @param obj
	 *            要转成xml的对象
	 * @param alias
	 *            转换过程中的别名设置
	 * @return xml字符串
	 */
	@SuppressWarnings("unchecked")
	public static String POJOToXMLNoCdata(Object obj, List<Class<?>> alias) {
		XStream xstream = new XStream(new XppDriver() {

			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new CompactWriter(out, new XmlFriendlyNameCoder("_-", "_")) {

					@Override
					protected void writeText(QuickWriter writer, String text) {
						// if (text.startsWith(InfConstant.CDATA_PRE) &&
						// text.endsWith(InfConstant.CDATA_SUF)) {
						// text = text.replace(InfConstant.CDATA_PRE,
						// "").replace(InfConstant.CDATA_SUF, "");
						// writer.write(text);
						//
						// }
						if (text == null) {
							text = "";
						} else {
							text =text != null? text.replace(InfConstant.CDATA_PRE, "").replace(InfConstant.CDATA_SUF, "") :"";
						}
						writer.write(text);
					}
				};
			}
		});
		
		xstream.alias("root", List.class);		
		for (Class ali : alias) {
			xstream.alias("item", ali);
		}
		//xstream.registerConverter(new NullConverter()); 
		return xstream.toXML(obj);
	}

	/**
	 * 将VO转换成xml
	 * 
	 * @param obj
	 *            要转成xml的对象
	 * @param alias
	 *            转换过程中的别名设置
	 * @return xml字符串
	 */
	@SuppressWarnings("unchecked")
	public static String POJOToXML(Object obj, Map<String, Class<?>> alias) {
		XStream xstream = new XStream(new XppDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new CompactWriter(out, new XmlFriendlyNameCoder("_-", "_")) {

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (text.startsWith(InfConstant.CDATA_PRE) && text.endsWith(InfConstant.CDATA_SUF)) {

							writer.write(text);
						} else {

							super.writeText(writer, text);
						}
					}
				};
			}

		});
		Iterator<Entry<String, Class<?>>> entrys = alias.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<String, Class<?>> entry = entrys.next();
			xstream.alias(entry.getKey(), entry.getValue());
		}
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xstream.toXML(obj);
	}

	public static String POJOToXML(Object obj, Map<String, Class> alias, Map<Class, String> useAttributes) {
		XStream xstream = new XStream(new XppDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new CompactWriter(out, new XmlFriendlyNameCoder("_-", "_")) {

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (text.startsWith(InfConstant.CDATA_PRE) && text.endsWith(InfConstant.CDATA_SUF)) {

							writer.write(text);
						} else {

							super.writeText(writer, text);
						}
					}
				};
			}

		});
		Iterator<Entry<String, Class>> entrys = alias.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<String, Class> entry = entrys.next();
			xstream.alias(entry.getKey(), entry.getValue());
		}
		if (useAttributes != null) {
			for (Class key : useAttributes.keySet()) {
				xstream.useAttributeFor(key, useAttributes.get(key));
			}
		}
		return xstream.toXML(obj);
	}

	public static <T> T xmlToPOJO(String xml, Class<T> clz, Map<String, Class> alias, Map<Class, String> useAttributes) {
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))) {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		Iterator<Entry<String, Class>> entrys = alias.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<String, Class> entry = entrys.next();
			xstream.alias(entry.getKey(), entry.getValue());
		}
		if (useAttributes != null) {
			for (Class key : useAttributes.keySet()) {
				xstream.useAttributeFor(key, useAttributes.get(key));
			}
		}
		return (T) xstream.fromXML(xml);
	}

	public static <T> T xmlToPOJO(String xml, Class<T> clz, Map<String, Class> alias, Map<Class, String> useAttributes, Map<Class, String> implicatCollections) {
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))) {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {

				return new MapperWrapper(next) {

					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		Iterator<Entry<String, Class>> entrys = alias.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<String, Class> entry = entrys.next();
			xstream.alias(entry.getKey(), entry.getValue());
		}
		if (useAttributes != null) {
			for (Class key : useAttributes.keySet()) {
				xstream.useAttributeFor(key, useAttributes.get(key));
			}
		}
		for (Class key : implicatCollections.keySet()) {
			xstream.addImplicitCollection(key, implicatCollections.get(key));

		}
		return (T) xstream.fromXML(xml);
	}

	public static void main(String[] args) {
		//
		// String responseXml =
		// "<?xml version=\"1.0\" encoding=\"utf-8\"?><root></root>";
		// List<Class<?>> responseAlias = new ArrayList<Class<?>>();
		// responseAlias.add(String.class);
		// List<String> responseIds = XmlTranslateUtil.xmlToPOJO(responseXml,
		// List.class, responseAlias);
		// System.out.println(responseIds);

		System.out.println(Long.MAX_VALUE);
	}
	
	/**
	 * 当传入的对象为null时返回空字符串，不然返回对象的字符串形式
	 * @param o
	 * @return
	 */
	public static String nullHandle(Object o){
		return o==null? "":String.valueOf(o);
	}
}
