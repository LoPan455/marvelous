package io.tjohander.marvelous.service

import io.tjohander.marvelous.model.api.marvel.ErrorContainer
import io.tjohander.marvelous.model.api.marvel.DataWrapper
import io.tjohander.marvelous.util.MarvelAuthGenerator
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
    @Autowired val authService: MarvelAuthGenerator
) {
    fun findCharacterByStartingString(searchString: String): Mono<DataWrapper> {
        return client
            .get()
            .uri { uriBuilder ->
                val authObject = authService.getAuthString()
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

    fun getCharacterById(id: Int): Mono<DataWrapper> {
        return client
            .get()
            .uri { uriBuilder ->
                val authObject = authService.getAuthString()
                uriBuilder.path("/v1/public/characters/${id}")
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

    fun getComicsByCharacterId(characterId: Int): Mono<DataWrapper> =
        client
            .get()
            .uri { uriBuilder ->
                val authObject = authService.getAuthString()
                uriBuilder.path("/v1/public/characters/${characterId}/comics")
                    .queryParam("ts", authObject.ts)
                    .queryParam("apikey", authObject.publicKey)
                    .queryParam("hash", authObject.md5Hash)
                    .build()
            }
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { it.bodyToMono<ErrorContainer>() }
            .bodyToMono(DataWrapper::class.java)
            .log()

    fun getComicById(id: Int) =
        client
            .get()
            .uri { uriBuilder ->
                val authObject = authService.getAuthString()
                uriBuilder.path("/v1/public/comics/${id}")
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