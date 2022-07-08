package com.bayoku

import com.bayoku.di.mainModule
import io.ktor.server.application.*
import com.bayoku.plugins.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    install(Koin){
        modules(mainModule)
    }

    configureSecurity()
    configureRouting()
    configureSerialization()
}
