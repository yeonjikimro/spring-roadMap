package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * http://localhost:8080/request-param-v1?username=hi&age=12 파라미터로 전
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge)  {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age)  {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age)  {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age)  {

        /*
        null과 "" 는 다르다.
        required=true일 때 ""는 빈문자열이기에 통과가 된다.

         @RequestParam(required = false) int age
         일경우 int는 기본형이기에 null이 들어가지 못한다.
         그렇기에 Integer age로 바꿔야 한다.
         */
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        /*
        @RequestParam(required = false) int age
        사용 가능 -> 값이 안들어오면 defaultValue 값인 -1이 들어가기 때문
         */

        log.info("username={}, age={}", username, age);

        return "ok";
    }

        @ResponseBody
        @RequestMapping("/request-param-map")
        public String requestParamMap(@RequestParam Map<String, Object> paramMap){

            log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

            return "ok";
        }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        /*
        public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
                HelloData helloData = new HelloData();
                helloData.setUsername(username);
                helloData.setAge(age);
         */
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        /*
        ModelAttribute와 RequestParam은 생략 가능하다.
        생략 시 아래 규칙이 적용된다.
        String, int, Integer 같은 단순 타입은 @RequestParam
        나머지는 @ModelAttribute로 지정해준다.(argument resolver 로 지정해둔 타입 제외)
         */
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

}
