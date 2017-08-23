/**
 * MIT License
 * <p>
 * Copyright (c) 2017-2027 PeKnight(JKpeknight@gmail.com)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.peknight.test.client.creator;

import com.peknight.common.logging.LogUtils;
import com.peknight.test.thrift.reflect.BeanCall;
import com.peknight.test.thrift.reflect.ClassInfo;
import com.peknight.test.thrift.reflect.ConstructorCall;
import com.peknight.test.thrift.reflect.ConstructorInfo;
import com.peknight.test.thrift.reflect.MethodCall;
import com.peknight.test.thrift.reflect.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author PeKnight
 *
 * Created by PeKnight on 2017/8/11.
 */
public final class PekTestContext {
    private PekTestContext() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(PekTestContext.class);

    private static final Map<String, BeanCall> BEAN_CALL_MAP = new HashMap<>();

    private static final Map<String, ConstructorCall> CONSTRUCTOR_CALL_MAP = new HashMap<>();

    private static final Map<String, MethodCall> METHOD_CALL_MAP = new HashMap<>();

    private static final Map<String, ClassInfo> CLASS_INFO_MAP = new HashMap<>();

    private static final Map<String, ConstructorInfo> CONSTRUCTOR_INFO_MAP = new HashMap<>();

    private static final Map<String, MethodInfo> METHOD_INFO_MAP = new HashMap<>();

    private static final Map<String, List<String>> STRING_LIST_MAP = new HashMap<>();

    public static void put(String key, BeanCall value) {
        BEAN_CALL_MAP.put(key, value);
    }

    public static void put(String key, ConstructorCall value) {
        CONSTRUCTOR_CALL_MAP.put(key, value);
    }

    public static void put(String key, MethodCall value) {
        METHOD_CALL_MAP.put(key, value);
    }

    public static void put(String key, ClassInfo value) {
        CLASS_INFO_MAP.put(key, value);
    }

    public static void put(String key, ConstructorInfo value) {
        CONSTRUCTOR_INFO_MAP.put(key, value);
    }

    public static void put(String key, MethodInfo value) {
        METHOD_INFO_MAP.put(key, value);
    }

    public static void put(String key, List<String> value) {
        STRING_LIST_MAP.put(key, value);
    }

    public static void put(BeanCall value) {
        BEAN_CALL_MAP.put(value.getBeanName(), value);
    }

    public static void put(ConstructorCall value) {
        int index = 0;
        while (CONSTRUCTOR_CALL_MAP.containsKey(value.getClassName() + "[" + index + "]")) {
            index++;
        }
        CONSTRUCTOR_CALL_MAP.put(value.getClassName() + "[" + index + "]", value);
    }

    public static void put(MethodCall value) {
        int index = 0;
        while (METHOD_CALL_MAP.containsKey(value.getClassName() + "." + value.getMethodName() + "[" + index + "]")) {
            index++;
        }
        METHOD_CALL_MAP.put(value.getClassName() + "." + value.getMethodName() + "[" + index + "]", value);
    }

    public static void put(ClassInfo value) {
        int index = 0;
        while (CLASS_INFO_MAP.containsKey(value.getClassName() + "[" + index + "]")) {
            index++;
        }
        CLASS_INFO_MAP.put(value.getClassName() + "[" + index + "]", value);
    }

    public static void put(ConstructorInfo value) {
        int index = 0;
        while (CONSTRUCTOR_INFO_MAP.containsKey(value.getClassName() + "[" + index + "]")) {
            index++;
        }
        CONSTRUCTOR_INFO_MAP.put(value.getClassName() + "[" + index + "]", value);
    }

    public static void put(MethodInfo value) {
        int index = 0;
        while (METHOD_INFO_MAP.containsKey(value.getClassName() + "." + value.getMethodName() + "[" + index + "]")) {
            index++;
        }
        METHOD_INFO_MAP.put(value.getClassName() + "." + value.getMethodName() + "[" + index + "]", value);
    }

    public static BeanCall getBeanCall(String key) {
        return BEAN_CALL_MAP.get(key);
    }

    public static ConstructorCall getConstructorCall(String key) {
        return CONSTRUCTOR_CALL_MAP.get(key);
    }

    public static MethodCall getMethodCall(String key) {
        return METHOD_CALL_MAP.get(key);
    }

    public static ClassInfo getClassInfo(String key) {
        return CLASS_INFO_MAP.get(key);
    }

    public static ConstructorInfo getConstructorInfo(String key) {
        return CONSTRUCTOR_INFO_MAP.get(key);
    }

    public static MethodInfo getMethodInfo(String key) {
        return METHOD_INFO_MAP.get(key);
    }

    public static List<String> getStringList(String key) {
        return STRING_LIST_MAP.get(key);
    }

    public static BeanCall removeBeanCall(String key) {
        return BEAN_CALL_MAP.remove(key);
    }

    public static ConstructorCall removeConstructorCall(String key) {
        return CONSTRUCTOR_CALL_MAP.remove(key);
    }

    public static MethodCall removeMethodCall(String key) {
        return METHOD_CALL_MAP.remove(key);
    }

    public static ClassInfo removeClassInfo(String key) {
        return CLASS_INFO_MAP.remove(key);
    }

    public static ConstructorInfo removeConstructorInfo(String key) {
        return CONSTRUCTOR_INFO_MAP.remove(key);
    }

    public static MethodInfo removeMethodInfo(String key) {
        return METHOD_INFO_MAP.remove(key);
    }

    public static List<String> removeStringList(String key) {
        return STRING_LIST_MAP.remove(key);
    }

    public static void listBeanCall() {
        for (Map.Entry<String, BeanCall> beanCallEntry : BEAN_CALL_MAP.entrySet()) {
            LogUtils.info(LOGGER, beanCallEntry);
        }
    }

    public static void listConstructorCall() {
        for (Map.Entry<String, ConstructorCall> constructorCallEntry : CONSTRUCTOR_CALL_MAP.entrySet()) {
            LogUtils.info(LOGGER, constructorCallEntry);
        }
    }

    public static void listMethodCall() {
        for (Map.Entry<String, MethodCall> methodCallEntry : METHOD_CALL_MAP.entrySet()) {
            LogUtils.info(LOGGER, methodCallEntry);
        }
    }

    public static void listClassInfo() {
        for (Map.Entry<String, ClassInfo> classInfoEntry : CLASS_INFO_MAP.entrySet()) {
            LogUtils.info(LOGGER, classInfoEntry);
        }
    }

    public static void listConstructorInfo() {
        for (Map.Entry<String, ConstructorInfo> constructorInfoEntry : CONSTRUCTOR_INFO_MAP.entrySet()) {
            LogUtils.info(LOGGER, constructorInfoEntry);
        }
    }

    public static void listMethodInfo() {
        for (Map.Entry<String, MethodInfo> methodInfoEntry : METHOD_INFO_MAP.entrySet()) {
            LogUtils.info(LOGGER, methodInfoEntry);
        }
    }

    public static void listStringList() {
        for (Map.Entry<String, List<String>> stringListEntry : STRING_LIST_MAP.entrySet()) {
            LogUtils.info(LOGGER, stringListEntry);
        }
    }
}
