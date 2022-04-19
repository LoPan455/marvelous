package io.tjohander.marvelous.controller

import io.tjohander.marvelous.model.api.marvel.Comic
import io.tjohander.marvelous.model.api.marvel.DataWrapper
import io.tjohander.marvelous.service.MarvelApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/comics/v1")
class ComicsController(
    @Autowired private val service: MarvelApiService
) {

    @GetMapping("/{id}")
    @ResponseBody
    fun getComicById(@PathVariable id: Int): Mono<DataWrapper<*>> = service
        .getComicById(id)
}