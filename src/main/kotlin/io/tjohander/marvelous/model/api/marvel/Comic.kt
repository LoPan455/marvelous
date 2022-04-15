package io.tjohander.marvelous.model.api.marvel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.Instant
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class Comic(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Int,
    val variantDescription: String,
    val description: String,
    val modified: Instant,
    val format: String,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceURI: ResourceURI,
    val urls: List<URL>,
    val series: SeriesSummary,
    val variants: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val collectedIssues: List<ComicSummary>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: Image,
    val images: List<Image>,
    val creators: List<Creator>,
    val characters: List<Character>,
    val stories: List<Story>,
    val events: EventList)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SeriesSummary(
    val resourceURI: String? = null,
    val url: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ComicSummary(
    val resourceURI: String? = null,
    val url: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ComicDate(
    val type: String? = null,
    val date: Date? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ComicPrice(
    val type: String? = null,
    val price: Float? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<EventSummary>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventSummary(
    val resourceURI: String,
    val name: String
)

