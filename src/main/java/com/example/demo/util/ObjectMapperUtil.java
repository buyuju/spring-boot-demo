package com.example.demo.util;

import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectMapperUtil {
	private static ObjectMapperConfig mapper = new ObjectMapperConfig();

	public static ObjectMapperConfig getJsonMapper() {
		return mapper;
	}

    public static String toJsonString(Object object){
        try{
            return mapper.writeValueAsString(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }




    /**
     * 把JSON转成Map
     * @param json
     * @param keyclazz
     * @param valueclazz
     * @return
     */
    @SuppressWarnings({ "rawtypes"})
    public static Object getJsonToMap(String json,Class keyclazz,Class valueclazz){
        Object object=null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object= mapper.readValue(json, TypeFactory.defaultInstance().constructParametricType(HashMap.class, keyclazz,valueclazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }


    /**
     * 把对象转成字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        String json=null;
        try {
            json= mapper.writeValueAsString(object);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * @param obj
     *            转换的对象值
     * @param clz
     *            类对象
     * @return 转换后的对象
     */
    public static <T> T transferObject(Object obj, Class clz) {
        T result = null;
        if (obj != null && !obj.equals("")) {
            Method[] methods = obj.getClass().getMethods();
            try {
                result = (T) clz.newInstance();
            } catch (Exception e1) {
                return null;
            }
            Method m;
            for (int i = 0; i < methods.length; i++) {
                m = methods[i];
                try {
                    if (m.getName().startsWith("set")) {
                        String fieldName = m.getName().replaceFirst("set", "");
                        Method method = result.getClass().getMethod(
                                m.getName(), m.getParameterTypes());
                        Method getMethod = obj.getClass().getMethod(
                                "get" + fieldName, new Class[] {});
                        method.invoke(result,
                                getMethod.invoke(obj, new Object[] {}));
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return result;
    }


    public static <T> List<T> transferObjectList(List list, Class clz) {
        List<T> result = new ArrayList<T>();
        T t;
        for (Object o : list) {
            t = transferObject(o, clz);
            result.add(t);
        }
        return result;
    }


    /**
     * json字符串转换成Class
     * @param jsonString
     * @param
     * @param <T>
     * @return
     */
    public static <T> T transferJsonToObject(String jsonString, Class clz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T)mapper.readValue(jsonString, clz);
        } catch (IOException e) {
        }
        return null;
    }
}
