package com.bayoku.security.saltedtoken

interface SaltHashDataSource {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltHash
    fun verify(value: String, saltHash: SaltHash): Boolean
}