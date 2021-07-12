package io.tjohander.marvelous.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.context.annotation.Bean as Bean

@Configuration
class MarvelApiConfig(
    @Value("\${marvel-api.base-url}") val baseUrl: String,
) {

    @Bean(name = ["marvelApiClient"])
    fun marvelApiClient(): WebClient  {
        return WebClient.create(baseUrl)
    }
}