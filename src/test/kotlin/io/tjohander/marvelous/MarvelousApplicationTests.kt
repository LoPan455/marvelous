package io.tjohander.marvelous

import io.tjohander.marvelous.util.MarvelAuthGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
class MarvelousApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun testMarvelAuthGenerator() {
		val authGenerator = MarvelAuthGenerator("foo", "bar")
		val auth = authGenerator.buildAuthString(Instant.now())
		println(auth)
	}

}
