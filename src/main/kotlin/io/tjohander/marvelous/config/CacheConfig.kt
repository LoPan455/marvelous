package io.tjohander.marvelous.config

import com.github.benmanes.caffeine.cache.AsyncCache
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import io.micrometer.core.lang.NonNull
import io.tjohander.marvelous.model.api.marvel.Character
import io.tjohander.marvelous.model.api.marvel.DataWrapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {

    @Bean(name = ["characterCache"])
    fun characterCache(): AsyncCache<String, DataWrapper<*>> =
        Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofHours(24))
            .buildAsync()
}