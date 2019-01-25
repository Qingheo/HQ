package com.bdqn.hq.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.bdqn.hq.anno.Bean;
@SuppressWarnings("all")
public class BeanFactory {

	private Map<String, Object> map = new HashMap<>();
	private String packageName;

	public BeanFactory(String packageName) {
		this.packageName = packageName;
		init();
	}

	public Object getBean(String name) {
		return map.get(name);
	}

	// 获取到所有的类名
	public void init() {
		String[] names = getFileName(packageName);
		for (String string : names) {
			String fullName = packageName.replaceAll("/", ".") + "." + string;
			Class clazz = getClazz(fullName);
			Bean bean = (Bean) clazz.getAnnotation(Bean.class);
			if (bean!= null) {
				try {
					Object obj = clazz.newInstance();
					// 获取他的属性
					Field[] fields = clazz.getDeclaredFields();

					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						
						Method method = clazz.getDeclaredMethod("set" + toFirstUpper(field.getName()), field.getType());
						method.invoke(obj, field.getType().newInstance());
					}
					map.put(bean.value(),(obj));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String[] getFileName(String packageName) {
		String path = BeanFactory.class.getClassLoader().getResource("").getPath();
		File filePath = new File(path + packageName);
		String[] names = filePath.list();
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].substring(0, names[i].lastIndexOf("."));
		}

		return names;
	}

	public Class getClazz(String cname) {

		try {
			Class clazz = Class.forName(cname);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toFirstUpper(String string) {

		return (char) (string.charAt(0) - 32) + string.substring(1);
	}

}
