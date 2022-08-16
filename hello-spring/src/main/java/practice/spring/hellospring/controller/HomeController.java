package practice.spring.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //"/"(슬래쉬)의 의미>> localhost로 접속하자마자 호출되는 부분
    public String home(){
        return "home"; //즉, 들어가자마자 home.html이 호출됨 (return home)
    }

}
