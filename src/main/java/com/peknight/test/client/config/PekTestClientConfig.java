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
package com.peknight.test.client.config;

import com.peknight.common.service.State;
import com.peknight.test.thrift.reflect.ReflectService;
import com.peknight.test.thrift.service.MessageService;
import com.peknight.test.thrift.service.SystemService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

/**
 *
 *
 * @author PeKnight
 *
 * Created by PeKnight on 2017/7/31.
 */
@Configuration
public class PekTestClientConfig {

    @Value("${test.server.address}")
    private String address;

    @Value("${test.server.port}")
    private int port;

    @Bean
    public TTransport pekTestClientTTransport() {
        return new TFramedTransport(new TSocket(address, port));
    }

    @Bean
    public SystemService.Iface systemServiceClient(TTransport pekTestClientTTransport) {
        return new SystemService.Client(new TMultiplexedProtocol(new TBinaryProtocol(pekTestClientTTransport), "SystemService"));
    }

    @Bean
    public ReflectService.Iface reflectServiceClient(TTransport pekTestClientTTransport) {
        return new ReflectService.Client(new TMultiplexedProtocol(new TBinaryProtocol(pekTestClientTTransport), "ReflectService"));
    }

    @Bean
    public MessageService.Iface messageServiceClient(TTransport pekTestClientTTransport) {
        return new MessageService.Client(new TMultiplexedProtocol(new TBinaryProtocol(pekTestClientTTransport), "MessageService"));
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public State homeShellState() {
        return new State();
    }
}
