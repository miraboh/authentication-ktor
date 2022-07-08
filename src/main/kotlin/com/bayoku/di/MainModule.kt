package com.bayoku.di

import com.bayoku.data.user.UserDataSource
import com.bayoku.data.user.UserDataSourceImpl
import com.bayoku.security.saltedtoken.SaltHashDataSource
import com.bayoku.security.saltedtoken.SaltHashDataSourceImpl
import com.bayoku.security.token.TokenDataSource
import com.bayoku.security.token.TokenDataSourceImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("user_db")
    }

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }

    single<SaltHashDataSource> {
        SaltHashDataSourceImpl()
    }

    single<TokenDataSource> {
        TokenDataSourceImpl()
    }
}