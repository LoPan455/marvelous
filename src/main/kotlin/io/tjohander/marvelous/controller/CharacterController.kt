package io.tjohander.marvelous.controller

import io.tjohander.marvelous.model.api.marvel.Character
import io.tjohander.marvelous.model.api.marvel.DataWrapper
import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono

@Controller
@RequestMapping("characters/v1")
class CharacterController(
    @Autowired private val service: MarvelApiService
) {

    @GetMapping("/find")
    @ResponseBody
    fun findCharacterByName(@RequestParam q: String): Mono<DataWrapper> {
        return service.getCharactersStartsWith(q)
    }
}