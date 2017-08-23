package com.peknight.test.client;

import com.peknight.common.config.PekApplication;
import com.peknight.common.springframework.context.ApplicationContextHolder;
import com.peknight.test.client.shell.HomeShell;
import org.springframework.boot.Banner;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

@EnableAsync
@PekApplication
public class TestClientApplication {

	@Resource
	private HomeShell homeShell;

	public void startClient() throws Exception {
		homeShell.shell();
	}
	public static void main(String[] args) throws Exception {
		ApplicationContextHolder.run(TestClientApplication.class, args, Banner.Mode.LOG);
		ApplicationContextHolder.getBean(TestClientApplication.class).startClient();
	}
}
