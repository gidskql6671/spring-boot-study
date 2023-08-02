package dong.kotlin_study.actuator

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.stereotype.Component

@Component
class CustomInfoContributor: InfoContributor {
    override fun contribute(builder: Info.Builder) {
        val content = HashMap<String, Any>()
        content["code-info"] = "InfoContributor 구현체에서 정의한 정보입니다."

        builder.withDetail("custom-info-contributor", content)
    }
}