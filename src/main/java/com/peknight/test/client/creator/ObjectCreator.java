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

import com.peknight.common.io.ScannerUtils;
import com.peknight.common.reflect.material.BeanContext;
import com.peknight.test.client.shell.BasicInputDispatcher;
import com.peknight.test.thrift.reflect.BeanCall;
import com.peknight.test.thrift.reflect.ClassInfo;
import com.peknight.test.thrift.reflect.ConstructorCall;
import com.peknight.test.thrift.reflect.ConstructorInfo;
import com.peknight.test.thrift.reflect.MethodCall;
import com.peknight.test.thrift.reflect.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 * @author PeKnight
 *
 * Created by PeKnight on 2017/8/11.
 */
@Component
public class ObjectCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectCreator.class);

    @Resource
    private Scanner scanner;

    @Resource
    private BasicInputDispatcher basicInputDispatcher;

    public String next() {
        return ScannerUtils.next(scanner, basicInputDispatcher);
    }

    public String setName() {
        return ScannerUtils.formatNext(BeanContext.BEAN_NAME_REG, scanner, LOGGER);
    }

    public boolean confirm() {
        if ("y".equals(next())) {
            return true;
        } else {
            return false;
        }
    }

    public ClassInfo getClassInfoFromContext() {
        LOGGER.info("Input ClassInfo Key:");
        String input = next();
        return PekTestContext.getClassInfo(input);
    }

    public ConstructorInfo getConstructorInfoFromContext() {
        LOGGER.info("Input ConstructorInfo Key:");
        String input = next();
        return PekTestContext.getConstructorInfo(input);
    }

    public MethodInfo getMethodInfoFromContext() {
        LOGGER.info("Input MethodInfo Key:");
        String input = next();
        return PekTestContext.getMethodInfo(input);
    }

    public BeanCall getBeanCallFromContext() {
        LOGGER.info("Input BeanCall Key:");
        String input = next();
        return PekTestContext.getBeanCall(input);
    }

    public ConstructorCall getConstructorCallFromContext() {
        LOGGER.info("Input ConstructorCall Key:");
        String input = next();
        return PekTestContext.getConstructorCall(input);
    }

    public MethodCall getMethodCallFromContext() {
        LOGGER.info("Input MethodCall Key:");
        String input = next();
        return PekTestContext.getMethodCall(input);
    }

    public List<String> getStringListFromContext() {
        LOGGER.info("Input StringList Key:");
        String input = next();
        return PekTestContext.getStringList(input);
    }

    public BeanCall getBeanCall(ClassInfo classInfo) {
        LOGGER.info("Get BeanCall From Context? (y/n)");
        if (confirm()) {
            return getBeanCallFromContext();
        } else if (classInfo == null) {
            return createBeanCall();
        } else {
            return createBeanCall(classInfo);
        }
    }

    public ConstructorCall getConstructorCall(ConstructorInfo constructorInfo, String className) {
        LOGGER.info("Get ConstructorCall From Context? (y/n)");
        if (confirm()) {
            return getConstructorCallFromContext();
        } else if (constructorInfo == null) {
            return createConstructorCall(className);
        } else {
            return createConstructorCall(constructorInfo);
        }
    }

    public MethodCall getMethodCall(MethodInfo methodInfo) {
        LOGGER.info("Get MethodCall From Context? (y/n)");
        if (confirm()) {
            return getMethodCallFromContext();
        } else if (methodInfo == null) {
            return createMethodCall();
        } else {
            return createMethodCall(methodInfo);
        }
    }

    public List<String> getStringList() {
        LOGGER.info("Get String List From Context? (y/n)");
        if (confirm()) {
            return getStringListFromContext();
        } else {
            return createStringList();
        }
    }

    public void saveBeanCall(BeanCall beanCall) {
        LOGGER.info("Save This BeanCall? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Key For This BeanCall");
            PekTestContext.put(setName(), beanCall);
        }
    }

    public void saveConstructorCall(ConstructorCall constructorCall) {
        LOGGER.info("Save This ConstructorCall? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Key For This ConstructorCall");
            PekTestContext.put(setName(), constructorCall);
        }
    }

    public void saveMethodCall(MethodCall methodCall) {
        LOGGER.info("Save This MethodCall? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Key For This MethodCall");
            PekTestContext.put(setName(), methodCall);
        }
    }

    public void saveStringList(List<String> stringList) {
        LOGGER.info("Save This List? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Key For This List:");
            PekTestContext.put(setName(), stringList);
        }
    }

    public List<String> getParamStringList(List<ClassInfo> paramList) {
        if (paramList == null) {
            return null;
        }
        List<String> stringList = new ArrayList<>(paramList.size());
        for (ClassInfo classInfo : paramList) {
            stringList.add(classInfo.getClassName());
        }
        return stringList;
    }

    public void setBeanCallFields(BeanCall beanCall) {
        LOGGER.info("Set Bean Name? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Bean Name");
            beanCall.setBeanName(setName());
        }

        LOGGER.info("Set Bean Value? (y/n)");
        if (confirm()) {
            LOGGER.info("Input Bean Value");
            beanCall.setBeanValue(next());
        }

        LOGGER.info("Set Method? (y/n)");
        if (confirm()) {
            LOGGER.info("Get Method From Contest? (y/n)");
            MethodCall method = null;
            if (confirm()) {
                method = getMethodCallFromContext();
            } else {
                method = createMethodCall();
            }
            beanCall.setMethod(method);
        }

        LOGGER.info("Set Components? (y/n)");
        if (confirm()) {
            LOGGER.info("Set Collection (y) / Set Map (n)");
            if (confirm()) {
                List<BeanCall> collectionComponents = new ArrayList<>();
                do {
                    collectionComponents.add(getBeanCall(null));
                    LOGGER.info("Add another one? (y/n)");
                    if (!confirm()) {
                        break;
                    }
                } while (true);
                beanCall.setCollectionComponents(collectionComponents);
            } else {
                List<List<BeanCall>> mapComponents = new ArrayList<>();
                do {
                    List<BeanCall> entry = new ArrayList<>(2);
                    LOGGER.info("Set Key:");
                    entry.add(getBeanCall(null));
                    LOGGER.info("Set Value:");
                    entry.add(getBeanCall(null));
                    mapComponents.add(entry);
                    LOGGER.info("Add another entry? (y/n)");
                    if (!confirm()) {
                        break;
                    }
                } while (true);
                beanCall.setMapComponents(mapComponents);
            }
        }
    }

    public BeanCall createBeanCall(ClassInfo classInfo) {
        BeanCall beanCall = new BeanCall();
        beanCall.setDeclaredClassName(classInfo.getClassName());
        List<ClassInfo> implementClassList = classInfo.getImplementClassList();
        if (implementClassList != null && implementClassList.size() > 0) {
            LOGGER.info("Use An Implements Class? (y/n)");
            if (confirm()) {
                for (int i = 0; i < implementClassList.size(); i++) {
                    LOGGER.info("[{}] {}", i, implementClassList.get(i).getClassName());
                }
                LOGGER.info("Input Index Number:");
                int index = Integer.parseInt(next());
                classInfo = implementClassList.get(index);
            }
        }
        beanCall.setActualClassName(classInfo.getClassName());
        LOGGER.info("Set Constructor? (y/n)");
        if (confirm()) {
            LOGGER.info("Get Constructor From Context? (y/n)");
            ConstructorCall constructorCall = null;
            if (confirm()) {
                constructorCall = getConstructorCallFromContext();
            } else {
                List<ConstructorInfo> constructorList = classInfo.getConstructorList();
                for (int i = 0; i < constructorList.size(); i++) {
                    LOGGER.info("[{}] {}", i, constructorList.get(i).getClassName());
                }
                LOGGER.info("Input Index Number");
                int index = Integer.parseInt(next());
                constructorCall = createConstructorCall(classInfo.getConstructorList().get(index));
            }
            beanCall.setConstructor(constructorCall);
        }
        setBeanCallFields(beanCall);
        saveBeanCall(beanCall);
        return beanCall;
    }


    public BeanCall createBeanCall() {
        LOGGER.info("Create Bean From Class Info? (y/n)");
        if (confirm()) {
            ClassInfo classInfo = getClassInfoFromContext();
            return createBeanCall(classInfo);
        } else {
            BeanCall beanCall = new BeanCall();
            LOGGER.info("Input Declared Class Name:");
            beanCall.setDeclaredClassName(next());
            LOGGER.info("Input Actual Class Name:");
            beanCall.setActualClassName(next());

            LOGGER.info("Set Constructor? (y/n)");
            if (confirm()) {
                beanCall.setConstructor(getConstructorCall(null, beanCall.getActualClassName()));
            }
            setBeanCallFields(beanCall);
            saveBeanCall(beanCall);
            return beanCall;
        }
    }

    public List<BeanCall> setParamList(List<ClassInfo> infoParamList) {
        List<BeanCall> paramList = new ArrayList<>(infoParamList.size());
        for (ClassInfo classInfo : infoParamList) {
            LOGGER.info("Set Param {}:", classInfo.getClassName());
            paramList.add(getBeanCall(classInfo));
        }
        return paramList;
    }

    public List<BeanCall> setParamList() {
        List<BeanCall> paramList = new ArrayList<>();
        while (true) {
            LOGGER.info("Set Param: ");
            paramList.add(getBeanCall(null));
            LOGGER.info("Add another one? (y/n)");
            if (!confirm()) {
                break;
            }
        }
        return paramList;
    }

    public ConstructorCall createConstructorCall(ConstructorInfo constructorInfo) {
        ConstructorCall constructorCall = new ConstructorCall();
        constructorCall.setClassName(constructorInfo.getClassName());
        LOGGER.info("Set Constructor Param List?");
        if (confirm()) {
            List<ClassInfo> infoParamList = constructorInfo.getParamList();
            constructorCall.setParamList(setParamList(infoParamList));
        }
        saveConstructorCall(constructorCall);
        return constructorCall;
    }

    public ConstructorCall createConstructorCall(String className) {
        LOGGER.info("Create ConstructorCall From Constructor Info? (y/n)");
        if (confirm()) {
            ConstructorInfo constructorInfo = getConstructorInfoFromContext();
            return createConstructorCall(constructorInfo);
        } else {
            ConstructorCall constructorCall = new ConstructorCall();
            if (className == null) {
                LOGGER.info("Set Class Name:");
                constructorCall.setClassName(next());
            } else {
                constructorCall.setClassName(className);
            }
            LOGGER.info("Set Constructor Param List?");
            if (confirm()) {
                constructorCall.setParamList(setParamList());
            }
            saveConstructorCall(constructorCall);
            return constructorCall;
        }
    }

    public void setMethodCallFields(MethodCall methodCall) {
        LOGGER.info("Set Invoker? (y/n)");
        if (confirm()) {
            methodCall.setInvoker(getBeanCall(null));
        }
        LOGGER.info("Set Return Bean Name? (y/n)");
        if (confirm()) {
            LOGGER.info("Set Return Bean Name:");
            methodCall.setReturnBeanName(setName());
        }
    }

    public MethodCall createMethodCall(MethodInfo methodInfo) {
        MethodCall methodCall = new MethodCall();
        methodCall.setClassName(methodInfo.getClassName());
        methodCall.setMethodName(methodInfo.getMethodName());
        List<ClassInfo> infoParamList = methodInfo.getParamList();
        if (infoParamList != null && infoParamList.size() != 0) {
            LOGGER.info("Set Method Param List:");
            methodCall.setParamList(setParamList(methodInfo.getParamList()));
        }
        setMethodCallFields(methodCall);
        saveMethodCall(methodCall);
        return methodCall;
    }

    public MethodCall createMethodCall() {
        LOGGER.info("Create MethodCall From Method Info? (y/n)");
        if (confirm()) {
            MethodInfo methodInfo = getMethodInfoFromContext();
            return createMethodCall(methodInfo);
        } else {
            MethodCall methodCall = new MethodCall();
            LOGGER.info("Input Class Name:");
            methodCall.setClassName(next());
            LOGGER.info("Input Method Name:");
            methodCall.setMethodName(next());
            List<BeanCall> paramList;
            LOGGER.info("Set Param List? (y/n)");
            if (confirm()) {
                paramList = setParamList();
            } else {
                paramList = new ArrayList<>(0);
            }
            methodCall.setParamList(paramList);
            setMethodCallFields(methodCall);
            saveMethodCall(methodCall);
            return methodCall;
        }
    }

    public List<String> createStringList() {
        LOGGER.info("Create A String List");
        List<String> stringList = new ArrayList<>();
        while (true) {
            stringList.add(next());
            LOGGER.info("Add another one? (y/n)");
            if (!confirm()) {
                break;
            }
        }
        saveStringList(stringList);
        return stringList;
    }
}
