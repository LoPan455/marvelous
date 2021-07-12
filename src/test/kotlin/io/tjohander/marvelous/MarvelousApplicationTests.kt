package io.tjohander.marvelous

import io.tjohander.marvelous.service.MarvelApiService
import io.tjohander.marvelous.util.MarvelAuthGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
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
        val authGenerator = MarvelAuthGenerator("foo", "bar")
        val auth = authGenerator.buildAuthString(Instant.now())
        println(auth)
    }

    @Test
    fun testMakeMarvelApiCall() {
       val result = apiService.getCharacters().block()
        println(result)
    }

}
