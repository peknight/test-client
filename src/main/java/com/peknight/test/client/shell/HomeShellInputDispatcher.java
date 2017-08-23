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
package com.peknight.test.client.shell;

import com.peknight.common.io.InputDispatcher;
import com.peknight.test.client.logic.ReflectLogic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 *
 * @author PeKnight
 *
 * Created by PeKnight on 2017/8/11.
 */
@Component
public class HomeShellInputDispatcher implements InputDispatcher {

    public static final String INFO = "$i";

    public static final String GET_CLASS = "$gc";

    public static final String LIST_CLASS = "$lc";

    public static final String LIST_BEAN = "$lb";

    public static final String GET_METHOD = "$gm";

    public static final String LIST_METHOD = "$lm";

    public static final String CREATE_BEAN = "$cb";

    public static final String INVOKE_METHOD = "$im";

    public static final String EMPTY = "";

    @Resource
    private BasicInputDispatcher basicInputDispatcher;

    @Resource
    private ReflectLogic reflectLogic;

    public void info() {
        LOGGER.info("[{}] Shell Info", String.format("%5s", INFO));
        LOGGER.info("[{}] Get Class", String.format("%5s", GET_CLASS));
        LOGGER.info("[{}] List Class", String.format("%5s", LIST_CLASS));
        LOGGER.info("[{}] List Bean", String.format("%5s", LIST_BEAN));
        LOGGER.info("[{}] Get Method", String.format("%5s", GET_METHOD));
        LOGGER.info("[{}] List Method", String.format("%5s", LIST_METHOD));
        LOGGER.info("[{}] Create Bean", String.format("%5s", CREATE_BEAN));
        LOGGER.info("[{}] Invoke Method", String.format("%5s", INVOKE_METHOD));
    }

    @Override
    public String dispatch(String input) {
        input =  basicInputDispatcher.dispatch(input);
        if (input == null) {
            return null;
        } else {
            switch (input) {
                case INFO:
                    info();
                    return null;
                case GET_CLASS:
                    reflectLogic.getClassInfo();
                    return EMPTY;
                case LIST_CLASS:
                    reflectLogic.listClassInfo();
                    return EMPTY;
                case LIST_BEAN:
                    reflectLogic.listBean();
                    return EMPTY;
                case GET_METHOD:
                    reflectLogic.getMethodInfo();
                    return EMPTY;
                case LIST_METHOD:
                    reflectLogic.listMethodInfo();
                    return EMPTY;
                case CREATE_BEAN:
                    reflectLogic.createBean();
                    return EMPTY;
                case INVOKE_METHOD:
                    reflectLogic.invokeMethod();
                    return EMPTY;
                default:
                    LOGGER.info("[INPUT] {}", input);
                    return input;
            }
        }
    }
}
