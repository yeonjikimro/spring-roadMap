package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!" ); // Model에 data는 hello라는 것을 보냄
        return "hello";
    }



    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // 파라미터로부터 넘어온 name을 받음
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // body 부에 직접 데이터를 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // view(화면) 없이 이 문자가 그대로 내려간다. http://localhost:8080/hello-string?name=spring!!!
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // http://localhost:8080/hello-api?name=spring!!! -> {"name":"spring!!!"} json 방식으로 나옴
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        } }





}
