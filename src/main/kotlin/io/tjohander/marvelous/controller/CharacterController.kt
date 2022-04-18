package io.tjohander.marvelous.controller

import io.tjohander.marvelous.model.api.marvel.DataWrapper
import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Controller
@RequestMapping("characters/v1")
class CharacterController(
    @Autowired private val service: MarvelApiService
) {

    @GetMapping("/find")
    @ResponseBody
    fun findCharacterByStartingString(@RequestParam q: String): Mono<DataWrapper> {
        return service.findCharacterByStartingString(q)
    }

    @GetMapping("/{characterId}")
    @ResponseBody
    fun getCharacterById(@PathVariable characterId: Int): Mono<DataWrapper> {
        return service.getCharacterById(characterId)
    }

    @GetMapping("/{characterId}/comics")
    @ResponseBody
    fun getComicsByCharacterId(@PathVariable characterId: Int): Mono<DataWrapper> {
        return service.getComicsByCharacterId(characterId)
    }
}