package com.example.mockmvc;

import com.alibaba.fastjson.JSON;
import com.example.mockmvc.vo.Dog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * spring单元检验，不需要启动服务器
 *
 * @throws Exception
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
public class MockmvcApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void initMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //get,url参数
    @Test
    public void testGet() throws Exception {
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/mock/get/21")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    //post,url参数
    @Test
    public void testPost() throws Exception {
        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/mock/post")
                        .param("id", "24")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    //发送json，post请求
    @Test
    public void postJson() throws Exception {
        //String content = "{\"name\":\"elephant\",\"password\":\" \",\"birthDay\":" + System.currentTimeMillis() + "}";
        //        String content = "{\"name\":\"elephant\",\"password\":null}";
        Dog dog = new Dog("11", 1234);
        String content = JSON.toJSONString(dog);

        String result = mockMvc.perform
                (MockMvcRequestBuilders.post("/mock/post/json")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    //post,object,url参数
    @Test
    public void postForm() throws Exception {
        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/mock/post/form")
                        .param("age", "24")
                        .param("time", "23")//添加参数
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))//数据的格式
                .andExpect(MockMvcResultMatchers.status().isOk())//返回的状态是200
                .andReturn()
                .getResponse()
                .getContentAsString(); //将相应的数据转换为字符串
        System.out.println(result);
    }

}
