package com.example.mockmvc.controller;

import com.example.mockmvc.vo.Dog;
import com.example.mockmvc.vo.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName TestController
 * @date 2019年08月06日 下午 4:55
 */
@RestController
@RequestMapping(value = "/mock")

public class TestController {

    @GetMapping(value = "/get/{id}")
    public String testGet(@PathVariable String id) throws IOException {
        System.out.println("controller方法 : testGet");
        return "getMapping" + id;
    }

    @PostMapping("/post")
    public String testPost(String id) throws IOException {
        System.out.println("controller方法 : testPost");
        return "psotMapping" + id;
    }

    @PostMapping("/post/json")
    public Dog postJson(@RequestBody Dog dog) throws IOException {
        System.out.println("controller方法 : postJson");
        System.out.println("controller方法 : " + dog);
        return new Dog("11", 3);
    }

    //不能时json
    @PostMapping("/post/form")
    public Dog postForm(Dog dog) throws IOException {
        System.out.println("controller方法 : postForm");
        System.out.println("controller方法 : " + dog);
        return new Dog("11", 3);
    }


	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> query(User user, @PageableDefault(size = 10, page = 0) Pageable pageable) {

		// 通过反射方法，打印查询参数对象
		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));

		ArrayList<User> users = new ArrayList<>();
		users.add(new User().setUsername("user1").setPassword("1"));
		users.add(new User().setUsername("user2").setPassword("2"));
		users.add(new User().setUsername("user3").setPassword("3"));
		return users;
	}

}
