package io.tjohander.marvelous.config

import com.github.benmanes.caffeine.cache.AsyncCache
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import io.micrometer.core.lang.NonNull
import io.tjohander.marvelous.model.api.marvel.Character
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {

    @Bean
    fun characterCache(): Cache<String, Character> =
        Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofHours(24))
            .build()
}