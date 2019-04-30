package com.gseem.lesson04.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Gson工具类
 * @author liangpengju-ds
 */
public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,
            // 显示形式是"key":null，而直接new出来的就没有"key":null的
            gson= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    private GsonUtil() {
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String toGsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }


    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }


    /**
     * 转成list
     * 解决泛型在编译期类型被擦除导致报错
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }


    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> gsonToMap(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}
