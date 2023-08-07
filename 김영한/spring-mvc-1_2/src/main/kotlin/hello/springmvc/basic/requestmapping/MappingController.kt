package hello.springmvc.basic.requestmapping

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@RestController
class MappingController {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    /**
     * 기본 요청
     * spring 2 버전까지: 둘다 허용 /hello-basic, /hello-basic/
     * spring 3 버전부터: /hello-basic 만 허용. 가장 뒤의 슬래시를 제거하지 않기에, 구분됨
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping("/hello-basic")
    fun helloBasic(): String {
        log.info("helloBasic")
        return "ok"
    }

    @RequestMapping(value = ["/mapping-get-v1"], method = [RequestMethod.GET])
    fun mappingGetV1(): String {
        log.info("mappingGetV1")
        return "ok"
    }

    @GetMapping("/mapping-get-v2")
    fun mappingGetV2(): String {
        log.info("mapping-get-v2")
        return "ok"
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    fun mappingPath(@PathVariable("userId") data: String): String {
        log.info("mappingPath userId={}", data)
        return "ok"
    }

    /**
     * 파라미터로 추가 매핑
     * 특정 파라미터가 있거나 없는 조건을 추가할 수 있다. 잘 사용하지는 않는다.
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = ["/mapping-param"], params = ["mode=debug"])
    fun mappingParam(): String {
        log.info("mappingParam")
        return "ok"
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = ["/mapping-header"], headers = ["mode=debug"])
    fun mappingHeader(): String {
        log.info("mappingHeader")
        return "ok"
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/ *"
     * consumes="*\/ *"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = ["/mapping-consume"], consumes = ["application/json"])
    fun mappingConsumes(): String {
        log.info("mappingConsumes")
        return "ok"
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/ *"
     * produces = "*\/ *"
     */
    @PostMapping(value = ["/mapping-produce"], produces = ["text/html"])
    fun mappingProduces(): String {
        log.info("mappingProduces")
        return "ok"
    }
}