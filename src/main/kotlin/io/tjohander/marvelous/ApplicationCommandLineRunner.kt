package io.tjohander.marvelous

import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ApplicationCommandLineRunner(
    @Autowired val apiService: MarvelApiService
) : CommandLineRunner {

    // Just a sanity check to make sure we're setup to call downstream ok
    override fun run(vararg args: String): Unit  {
        val response = apiService.findCharacterByStartingString("Magneto")
        response.subscribe {
            println(it)
        }
    }
}