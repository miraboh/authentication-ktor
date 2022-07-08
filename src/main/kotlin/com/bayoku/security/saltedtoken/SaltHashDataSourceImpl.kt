package com.bayoku.security.saltedtoken

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SaltHashDataSourceImpl : SaltHashDataSource {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        return SaltHash(
            hash = hash,
            salt = saltAsHex
        )
    }

    override fun verify(value: String, saltHash: SaltHash): Boolean {
        return DigestUtils.sha256Hex(saltHash.salt + value) == saltHash.hash
    }
}