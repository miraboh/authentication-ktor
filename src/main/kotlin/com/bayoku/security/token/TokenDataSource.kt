package com.bayoku.security.token

interface TokenDataSource {
    fun generate(config: TokenConfig, vararg claims: TokenClaim): String
}