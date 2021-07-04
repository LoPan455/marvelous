package io.tjohander.marvelous.service

import io.tjohander.marvelous.util.MarvelAuthGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.time.Instant

@Service
class MarvelApiService(
        @Autowired val marvelApiClient: WebClient,
        @Autowired val marvelAuthGenerator: MarvelAuthGenerator
) {
        fun getCharacters(): Mono<ClientResponse> {
                return marvelApiClient
                        .get()
                        .uri("/v1/public/characters?name=Thor")
                        .headers { headers ->
                                val authObject = marvelAuthGenerator.buildAuthString(Instant.now())
                                headers.set("hash", authObject.md5Hash)
                                headers.set("ts", authObject.ts)
                                headers.set("apiKey", authObject.publicKey)
                        }
                        .exchange()
                        .toMono()
        }
}