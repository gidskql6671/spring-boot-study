package dong.kotlin_study.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class RestTemplateService {

    fun getName(): String {
        val uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api")
            .encode()
            .build()
            .toUri()

        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.getForEntity(uri, String::class.java)

        return responseEntity.body?: ""
    }

    fun getNameWithPathVariable(): String {
        val uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api/{name}")
            .encode()
            .build()
            .expand("test")
            .toUri()

        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.getForEntity(uri, String::class.java)

        return responseEntity.body?: ""
    }

    fun getNameWithParameter(): String {
        val uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api/param")
            .queryParam("name", "test")
            .encode()
            .build()
            .toUri()

        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.getForEntity(uri, String::class.java)

        return responseEntity.body?: ""
    }

    fun postWithBody(): ResponseEntity<String> {
        val uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api")
            .encode()
            .build()
            .toUri()

        val body = "test"

        val restTemplate = RestTemplate()

        return restTemplate.postForEntity(uri, body, String::class.java)
    }
}