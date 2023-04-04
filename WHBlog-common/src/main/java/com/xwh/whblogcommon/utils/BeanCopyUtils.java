package com.xwh.whblogcommon.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    public static <T> T copyBean(Object source, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            T t = constructor.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> copyList(List<?> source, Class<T> tClass) {
        return source.stream().map(item -> copyBean(item, tClass)).collect(Collectors.toList());
    }
}
