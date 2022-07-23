package practice.spring.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HelloController {
    //웹 어플리케이션에서 /hello 라고 들어오면 해당 매서드 호출
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    //외부에서 parameter을 받는 방식 -> @RequestParam
    public String helloMvc(@RequestParam("name")  String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
