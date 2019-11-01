package com.example.mockmvc.controller;

import com.example.mockmvc.vo.Dog;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

}
