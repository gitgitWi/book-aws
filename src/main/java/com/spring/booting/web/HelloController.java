package com.spring.booting.web;

import com.spring.booting.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller that returns JSON
@RestController
public class HelloController {

//  @GetMapping = @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

//    @RequestParam("") 외부에서 API로 넘긴 파라미터를 가져옴
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto (@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
