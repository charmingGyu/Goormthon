package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * @author hyoungmin.park
 */
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, @RequestParam(required = false, defaultValue = "1") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello-template";

    }

    @GetMapping("hello-string")
    @ResponseBody // 직접 입력하겠다.
    public String helloString(@RequestParam("name") String name) {
        return /*<html>*/ "hello " + name /* + </html> */; // "hello spring" --> 이 문자가 그대로 내려간다.-->view 같은것이 없다.
    }

    @GetMapping("hello-api") // 문자열이 아닌 데이터를 필요로 하면 API 방식 많이 사용
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; // json

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



    }

