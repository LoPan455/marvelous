package io.tjohander.marvelous.model.api.marvel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataWrapper(
    val code: Int,
    val status: String,
    val data: DataContainer<Any>,
    val etag: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorContainer(
    val code: String,
    override val message: String
) : Throwable()