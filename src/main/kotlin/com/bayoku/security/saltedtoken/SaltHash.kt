package com.bayoku.security.saltedtoken

data class SaltHash(
    val hash: String,
    val salt: String
)
