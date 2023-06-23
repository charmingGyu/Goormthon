package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hyoungmin.park
 */
@Controller
public class HomeController {

    @GetMapping("/") // /의 의미는 localhost:8080을 입력하면 public~ return 이 호출된다.
    public String home() {
        return "home";
    }


}