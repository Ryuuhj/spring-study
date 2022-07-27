package practice.spring.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {
    //웹 어플리케이션에서 /hello 라고 들어오면 해당 매서드 호출
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    //model - 웹페이지에서 쓰는 인벤토리 개념 (웹페이지에서 사용할 정보를 담아서 넘기는 그릇)
    //data로 검색하면 hello!!란 값을 사용?


    @GetMapping("hello-mvc")
    //외부에서 parameter을 받는 방식 -> @RequestParam ;; 웹페이지에서 정보 넘겨줄때
    // naver.com?name="hello"

    //미지수인 변수가 "name" , 변수 값이 name으로 requestParam을 이용해 값이 넘어감
    public String helloMvc(@RequestParam("name")  String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//중요! - http(header, body로 구성)에서 body에 return 데이터를 직접 넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
        //if "spring"입력 -> return값 = "hello spring"
    }

    // API를 통한 Data 전달을 구현하기 위해 전달할 Data로 객체 class 먼저 정의
    static class Hello{
        private String name; //맴버변수
        //name의 getter, setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    // API 관련 method 정의
    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi (@RequestParam("name")  String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //hello 객체를 return하는 method 생성
}
