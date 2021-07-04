package io.tjohander.marvelous

import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ApplicationCommandLineRunner(
    @Autowired val apiService: MarvelApiService
) : CommandLineRunner {

    override fun run(vararg args: String): Unit  {
        println(apiService.getCharacters())
    }
}