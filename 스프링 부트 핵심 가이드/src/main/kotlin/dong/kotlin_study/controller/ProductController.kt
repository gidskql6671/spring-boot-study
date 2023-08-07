package dong.kotlin_study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController {

    @GetMapping
    fun test(): String {
        return "Test"
    }

    @PostMapping
    fun postTest(): String {
        return "test"
    }
}