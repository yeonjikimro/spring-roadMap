package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 롬복이 제공해주는 어노테이션
@Slf4j
@RestController
public class LogTestController {

    // 클래스 지정
    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";


        System.out.println("name = " + name);

        // '+' 라는 연산이 일어나면 쓸모없는 리소스를 사용하기에 {}를 사용하는 것 추천
        log.trace(" trace log="+ name);
        log.trace(" trace log={}", name);
        log.debug(" debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);
/*      결과값
        name = Spring
        2023-08-30T22:05:36.133+09:00  INFO 30181 --- [nio-8080-exec-2] h.springmvc.basic.LogTestController      :  info log=Spring */
        return "ok";
    }
}
