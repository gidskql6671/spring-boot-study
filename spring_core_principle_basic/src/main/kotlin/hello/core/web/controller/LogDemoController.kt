package hello.core.web.controller

import hello.core.common.MyLogger
import hello.core.web.service.LogDemoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.ObjectProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogDemoController(
    private val logDemoService: LogDemoService,
    private val myLoggerProvider: ObjectProvider<MyLogger>
) {

    @GetMapping("log-demo")
    fun logDemo(request: HttpServletRequest): String {
        val myLogger = myLoggerProvider.getObject()
        myLogger.requestURL = request.requestURL.toString()

        myLogger.log("controller test")
        logDemoService.logic("testId")

        return "ok"
    }
}