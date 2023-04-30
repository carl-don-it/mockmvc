package com.example.mockmvc;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.JvmProxyConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = MockmvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //注解配置一个mockmvc
public class Test2 {

    @Resource
    MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        // 开一个模拟的服务器，代表第三方
        wireMockServer = new WireMockServer(WireMockConfiguration.options()
                .enableBrowserProxying(true)
                .dynamicPort()
                .dynamicHttpsPort()
                .trustAllProxyTargets(true));
        wireMockServer.start();
        //代理所有请求
        JvmProxyConfigurer.configureFor(wireMockServer);
    }

    @After
    public void destroy() {
        wireMockServer.stop();
        JvmProxyConfigurer.restorePrevious();
    }

    @Test
    public void test1() throws Exception {
        //模拟请求，也可以直接发起一个http请求，没啥区别，测试用前者吧
        mockMvc.perform(MockMvcRequestBuilders.post("/crawl/sdfsd")
                .content("{}")
                .headers(new HttpHeaders()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}
