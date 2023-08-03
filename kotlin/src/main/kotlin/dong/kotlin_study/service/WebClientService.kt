package dong.kotlin_study.service

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WebClientService {

    fun getName(): String {
        val webClient = WebClient.builder()
            .baseUrl("http://localhost:9090")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        return webClient.get()
            .uri("/api/v1/crud-api")
            .retrieve()
            .bodyToMono(String::class.java)
            .block() ?: ""
    }

    fun getNameWithPathVariable(): String {
        val webClient = WebClient.create("http://localhost:9090")

        val responseEntity = webClient.get()
            .uri("/api/v1/crud-api/{name}", "test")
            .retrieve()
            .toEntity(String::class.java)
            .block()

        return responseEntity?.body ?: ""
    }

    fun getNameWithParameter(): String {
        val webClient = WebClient.create("http://localhost:9090")

        return webClient.get()
            .uri{ it.path("/api/v1/crud-api").queryParam("name", "test").build() }
            .exchangeToMono {
                if (it.statusCode() == HttpStatus.OK) {
                    it.bodyToMono(String::class.java)
                }
                else {
                    it.createException().flatMap{ e -> Mono.error(e)}
                }
            }
            .block() ?: ""
    }
}