package hello.core.web.service

import hello.core.common.MyLogger
import org.springframework.stereotype.Service

@Service
class LogDemoService(private val myLogger: MyLogger) {

    fun logic(id: String) {
        myLogger.log("service id = $id")
    }
}