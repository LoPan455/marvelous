package io.tjohander.marvelous

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarvelousApplication

fun main(args: Array<String>) {
	runApplication<MarvelousApplication>(*args)
}
