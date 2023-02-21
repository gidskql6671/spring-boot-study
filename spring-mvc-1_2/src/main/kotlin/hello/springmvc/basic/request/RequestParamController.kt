package hello.springmvc.basic.request

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class RequestParamController {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    fun requestParamV1(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        log.info("username={}, age={}", username, age)

        response.writer.write("ok")
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(
        @RequestParam("username") memberName: String,
        @RequestParam("age") memberAge: Int
    ): String {
        log.info("username={}, age={}", memberName, memberAge)
        return "ok"
    }

    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username: String,
        @RequestParam age: Int
    ): String {
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam 사용
     * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
     * @RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 required=false 를 적용한다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(username: String, age: Int): String {
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam.required
     * /request-param-required -> username이 없으므로 예외
     *
     * 주의!
     * /request-param-required?username= -> 빈문자로 통과
     *
     * 주의!
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     * 코틀린 버전에서는 Int?가 아니라 Int로 사용할 시, defaultValue 설정 필요
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam(required = true) username: String,
        @RequestParam(required = false) age: Int
    ): String {
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam
     * - defaultValue 사용
     *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") username: String,
        @RequestParam(required = false, defaultValue = "-1") age: Int
    ): String {
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(@RequestParam paramMap: Map<String, Any>): String {
        log.info("username={}, age={}", paramMap["username"], paramMap["age"])
        return "ok"
    }
}