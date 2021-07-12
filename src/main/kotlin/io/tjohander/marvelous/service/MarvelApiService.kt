package io.tjohander.marvelous.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.tjohander.marvelous.util.MarvelAuthGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.lang.Exception
import java.time.Instant

@Service
class MarvelApiService(
    @Autowired val marvelApiClient: WebClient,
    @Autowired val marvelAuthGenerator: MarvelAuthGenerator
) {
    fun getCharacters(): Mono<MarvelApiResponseContainer> {
        val authObject = marvelAuthGenerator.buildAuthString(Instant.now())
        return marvelApiClient
            .get()
            .uri { uriBuilder ->
                uriBuilder.path("/v1/public/characters")
                    .queryParam("nameStartsWith", "Sp")
                    .queryParam("ts", authObject.ts)
                    .queryParam("apikey", authObject.publicKey)
                    .queryParam("hash", authObject.md5Hash)
                    .build()
            }
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { it.bodyToMono<ErrorContainer>()}
            .bodyToMono()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class MarvelApiResponseContainer(
    val code: Int,
    val status: String,
    val etag: String,
    val data: MarvelApiPageData
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MarvelApiPageData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Character(
    val name: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorContainer(
    val code: String,
    override val message: String
) : Throwable()