package com.bayoku.plugins

import com.bayoku.data.user.UserDataSource
import com.bayoku.routes.authenticate
import com.bayoku.routes.getSecretInfo
import com.bayoku.routes.signIn
import com.bayoku.routes.signUp
import com.bayoku.security.saltedtoken.SaltHashDataSource
import com.bayoku.security.token.TokenConfig
import com.bayoku.security.token.TokenDataSource
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val saltHashDataSource by inject <SaltHashDataSource>()
    val userDataSource by inject <UserDataSource>()
    val tokenDataSource by inject <TokenDataSource>()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )

    install(Routing) {
        signUp(saltHashDataSource, userDataSource)
        signIn(userDataSource, saltHashDataSource, tokenDataSource, tokenConfig)
        authenticate()
        getSecretInfo()
    }
}
