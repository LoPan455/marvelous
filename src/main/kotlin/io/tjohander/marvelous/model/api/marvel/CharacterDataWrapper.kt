package io.tjohander.marvelous.model.api.marvel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val etag: String,
    val data: CharacterDataContainer
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorContainer(
    val code: String,
    override val message: String
) : Throwable()