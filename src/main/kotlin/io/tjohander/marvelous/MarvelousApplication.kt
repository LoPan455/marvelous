package io.tjohander.marvelous

import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class MarvelousApplication

fun main(args: Array<String>) {
	runApplication<MarvelousApplication>(*args)
}
