package io.tjohander.marvelous.service

import io.tjohander.marvelous.model.api.marvel.ErrorContainer
import io.tjohander.marvelous.model.api.marvel.DataWrapper
import io.tjohander.marvelous.util.MarvelAuthGenerator.Companion.buildAuthString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono
import java.time.Instant

@Service
class MarvelApiService(
    @Autowired val client: WebClient,
    @Value("\${marvel-api.public-key}") val marvelApiPublicKey: String,
    @Value("\${marvel-api.private-key}") val marvelApiPrivateKey: String
) {
    fun getCharactersStartsWith(searchString: String): Mono<DataWrapper> {
        val authObject = buildAuthString(Instant.now(), marvelApiPublicKey, marvelApiPrivateKey)
        return client
            .get()
            .uri { uriBuilder ->
                uriBuilder.path("/v1/public/characters")
                    .queryParam("nameStartsWith", searchString)
                    .queryParam("ts", authObject.ts)
                    .queryParam("apikey", authObject.publicKey)
                    .queryParam("hash", authObject.md5Hash)
                    .build()
            }
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { it.bodyToMono<ErrorContainer>() }
            .bodyToMono(DataWrapper::class.java)
            .log()
    }
}