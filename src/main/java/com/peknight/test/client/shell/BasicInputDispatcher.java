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
import com.peknight.common.service.State;
import com.peknight.test.client.creator.PekTestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BasicInputDispatcher implements InputDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicInputDispatcher.class);

    public static final String INFO = "#i";

    public static final String EXIT = "#e";

    public static final String BEAN_CALL = "#bc";

    public static final String CONSTRUCTOR_CALL = "#cc";

    public static final String METHOD_CALL = "#mc";

    public static final String CLASS_INFO = "#bi";

    public static final String CONSTRUCTOR_INFO = "#ci";

    public static final String METHOD_INFO = "#mi";

    @Resource
    private State homeShellState;

    public void info() {
        LOGGER.info("[{}] Global Info", String.format("%5s", INFO));
        LOGGER.info("[{}] List Bean Call", String.format("%5s", BEAN_CALL));
        LOGGER.info("[{}] List Constructor Call", String.format("%5s", CONSTRUCTOR_CALL));
        LOGGER.info("[{}] List Method Call", String.format("%5s", METHOD_CALL));
        LOGGER.info("[{}] List Class Info", String.format("%5s", CLASS_INFO));
        LOGGER.info("[{}] List Constructor Info", String.format("%5s", CONSTRUCTOR_INFO));
        LOGGER.info("[{}] List Method Info", String.format("%5s", METHOD_INFO));
        LOGGER.info("[{}] Exit", String.format("%5s", EXIT));
    }

    @Override
    public String dispatch(String input) {
        switch (input) {
            case INFO:
                info();
                return null;
            case EXIT:
                homeShellState.setFinalize(true);
                System.exit(0);
                return input;
            case BEAN_CALL:
                LOGGER.info(" == Bean Call List == ");
                PekTestContext.listBeanCall();
                return null;
            case CONSTRUCTOR_CALL:
                LOGGER.info(" == Constructor Call List == ");
                PekTestContext.listConstructorCall();
                return null;
            case METHOD_CALL:
                LOGGER.info(" == Method Call List == ");
                PekTestContext.listMethodCall();
                return null;
            case CLASS_INFO:
                LOGGER.info(" == Class Info List == ");
                PekTestContext.listClassInfo();
                return null;
            case CONSTRUCTOR_INFO:
                LOGGER.info(" == Constructor Info List == ");
                PekTestContext.listConstructorInfo();
                return null;
            case METHOD_INFO:
                LOGGER.info(" == Method Info List == ");
                PekTestContext.listMethodInfo();
                return null;
            default:
                return input;
        }
    }
}