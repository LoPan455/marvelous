package io.tjohander.marvelous.model.api.marvel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Character(
    val id: String,
    val name: String,
    val description: String,
    val resourceURI: String,
    val urls: List<URL>,
    val thumbnail: Image,
    val comics: List<Comic>,
    val stories: List<Story>,
    val events: List<Event>,
    val series: List<Series>
)
