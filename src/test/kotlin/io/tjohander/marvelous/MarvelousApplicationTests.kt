package io.tjohander.marvelous

import io.tjohander.marvelous.service.MarvelApiService
import io.tjohander.marvelous.util.MarvelAuthGenerator.Companion.buildAuthString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.Instant

@SpringBootTest
@ActiveProfiles("overrides")
class MarvelousApplicationTests(
    @Autowired val apiService: MarvelApiService
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun testMarvelAuthGenerator() {
        val auth = buildAuthString(Instant.now(), "foo", "bar")
        println(auth)
    }

    @Test
    fun testMakeMarvelApiCall() {
        val result = apiService.getCharacterById("Sp").block()
        println(result)
    }

}
