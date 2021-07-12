package io.tjohander.marvelous.util

import io.tjohander.marvelous.model.MarvelAuthComponents
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class MarvelAuthGenerator(
    @Value("\${marvel-api.public-key}") val marvelApiPublicKey: String,
    @Value("\${marvel-api.private-key}") val marvelApiPrivateKey: String
) {

    fun buildAuthString(timeStamp: Instant): MarvelAuthComponents {
        val preHashString = "${timeStamp.toEpochMilli()}$marvelApiPrivateKey$marvelApiPublicKey"
        val hashed: String = DigestUtils.md5Hex(preHashString)
        return MarvelAuthComponents(
            ts = timeStamp.toEpochMilli().toString(),
            publicKey = marvelApiPublicKey,
            md5Hash = hashed
        )
    }

}