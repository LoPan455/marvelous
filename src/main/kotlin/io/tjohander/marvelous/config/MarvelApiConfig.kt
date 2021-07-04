package io.tjohander.marvelous.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.context.annotation.Bean as Bean

@Configuration
class MarvelApiConfig {

    @Bean(name = ["marvelApiClient"])
    fun marvelApiClient(): WebClient  {
        return WebClient.create("https://gateway.marvel.com")
    }
}