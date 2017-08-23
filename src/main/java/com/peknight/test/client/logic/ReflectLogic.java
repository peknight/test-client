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
package com.peknight.test.client.logic;

import com.peknight.test.client.creator.ObjectCreator;
import com.peknight.test.client.creator.PekTestContext;
import com.peknight.test.thrift.reflect.ActionResult;
import com.peknight.test.thrift.reflect.BeanCall;
import com.peknight.test.thrift.reflect.ClassInfo;
import com.peknight.test.thrift.reflect.MethodCall;
import com.peknight.test.thrift.reflect.MethodInfo;
import com.peknight.test.thrift.reflect.ReflectService;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 * @author PeKnight
 *
 * Created by PeKnight on 2017/8/11.
 */
@Component
public class ReflectLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectLogic.class);

    @Resource
    private ObjectCreator objectCreator;

    @Resource
    private TTransport pekTestClientTTransport;

    @Resource
    private ReflectService.Iface reflectServiceClient;

    public void getClassInfo() {
        LOGGER.info("Input ClassName:");
        String className = objectCreator.next();
        LOGGER.info("Set searchPackages:");
        List<String> searchPackages = objectCreator.getStringList();
        try {
            pekTestClientTTransport.open();
            ClassInfo classInfo = reflectServiceClient.getClassInfo(className, searchPackages);
            pekTestClientTTransport.close();
            LOGGER.info(classInfo.toString());
            PekTestContext.put(classInfo);
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void listClassInfo() {
        LOGGER.info("Set basePackages:");
        List<String> basePackages = objectCreator.getStringList();

        LOGGER.info("Get searchPackages:");
        List<String> searchPackages = objectCreator.getStringList();

        try {
            pekTestClientTTransport.open();
            List<ClassInfo> classInfoList = reflectServiceClient.listClassInfo(basePackages, searchPackages);
            pekTestClientTTransport.close();
            for (ClassInfo classInfo : classInfoList) {
                LOGGER.info(classInfo.toString());
                PekTestContext.put(classInfo);
            }
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void getMethodInfo() {
        LOGGER.info("Input ClassName:");
        String className = objectCreator.next();
        LOGGER.info("Input MethodName:");
        String methodName = objectCreator.next();
        LOGGER.info("Set paramList:");
        List<String> paramList = objectCreator.getStringList();
        LOGGER.info("Set searchPackages:");
        List<String> searchPackages = objectCreator.getStringList();
        try {
            pekTestClientTTransport.open();
            MethodInfo methodInfo = reflectServiceClient.getMethodInfo(className, methodName, paramList, searchPackages);
            pekTestClientTTransport.close();
            LOGGER.info(methodInfo.toString());
            PekTestContext.put(methodInfo);
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void listMethodInfo() {
        LOGGER.info("Input ClassName:");
        String className = objectCreator.next();
        LOGGER.info("Set searchPackages:");
        List<String> searchPackages = objectCreator.getStringList();
        try {
            pekTestClientTTransport.open();
            List<MethodInfo> methodInfoList = reflectServiceClient.listMethodInfo(className, searchPackages);
            pekTestClientTTransport.close();
            for (MethodInfo methodInfo : methodInfoList) {
                LOGGER.info(methodInfo.toString());
                PekTestContext.put(methodInfo);
            }
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void listBean() {
        try {
            pekTestClientTTransport.open();
            List<BeanCall> beanCallList = reflectServiceClient.listBean();
            pekTestClientTTransport.close();
            for (BeanCall beanCall : beanCallList) {
                LOGGER.info(beanCall.toString());
                PekTestContext.put(beanCall);
            }
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void createBean() {
        BeanCall beanCall = objectCreator.getBeanCall(null);
        try {
            pekTestClientTTransport.open();
            ActionResult actionResult = reflectServiceClient.createBean(beanCall);
            pekTestClientTTransport.close();
            LOGGER.info(actionResult.toString());
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }
    }

    public void invokeMethod() {
        MethodCall methodCall = objectCreator.getMethodCall(null);
        try {
            pekTestClientTTransport.open();
            ActionResult actionResult = reflectServiceClient.invokeMethod(methodCall);
            pekTestClientTTransport.close();
            LOGGER.info(actionResult.toString());
        } catch (TException e) {
            LOGGER.error("", e);
            pekTestClientTTransport.close();
        }

    }
}
